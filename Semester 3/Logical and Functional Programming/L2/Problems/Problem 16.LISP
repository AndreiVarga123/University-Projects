(defun makeLinear(l)
  (cond
   ((atom l) (list l))
   (T (apply #'append (mapcar #'makeLinear l)))
  )
)
  