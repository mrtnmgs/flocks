(ns flocks.rules
    (:require [quil.core :as q]
              [flocks.utils :as u]))

(defn calc-flock-cohesion-alignment [flock]
  (let [
        b (vec (reduce (fn [b1 b2] (map + b1 b2)) flock))
        c (count flock)]
    (vec (map / b [c c c c]))))

(defn draw-flock-cohesion-alignment [x y vx vy]
  (let [[nx ny] (u/normalize vx vy)]
    (q/stroke-weight 1)
    (q/stroke 255 0 0)
    (q/line x y (+ x (* nx 10)) (+ y (* ny 10)))

    (q/stroke-weight 5)
    (q/stroke 0 0 255)
    (q/fill 128 128 255)
    (q/point x y)
    )
  )

; Vector pointing from bird to flock center
(defn make-vector [bird cohesion]
  [(- (u/x bird) (u/x cohesion)) (- (u/y bird) (u/y cohesion))])

; Length of vector pointing from bird to flock center
(defn distance [bird cohesion]
  (apply u/magnitude (make-vector bird cohesion)))

; Speed up based on distance from center of flock
(defn speed [bird cohesion]
  (let [m (distance bird cohesion)]
    (cond (< m 100) 1 :else (+ (* (- m 100) 0.01) 1))
  ))

; Rule Cohesion
; Rotate return angle to rotate bird (left or right) to move towards target
(defn check-cohesion [bird target]
  (let [
         left (distance [(- (u/x bird) (u/vy bird)) (+ (u/y bird) (u/vx bird))] target)
         right (distance [(+ (u/x bird) (u/vy bird)) (- (u/y bird) (u/vx bird))] target)
         ]
    (if (< left right) 0.03 -0.03))
  )

; Rule Out of Bounds
; If the bird is out of bounds return the rotation angle to face the center of the screen
(defn check-bounds-angle [bird]
  (let [x (u/x bird) y (u/y bird)]
    (if (or (< x 0) (> x (q/width)) (< y 0) (> y (q/height)))
      (check-cohesion bird [(/ (q/width) 2) (/ (q/height) 2)])
      0)))

; Rule Alignment
; Return the rotation (left or right) to align with the flock
(defn check-alignment [bird cohesion]
  (check-cohesion bird [(+ (u/x bird) (u/vx cohesion)) (+ (u/y bird) (u/vy cohesion))])
  )

; Calculate new velocity based on rules
(defn velocity [bird cohesion]
  (let [
         bird-speed (speed bird cohesion) ; speed of bird; go faster further away from flock
         [nvx, nvy] (u/normalize (u/vx bird) (u/vy bird)) ; normalized bird velocity to be unit vector
         flock-distance (distance bird cohesion) ; distance from bird to flock
         mouse-distance (distance bird [(q/mouse-x) (q/mouse-y)]) ; distance from bird to mouse
         ]
    (let [bounds-angle (check-bounds-angle bird)]
      (u/rotate (* nvx bird-speed) (* nvy bird-speed)
        (if (not= bounds-angle 0)
          bounds-angle ; if bird is off screen rotate to face center of screen
          (if (< mouse-distance 100)
            (- (check-cohesion bird [(q/mouse-x) (q/mouse-y)])) ; if close to mouse rotate away from it
            (if (> flock-distance 100)
              (check-cohesion bird cohesion) ; if far from flock rotate to center of flock
              (check-alignment bird cohesion)))))))) ; if near to flock rotate to align with flock

