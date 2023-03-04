(defun nrAtoms(l)
  (cond
   ((atom l) 1)
   (T (apply #'+ (mapcar #'nrAtoms l)))
  )
)
