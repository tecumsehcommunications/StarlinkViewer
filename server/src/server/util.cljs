(ns server.util
  (:require [cljs.nodejs :as node])
  (:import goog.dom))

  (def root "/home/user/lib/node-v12.2.0-linux-x64/lib/node_modules/")

  (def fs (node/require "fs"))
  (def gm (node/require (str root "gm")))
  (def request (node/require (str root "request")))
  (def parser (node/require (str root "node-html-parser")))
  (def mongodb (node/require (str root "mongodb")))
  (def livereload  (node/require (str root "livereload")))
  (def wss (node/require (str root "ws")))


  (defn livereload-on [ directory ]
    (let [ lrServer (.createServer livereload
                    (js-obj "exts"
                            (array "html" "svg" "css" "png" "jpg" "gif")))
        fileWatcher (.watch lrServer directory) ]))



  (defn newWss [ port ]
      (new wss.Server (js-obj "port" port)))  


  (deftype noradImageFetcher [ tempDir archive ] Object
           (init [ this time ]
             (set! this.time (str time))
             (set! this.wrkTime (str nil))
             (set! this.files "none")
             (set! this.lastFile "none")
             (set! this.timerHandle nil)
             (set! this.baseUrl  "https://radar.weather.gov/RadarImg/N0R/BHX/")                          
             (set! this.reqObject (js-obj
                                       "headers" (js-obj
                                                  "Host" "radar.weather.gov"
                                                  "User-Agent" "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:66.0) Gecko/20100101 Firefox/66.0"   
                                                  "Accept" "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
                                                  "Accept-Language" "en-US,en;q=0.5"
                                                  "Connection" "keep-alive"
                                                  "Upgrade-Insecure-Requests" 1)))
             (set! this.sleepTime  300000) ; 5 mins in ms
             (set! this.stdErr (fn [err] (if err (js.console.log err))))
           this)

           (queue [this]
             (set! this.timerHandle (js.setTimeout (fn [] (.getPic this)) this.sleepTime)))

           (dequeue [this]
             (if this.timerHandle
               (do
                 (js.clearTimeout this.timerHandle)
                 (set! this.timerHandle nil))))
        
           (scrubDir [ this ]
             (.readdir fs this.archive
                       (fn [err files] ; files is a js array
                         (let [ numFiles (- (.-length files) 1)
                                excess (- numFiles 61) ] ; no more than 61 pic in directory
                           (if (> excess 0)
                             (do
                               (set! this.files (.slice files (- excess 1) files.length))
                               (doseq [x (range excess) ]
                                 (.unlink fs
                                          (str this.archive (aget files x)) this.stdErr)))
                             (set! this.files files))
                           (set! this.lastFile
                               (aget files
                                     (- files.length 1)))))))
                           
           (getPic [this]
                (request.get
                    this.baseUrl  
                    this.reqObject
                   (fn [err res body]
                       (if err
                          (do
                             (js.console.log "norad page request error")
                             (set! this.sleepTime 60000) ; wait a minute and try again)
                             (.queue this))
                          (let [ tableRows (.reverse ; reversing table to start at bottom 
                                             (goog.dom.getChildren
                                              (.querySelector (parser.parse body) "table"))) 
                                 goodTable (.slice tableRows 0 (- (. tableRows -length) 3 )) ]
                                                        
                            (loop [ cnt 1 ]
                              (if (and (< cnt 20 ) (aget goodTable cnt))
                                (let [ name (.-innerHTML (goog.dom.getFirstElementChild
                                                            (aget (goog.dom.getChildren
                                                                (aget goodTable cnt)) 1)))
                                       year  (.substring name 4 8)
                                       month (.substring name 8 10)
                                       day   (.substring name 10 12)
                                       time  (.substring name 13 17)
                                       url   (str this.baseUrl name)
                                       tempfile (str this.tempDir "norad-" cnt ".gif")
                                       fname (str this.archive year month day time ".png")  ]
                                  
                                  (if (= 1 cnt) (set! this.wrkTime time))
                                  (if (= this.wrkTime this.time)
                                    (set! this.sleepTime 300000)
                                    (do
                                        (.pipe
                                          (request.get
                                            (str this.baseUrl name)
                                            this.reqObject)
                                          (.on
                                            (fs.createWriteStream tempfile)
                                            "finish"
                                            (fn []
                                              (js.console.log "tempfile name: " tempfile)
                                              (.write
                                               (.resize 
                                                (.rotate
;                                                (.resize
                                                  (gm tempfile)
;                                                  1600 1100)
                                                  "transparent" -12.5)
                                                1024 1024 "!")
                                               fname
                                               (fn [err]
                                                 (if err
                                                   (do   (js.console.log "norad image write error")
                                                           (js.console.log err)))
                                                 (.unlink fs tempfile this.stdErr))))))
                                        (recur (inc cnt)))))))
                          (set! this.sleepTime 300000)  
                          (set! this.time this.wrkTime)
                          (.scrubDir this)
                          (.queue this )))))))                                        

  (deftype geosImageFetcher [ tempDir archive ] Object
    ; require node filesystem (as fs) and node request as rs in scope       
           (init  [this time ]
             (set! this.time (str time))
             (set! this.files "none")
             (set! this.lastFile "none")
             (set! this.timerHandle nil)
             (set! this.reqString  "https://weather.msfc.nasa.gov/cgi-bin/get-abi?satellite=GOESWestconusband02&lat=40.4&lon=-124.0&zoom=1&width=558&height=992&quality=100&mapcolor=yellow")
             (set! this.urlPrefix  "https://weather.msfc.nasa.gov")
             (set! this.tempFile   (str tempDir "nasageo.jpg"))
             (set! this.sleepTime  300000) ; 5 mins in ms
             (set! this.stdErr (fn [err] (if err (js.console.log err))))          
           this)

           (queue [this]
             (set! this.timerHandle (js.setTimeout (fn [] (.getPic this)) this.sleepTime)))

           (dequeue [this]
             (if this.timerHandle
               (do
                 (js.clearTimeout this.timerHandle)
                 (set! this.timerHandle nil))))

           (scrubDir [ this ]
             (.readdir fs this.archive
                       (fn [err files] ; files is a js array
                         (let [ numFiles (- (.-length files) 1)
                                excess (- numFiles 61) ] ; no more than 61 pics in directory
                           (if (> excess 0)
                             (do
                               (set! this.files (.slice files (- excess 1) files.length))
                               (doseq [x (range excess) ]
                                 (.unlink fs (str this.archive (aget files x)) this.stdErr)))
                             (set! this.files files))
                           (set! this.lastFile
                               (aget files
                                    (- files.length 1)))))))           
           (getPic [this]
             (request.get
               this.reqString
               (fn [err res body]
                 (if err
                   (do
                     (js.console.log "geos satellite picture request error")
                     (set! this.sleepTime 60000)
                     (.queue this))
                   (let [ start   (+ 1 (.indexOf body "\"" (.indexOf body  "<IMG")))
                          end     (.indexOf body "\"" start)
                          srcStr  (.substring body start end)
                          fnStart (+ (.indexOf srcStr "/GOES") 5)
                          time (.substring srcStr fnStart (+ fnStart 4))
                          year (.substring srcStr (+ fnStart 4) (+ 8 fnStart))
                          day (.substring srcStr (+ fnStart 8) (+ 11 fnStart))
                          url (str "https://weather.msfc.nasa.gov" srcStr )                         
                          fname (str this.archive year day time ".jpg")  ]
           
                     (if (= time this.time)
                       (do
                         (set! this.sleepTime 60000) ; try again in one minute
                         (.queue this))
                       (do
                         (set! this.sleepTime 300000) ; five minute wait                          
                         (set! this.time time)
                         (.pipe
                          (request url)
                          (.on
                           (fs.createWriteStream this.tempFile)
                           "finish"
                           (fn []
                             (.write
                              (.resize
                                 (.crop
                                      (gm this.tempFile)
                                        558
                                        932
                                        0
                                        60)
                                 512 512 "!")
                                 fname
                                 (fn [err]
                                   (if err
                                     (do
                                       (js.console.log "nasa image write error")
                                       (js.console.log err))
                                     (do
                                       (.scrubDir this)
                                       (.queue this))))))))))))))))

