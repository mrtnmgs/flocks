(ns flocks.flock
  (:require [quil.core :as q]
    [flocks.bird :as bird]))

(defn randcoords [maxw maxh] [(rand-int maxw) (rand-int maxh)])

(defn init [w h flocksize] (vec (map vec (repeatedly flocksize #(randcoords w h)))))

(defn draw [flock]
  (doseq [[x y] flock] (bird/draw x y 1 -1))
)

