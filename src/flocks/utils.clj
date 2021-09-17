(ns flocks.utils)

(defn poltocar [radius angle]
    [(/ 180 (* radius (Math/cos (* Math/PI angle))))
    (/ 180 (* radius (Math/sin (* Math/PI angle))))]
)

; (defn cartopol [x y]
;   ;r = √ ( x2 + y2 )
;   ;θ = tan-1 ( y / x )
;   [()
;   (Math/atan (/ y x))]
; )
