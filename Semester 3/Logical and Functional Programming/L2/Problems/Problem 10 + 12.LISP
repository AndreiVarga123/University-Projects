(defun replaceAWithB(l a b r)
  (cond
   ((null l) r)
   ((and (atom l) (eql l a)) b)
   ((and (atom l) (eql l b)) a)
   ((atom l) l)
   (T (append r (mapcar #'(lambda (l) (replaceAWithB l a b r)) l)))
  )
)