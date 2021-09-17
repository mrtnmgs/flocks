(ns flocks.birdtest
    (:require [quil.core :as q]
              [quil.middleware :as m]))

(load "bird")
(load "sim")

(defn createBird []
  (let [a (rand (* 2 q/PI))]
    [
      (rand 500)
      (rand 500)
      (Math/sin a)
      (Math/cos a)
      ]))

(defn createFlock [] (repeatedly 5 createBird))

(defn setup []
  (q/frame-rate 30)
  (createFlock))

(defn update-state [state]
  (let [c (calc-flock-cohesion-alignment state)]
    (map (fn [b] (update-bird b c)) state)))

(defn draw-state [state]
  (q/background 240)
  (q/fill 0)
  (doseq [b state] (flocks.bird/draw b))
  (let [c (calc-flock-cohesion-alignment state)]
    (apply flocks.bird/draw-flock-cohesion-alignment c)
    )
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

