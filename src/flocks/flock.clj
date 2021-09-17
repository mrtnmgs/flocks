(ns flocks.flock
  (:require [quil.core :as q]
    [flocks.bird :as bird]))

(defn randpos [maxw maxh] [(rand-int maxw) (rand-int maxh)])

(defn randveloc [] (q/random-2d))

(defn randcoords [w h] (vec (concat (randpos w h) (randveloc))))

(defn init [w h flocksize] (atom (repeatedly flocksize #(randcoords w h))))

(defn draw [flock]
  (doseq [b flock] (bird/draw b))
)

(defn move [flock, cohesion]
  (map (fn [bird] (bird/move bird cohesion)) flock)
)
