(ns app.util.frame )

(deftype FrameLoop [ clock ] Object
        (init [ this ]
          (set! this.reqId nil)
          (set! this.run (.bind this.run this))
        this)

        (run [ this ]
          (set! this.reqId (js.requestAnimationFrame this.run))
          (.update this.clock (js/performance.now)))

        (stop [this]
              (js/cancelAnimationFrame this.reqId)))

