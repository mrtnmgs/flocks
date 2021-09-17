(load "utils")

(defn draw-bird [x y vx vy]
  (let [[nx ny] (normalize vx vy)]
    (q/stroke-weight 1)
    (q/stroke 0 0 255)
    (q/fill 128 128 255)

    (q/push-matrix)
    (q/translate x y)
    (q/triangle (* ny -5) (* nx 5) (* ny 5) (* nx -5) (* nx 10) (* ny 10))
    (q/pop-matrix)
    )
  )

(defn draw-flock-cohesion-alignment [x y vx vy]
  (let [[nx ny] (normalize vx vy)]
    (q/stroke-weight 1)
    (q/stroke 255 0 0)
    (q/line x y (+ x (* nx 20)) (+ y (* ny 20)))

    (q/stroke-weight 5)
    (q/stroke 0 0 255)
    (q/fill 128 128 255)
    (q/point x y)
    )
  )

