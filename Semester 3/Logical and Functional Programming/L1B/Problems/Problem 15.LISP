(defun postOrder(L)
  (cond
   ((null L) nil)
   (T (append (postOrder (cadr L)) (append  (postOrder (caddr L)) (list (car L)) )))
  )
)