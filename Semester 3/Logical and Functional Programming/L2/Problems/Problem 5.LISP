(defun mapSum(l)
  (cond
   ((and (numberp l) (evenp l) l))
   ((and (numberp l) (oddp l) (- 0 l))) 
   ((atom l) 0)
   (T (apply #'+ (mapcar #'mapSum l)))
  )
)