(ns app.util.frame )

; generic getanimationframe loop.  pass it the clock object that you find in app.util.time (to run any pulsers you want to attach.  Also pass it a function (on .init) that will execute each time requestanimationframe runs.

(deftype FrameLoop [ clock ] Object
        (init [ this onframe ]
          (set! this.reqId nil)
          (set! this.onframe onframe)
          (set! this.run (.bind this.run this))
        this)

        (run [ this ]
          (set! this.reqId (js.requestAnimationFrame this.run))
          (.onframe this)
          (.update this.clock (js/performance.now)))

        (stop [this]
              (js/cancelAnimationFrame this.reqId)))

