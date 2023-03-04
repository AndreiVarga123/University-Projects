(defun inOrder(L)
  (cond
   ((null L) nil)
   (T (append (inOrder (cadr L)) (append (list (car L)) (inOrder (caddr L)))))
  )
)