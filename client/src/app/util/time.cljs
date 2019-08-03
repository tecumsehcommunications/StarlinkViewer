(ns app.util.time )

(deftype Clock [ ] Object
         
         (init [this]
           (set! this.stampT 0)
           (set! this.deltaT 0)
           (set! this.pulserA (array))
           this)

         (updater [ this el idx arr]
           (.ontick el this.deltaT))
         
         (update [ this newT ]
           (set! this.deltaT (- newT this.stampT))
           (set! this.stampT newT)
           (.forEach this.pulserA this.updater this))

         (add [ this pulser ] (.push this.pulserA pulser))
         
         (remove [ this fps ]
           (.forEach this.pulserA (fn [el idx arr]
              (if (= el.mpf (/ 1000 fps))
                (.splice arr idx 1))))))


(deftype Pulser [ fps ] Object
         
; objects added to pulser must have 1) a "name" property, to allow
; removal, and a pulse funtion the pulser will call when on each new
; frame

  (init [ this ]
         (set! this.mpf (/ 1000 fps))
         (set! this.anims (array))
         (set! this.deltaT 0)
   this)
   
   (pulse [ this el idx arr]
     (.onpulse el))
   
   (add [ this animF ]
     (.push this.anims animF))
   
   (remove [ this nameF ]
     (.forEach this.anims (fn [ el idx arr]
        (if ( = nameF el.name ) (.splice arr idx 1)))))

   (ontick [ this deltaT ]
     (set! this.deltaT (+ this.deltaT deltaT))
     (if (>= this.deltaT this.mpf)
       (do
         (.forEach this.anims this.pulse)
         (set! this.deltaT 0)))))
