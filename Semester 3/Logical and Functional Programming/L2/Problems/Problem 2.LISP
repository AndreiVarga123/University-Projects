(defun mapSum(l)
  (cond
   ((numberp l) l)
   ((atom l) 0)
   (T (apply #'+ (mapcar #'mapSum l)))
  )
)

;mapSum(l) =  l if l is a number
;             0 if l is atomic
;             mapSum(l1) + mapSum(l2) + ... + mapSum(ln) otherwise
