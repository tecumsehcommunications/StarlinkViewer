(ns app.render)

(defn init [ canvas container resizeArray ]
  
  (def webgl (new js.THREE.WebGLRenderer
                  (js-obj    "canvas" canvas
                             "antialias" true
                             "alpha" true )))
  
  (.setPixelRatio webgl js.window.devicePixelRatio )
 
  (.setSize webgl
            container.offsetWidth
            container.offsetHeight)
  
  (set! webgl.gammaOutput true)

  (set! webgl.resize
  (fn []
    (.setPixelRatio webgl js.window.devicePixelRatio )
    (.setSize webgl  container.offsetWidth container.offsetHeight)))
  
  (.push resizeArray webgl))
 
