(ns server.core
  (:require [cljs.nodejs :as node]
            [server.util :as util])
  (:import goog.dom))


(defn init [ ]
  
 (def lrHandle (util/livereload-on "/home/user/code/tecumseh/pub/"))


 (def noradFetcher (.init (new util/noradImageFetcher
                             "/home/user/code/site/pub/img/tmp/"                          
                             "/home/user/code/site/pub/img/norad/")
                             "newtime"))

  (.scrubDir noradFetcher )
  (.getPic noradFetcher)
  (.dequeue noradFetcher)
  
 (def geosFetcher (.init (new util/geosImageFetcher
                            "/home/user/code/site/pub/img/tmp/"
                            "/home/user/code/site/pub/img/nasa/")
                            "newtime"))

  (.scrubDir geosFetcher)
  (.getPic geosFetcher)
  (.dequeue geosFetcher)

  (def wss (util/newWss 3000))  

  (def users (js-obj))
  
  (defn sendTo [ connection message ]
    (if connection.send  (.send connection (js.JSON.stringify message))))


 (.on wss "connection"
       (fn [connection]
         (js.console.log "connection")
         (.on connection "message"
              (fn [msg]
                (let [ data (js.JSON.parse msg) ]
                  (case data.type
                    "lastFiles"
                           (do
                                 (sendTo connection (js-obj "type"  "lastFiles"
                                              "norad" noradFetcher.lastFile
                                              "nasa"  geosFetcher.lastFile)))
                           
                     "fileLists"
                           (do
                                 ( sendTo connection (js-obj "type"  "fileLists"
                                              "norad" noradFetcher.files
                                              "nasa"  geosFetcher.files)))
                      "login"
                           (do
                                 (js.console.log "user logged:" data.name)
                                 (if (aget users (. data -name))
                                   (sendTo connection (js-obj "type" "login"
                                                           "success" false))
                                   (do
                                     (aset users (. data -name) connection)
                                     (set! connection.name data.name)
                                     (sendTo connection (js-obj "type" "login"
                                                                "success" true)))))
                    "offer"    (let [ conn (aget users (. data -name)) ]
                                 (js.console.log "Sending offer to: " data.name)
                                 (if conn
                                   (do
                                     (set! connection.otherName data.name)
                                     (sendTo conn (js-obj "type"  "offer"
                                                          "offer" data.offer
                                                          "name" connection.name)))))
                    "answer"    (let [ conn (aget users (. data -name)) ]
                                  (js.console.log "Sending answer to: " data.name)
                                  (if conn
                                    (do
                                      (set! connection.otherName data.name)
                                      (sendTo conn (js-obj "type"   "answer"
                                                           "answer" data.answer)))))
                    "candidate" (let [ conn (aget users (. data -name)) ]
                                  (js.console.log "sending candidate to...")
                                  (if conn
                                    (sendTo conn (js-obj "type"      "candidate"
                                                         "candidate" data.candidate))))
                    
                    "leave"
                             (let [ conn (aget users (. data -name)) ]
                                  (if conn
                                    (do
                                      (set! conn.otherName nil)
                                      (sendTo conn (js-obj "type" "leave")))))

                    (sendTo connection (js-obj "type" "error"
                                               "message" "Command not found: " data.type))))))
         (.on connection "close"
              (fn []
                (if connection.name
                  (do
                    (js-delete users (. connection -name))
                    (if connection.otherName
                      (let [ conn (. connection -otherName ) ]
                            (if conn
                              (do
                                (set! conn.otherName nil)
                                (sendTo conn (js-obj "type" "leave"))))))))))))


