(defn randcoords [w h] ([(rand-int w) (rand-int h)]))
(defn initstate [w h] (vec (map (comp ref vec) (repeatedly flocksize (randcoords w h)))))

(defn draw-flock [flocksize]
    ;(draw-bird [10 20])
   (q/triangle 10 10 30 10 10 30)
)

