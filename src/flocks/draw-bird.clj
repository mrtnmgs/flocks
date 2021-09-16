(defn draw-bird [x y vx vy]
  (q/push-matrix)
  (q/translate x y)
  (q/line 0 0 vx vy)
  (q/pop-matrix)

  (q/push-matrix)
  (q/translate x y)
  (q/rotate (Math/atan (/ vy vx)))
  (q/triangle -10 -10 10 -10 0 20)
  (q/pop-matrix))

