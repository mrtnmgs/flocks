(ns flocks.utils
  (:require [quil.core :as q]))

(defn poltocar [radius angle]
    [(/ 180 (* radius (Math/cos (* Math/PI angle))))
    (/ 180 (* radius (Math/sin (* Math/PI angle))))]
)

(defn x [b] (get b 0))
(defn y [b] (get b 1))
(defn vx [b] (get b 2))
(defn vy [b] (get b 3))

(defn magnitude [x y]
    (let [m (q/sqrt (+ (q/sq x) (q/sq y)))] m))

(defn normalize [x y]
    (let [m (magnitude x y)] [(/ x m) (/ y m)]))

(defn rotate [x y a]
    (let [sin (Math/sin a) cos (Math/cos a)]
      [(- (* cos x) (* sin y)) (+ (* sin x) (* cos y))]))
