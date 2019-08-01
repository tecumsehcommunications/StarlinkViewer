(ns server.scratch
  (:require [cljs.nodejs :as node]))

(def root "/home/user/lib/node-v12.2.0-linux-x64/lib/node_modules/")

(def ffmpeg (node/require (str root "ffmpeg")))
(def util (node/require "util"))

(def ffprobe (node/require (str root "ffprobe")))
(def ffprobeStatic (node/require
                    (str root "ffprobe-static")))


(defn process-video [ video path ]
  
  ; step one - optimize for web
  (new ffmpeg video
       (fn [err outVideo]
         (if (not err)
           (do
             (.addCommand outVideo "-vcodec" "h264")
             (.addCommand outVideo "-acodec" "aac")
             (.addCommand outVideo "-strict" "-2")
             (.addCommand outVideo "-movflags" "faststart")
             (.addCommand outVideo "-vf" "scale=1600x900")
             (.save outVideo (str path "video.mp4")
                    (fn [ err file ]
                      (if (not err)
                        (do
                          (new ffmpeg file
                               (fn [err newVideo]
                                 (.fnExtractSoundToMP3 newVideo (str path "video.mp3")
                                                       (fn [err file]
                                                         (if err (js.console.log "sound extraction failure"))))
                                 (ffprobe file
                                          (js-obj "path" ffprobeStatic.path)
                                          (fn [err info]
                                            (if err
                                              (js.console.log err)
                                              (do
                                                (.addCommand newVideo "-frames:v" 1)
                                                (.addCommand newVideo
                                                      "-filter_complex"
                                                      (str "compand=2,showwavespic=s="
                                                           (* 4 (js.parseInt (aget info "streams" 0 "nb_frames")))
                                                           "x120"))
                                                (.save newVideo (str path "frames.png")
                                                       (fn [err file]
                                                         (if err
                                                           (js.console.log "frames output error")
                                                           (js.console.log "frames pic created")))))))))))))))))))
      


(process-video "/home/user/code/newview/video.mp4" "/home/user/code/newview/pub/")



(fn [info] (js.console.log info)))

 
 (fn [info] (def mark (js.JSON.parse info))))

(defn fmpegWrap [ vidPath ]
  (new ffmpeg vidPath
       (fn [ err video ]
         (if (not err)
           video
           (js.console.log err)))))

(defn putWaveImg [ video path ]
  (.addCommand video "-filter_complex" "compand=2,showwavespic=s=640x120")
  (.addCommand video "-frames:v" 1)
  (.save video path))


                        
                        

                        
                        
                        

                        

ffprobe -v error -select_streams v:0 -show_entries stream=nb_frames -of default=nokey=1:noprint_wrappers=1 video.mp4


 
(js.console.log (util.inspect avideo true nil true))
(js.console.log  (js.JSON.stringify thisvideo ) 

                 
