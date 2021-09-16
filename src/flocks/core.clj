(ns flocks.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(load "utils")
(load "flock")

(defn setup []
  (def flocksize 10)
  (def w (q/width))
  (def h (q/height))

  (flocksetup w h flocksize) ; defines initstate. Can't be done before we get w and h 
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. 
  ; (println (map deref initstate))
  {:flock initstate})

(defn update-state [state]
  {})

(defn draw-state [state]
  (q/background 240)
  (q/fill 0)
  (draw-flock state)
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
