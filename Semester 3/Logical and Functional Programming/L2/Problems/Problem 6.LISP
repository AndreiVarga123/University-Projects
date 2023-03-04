(defun mapSum(l)
  (cond
   ((numberp l) l)
   ((atom l) -1)
   (T (apply #'max (mapcar #'mapSum l)))
  )
)