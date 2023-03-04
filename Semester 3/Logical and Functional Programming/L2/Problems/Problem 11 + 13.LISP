(defun whatDepth(l lvl)
  (cond
   ((atom l) lvl)
   (T (apply #'max (mapcar #'(lambda (l) (whatDepth l (+ lvl 1))) l)))
  )
)