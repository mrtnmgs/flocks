(ns flocks.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state.
  {:x 80 :y 80 :vx 1 :vy -1})

(defn update-state [state]
  {:x (+ (:x state) (:vx state)) :y (+ (:y state) (:vy state)) :vx (:vx state) :vy (:vy state)})

(defn draw-bird [x y vx vy]
  (q/push-matrix)
  (q/translate x y)
  (q/line 0 0 vx vy)
  (q/pop-matrix)

  (q/push-matrix)
  (q/translate x y)
  (q/rotate (Math/atan (/ vy vx)))
  (q/triangle -10 -10 10 -10 0 20)
  (q/pop-matrix)
)

(defn draw-state [state]
  (q/background 240)
  (q/fill 0)
  (q/translate 100 100)
  ; Calculate x and y coordinates of the circle.
  (draw-bird (:x state) (:y state) (:vx state) (:vy state))
  (draw-bird 10 10 10 50)
  (draw-bird 50 50 200 50)
  (draw-bird 100 100 50 75)
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

