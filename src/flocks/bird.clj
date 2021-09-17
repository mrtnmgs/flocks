(ns flocks.bird
  (:require [quil.core :as q]
            [flocks.rules :as r]
            [flocks.utils :as u]))

(defn draw [[ x y vx vy ]]
  (let [[nx ny] (u/normalize vx vy)]
    (q/stroke-weight 1)
    (q/stroke 255 0 0)

    (q/push-matrix)
    (q/translate x y)
    (q/line 0 0 (* vx 20) (* vy 20))
    (q/pop-matrix)

    (q/stroke 0 0 255)
    (q/fill 128 128 255)

    (q/push-matrix)
    (q/translate x y)
    (q/triangle (* ny -5) (* nx 5) (* ny 5) (* nx -5) (* nx 10) (* ny 10))
    (q/pop-matrix)
    )
  )

(defn move [[x y vx vy] cohesion]
  (let [[nvx nvy] (r/velocity [x y vx vy] cohesion)]
    [(+ x vx) (+ y vy) nvx nvy]
    )
  )

