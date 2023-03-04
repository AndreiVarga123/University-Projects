(defun preOrder(L)
  (cond
   ((null L) nil)
   (T (append (list (car L)) (append (preOrder (cadr L)) (preOrder (caddr L)))))
  )
)