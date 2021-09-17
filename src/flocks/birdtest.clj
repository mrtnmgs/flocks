(ns flocks.birdtest
    (:require [quil.core :as q]
              [quil.middleware :as m]))

(load "draw-bird")

(defn createBird []
  (let [a (rand (* 2 q/PI))]
    {
      :x (rand 500)
      :y (rand 500)
      :vx (Math/sin a)
      :vy (Math/cos a)
      :a (rand (* 2 q/PI))
      }))

(defn createFlock [] (repeatedly 20 createBird))

(defn updateBird [b]
  {
    :x (+ (:x b) (:vx b))
    :y (+ (:y b) (:vy b))
    :vx (Math/cos (:a b))
    :vy (Math/sin (:a b))
    :a (+ (:a b) 0.01)
    })

(defn setup []
  (q/frame-rate 30)
  (createFlock))

(defn update-state [state] (map updateBird state))

(defn draw-state1 [state]
  (q/background 240)
  (q/fill 0)
  (doseq [b state] (draw-bird (:x b) (:y b) (:vx b) (:vy b)))
  )

(q/defsketch flocks
  :title "Flocks"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state1
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

(defn -main [& args])

