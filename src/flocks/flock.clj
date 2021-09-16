(defn flocksetup [w h flocksize]
  (defn randcoords [] [(rand-int w) (rand-int h)])
  (def initstate (vec (map (comp ref vec) (repeatedly flocksize randcoords))))
  )

(defn draw-flock [flock]
    ;(draw-bird [10 40])
   (q/triangle 10 10 40 10 10 40)
)

