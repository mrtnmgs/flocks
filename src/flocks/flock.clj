(load "draw-bird")

(defn flocksetup [w h flocksize]
  (defn randcoords [] [(rand-int w) (rand-int h)])
  (def initstate (vec (map (comp ref vec) (repeatedly flocksize randcoords))))
  )

(defn draw-flock [state]
  ;(q/triangle 10 10 20 30 30 20)
  ; (defn myfunc [[x y]] (draw-bird x y 1 -1))
  (defn myfunc [[x y]] (println y))
  (map myfunc (:flock state))
)

