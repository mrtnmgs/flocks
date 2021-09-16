(ns flocks.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(load "utils")

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  (def flocksize 10)
  (defn randcoords [] ([(rand-int (q/width)) (rand-int (q/height))]))
  (def initstate (vec (map (comp ref vec) (repeatedly flocksize randcoords))))
  ; setup function returns initial state. 
  {:flock initstate})

(defn update-state [state]
  {})

(defn draw-bird [pos]
  (q/triangle 10 10 20 30 30 20)
  ; (let [angle 45
  ;       x (* 150 (q/cos angle))
  ;       y (* 150 (q/sin angle))
  ;       pos [20 20]]
  ;   ; Move origin point to the center of the sketch.
  ;   (q/with-translation [(/ (q/width) 2)
  ;                        (/ (q/height) 2)]
; ))
)

(defn draw-flock [flocksize]
      (draw-bird [10 20]))


(defn draw-state [state]
  (q/background 240)
  (q/fill 0)
  ; Calculate x and y coordinates of the circle.
  (draw-flock 4)
)

(q/defsketch flocks
  :title "Flocks"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

(defn -main [& args])

