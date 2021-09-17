(ns flocks.bird
  (:require [quil.core :as q]))

(defn magnitude [x y]
  (let [m (q/sqrt (+ (q/sq x) (q/sq y)))] m))

(defn normalize [x y]
  (let [m (magnitude x y)] [(/ x m) (/ y m)]))

(defn draw [x y vx vy]
  (let [[nx ny] (normalize vx vy)]
    (q/stroke-weight 1)
    (q/stroke 255 0 0)

    (q/push-matrix)
    (q/translate x y)
    (q/line 0 0 (* nx 20) (* ny 20))
    (q/pop-matrix)

    (q/stroke 0 0 255)
    (q/fill 128 128 255)

    (q/push-matrix)
    (q/translate x y)
    (q/triangle (* ny -5) (* nx 5) (* ny 5) (* nx -5) (* nx 10) (* ny 10))
    (q/pop-matrix)
    )
  )

(defn move [x y vx vy]
  [(+ x vx) (+ y vy) vx vy]
)
