(ns app.core
  (:require [app.repl :as repl]
            [app.util.frame :as frame]
            [app.render]
            [app.events :as events]
            [app.util.time :as time]))


(def resizeArray (array))
(set! js/window.onresize (fn [e] (.forEach resizeArray
                                           (fn [ el idx arr ] (.resize el)))))

(defn ^:export main [  ]
   ; general setup for all scenes, specific modules loaded last

  
  (def clock (.init (time/Clock.)))
  (def frameLoop (.init (new frame/FrameLoop clock)))
  (def pulser (.init (new time/Pulser 20 ))) ; 20fps pulser for animations
  
  (.add clock pulser)
  (.add pulser (. leftTabControls -controller))
  (.run frameLoop)
  
)
  
(set! js/cljs-entry-point main)
