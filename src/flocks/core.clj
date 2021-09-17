(ns flocks.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [flocks.utils :as utils]
            [flocks.flock :as flock]
            [flocks.rules :as r]))

(defn setup []
  (def flocksize 100)
  (def w (q/width))
  (def h (q/height))

  (q/frame-rate 30)
  ; setup function returns initial state.
  {:flock (flock/init w h flocksize)})

(defn draw-state [state]
  (q/background 240)
  (q/fill 0)
  (let [ cohesion (r/calc-flock-cohesion-alignment @(:flock state)) ]
    (apply r/draw-flock-cohesion-alignment cohesion)
    (flock/draw @(:flock state))
    (swap! (:flock state) (fn [flock] (flock/move flock cohesion)))
    )
)

(q/defsketch flocks
  :title "Flocks"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :draw draw-state
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

(defn -main [& args])
