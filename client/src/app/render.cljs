(ns app.render)

(defn init [ canvas container ]
  (def webgl (new js.THREE.WebGLRenderer (js-obj    "canvas" canvas
                                                    "antialias" true
                                                    "alpha" true )))
  (.setPixelRatio webgl js/window.devicePixelRatio )
 
  (.setSize webgl  (.-offsetWidth container)  (.-offsetHeight container))
  (set! webgl.resize
        (fn []
          (.setPixelRatio webgl js/window.devicePixelRatio )
          (.setSize webgl  (.-offsetWidth container)  (.-offsetHeight container))))
  (.push app.core.resizeArray webgl))
 
