(defn x [b] (get b 0))
(defn y [b] (get b 1))
(defn vx [b] (get b 2))
(defn vy [b] (get b 3))

(defn magnitude [x y]
  (let [m (q/sqrt (+ (q/sq x) (q/sq y)))] m))

(defn normalize [x y]
  (let [m (magnitude x y)] [(/ x m) (/ y m)]))

(defn calc-flock-cohesion-alignment [flock]
      (let [b (reduce (fn [b1 b2]
                        [
                          (+ (x b1) (x b2))
                          (+ (y b1) (y b2))
                          (+ (vx b1) (vx b2))
                          (+ (vy b1) (vy b2))
                          ]) flock)
            c (count flock)]
        [
          (/ (x b) c)
          (/ (y b) c)
          (/ (vx b) c)
          (/ (vy b) c)
          ]
        )
  )

(defn update-bird [b c]
  (let [
         bird_vel (normalize (vx b) (vy b))
         flock_center (normalize (- (x c) (x b)) (- (y c) (y b)))
         flock_vel (normalize (vx c) (vy c))
         ]
    [
      (+ (x b) (vx b))
      (+ (y b) (vy b))
      (+ (* 0.98 (vx b)) (* 0.01 (x flock_center) (* 0.01 (x flock_vel))))
      (+ (* 0.98 (vy b)) (* 0.01 (y flock_center) (* 0.01 (x flock_vel))))
      ])
    )
