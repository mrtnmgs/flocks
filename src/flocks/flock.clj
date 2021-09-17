(ns flocks.flock
  (:require [quil.core :as q]
    [flocks.bird :as bird]))

(defn randpos [maxw maxh] [(rand-int maxw) (rand-int maxh)])

(defn randveloc [] [(- 1 (rand 2)) (- 1 (rand 2))])

(defn randcoords [w h] (vec (concat (randpos w h) (randveloc))))

(defn init [w h flocksize] (vec (map vec (repeatedly flocksize #(randcoords w h)))))

(defn draw [flock]
  (doseq [[x y vx vy] flock] (bird/draw x y vx vy))
)

; (defn move [flock]
;   (doseq [x y
 ;  )
