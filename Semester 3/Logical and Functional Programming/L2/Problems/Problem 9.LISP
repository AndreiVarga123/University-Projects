(defun removeA(l a r)
  (cond
   ((null l) r)
   ((and (atom l) (eql a l)) NIL)
   ((atom l) (list l))
   (T (append r (list (apply #'append (mapcar #'(lambda (l) (removeA l a r)) l)))))
  )
)
