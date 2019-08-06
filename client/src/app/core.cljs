(ns app.core
  (:require [app.repl :as repl]
            [app.util.frame :as frame]
            [app.render :as renderer]
            [app.util.time :as time]))


(def resizeArray (array))
(set! js/window.onresize (fn [e] (.forEach resizeArray
                                           (fn [ el idx arr ] (.resize el)))))

(defn ^:export main [  ]
  
  (def mainContainer (.getElementById js.document "mainContainer"))
  (def wglCanvas (.getElementById js.document "wglCanvas"))
  (renderer/init wglCanvas mainContainer resizeArray)
  (def camera (new js.THREE.PerspectiveCamera 45
                   (/ wglCanvas.offsetWidth
                      wglCanvas.offsetHeight)
                   1 100))

  
  (.set camera.position 0 0 35)
  (set! camera.resize
        (fn []
          (set! camera.aspect
                (/ mainContainer.offsetWidth
                   mainContainer.offsetHeight))
          (.updateProjectionMatrix camera)))
  (.push resizeArray camera)       


  (def gLoader (new js.GLTFLoader ))
  (.load gLoader "scn/celestial-globe.gltf"
         (fn [ gltf  ]
           (def scene gltf.scene)
           (def earth (.getObjectByName scene "earth"))
           (def celSphere (.getObjectByName scene "celSphere"))
           (set! earth.material.emissive earth.material.color)
           (set! celSphere.material.emissive celSphere.material.color)

           (set! scene.background (new js.THREE.Color 0x383838))
           (def clock (.init (time/Clock.)))
           (def frameLoop
            (.init (new frame/FrameLoop clock)
                   (fn []
                     (.render renderer/webgl scene camera))))
            (.run frameLoop))))

(set! js.window.onload main)

