(ns flocks.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [flocks.utils :as utils]
            [flocks.flock :as flock]))

(defn setup []
  (def flocksize 10)
  (def w (q/width))
  (def h (q/height))

  (q/frame-rate 3)
  (q/color-mode :hsb)
  ; setup function returns initial state. 
  {:flock (flock/init w h flocksize)})

(defn update-state [state]
  ;(println "UPDATE")
  ;(println (map deref (:flock state)))
  state)

(defn draw-state [state]
  (q/background 240)
  (q/fill 0)
  ;(println (:flock state))
  (flock/draw (:flock state))
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
