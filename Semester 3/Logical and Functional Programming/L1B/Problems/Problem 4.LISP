(defun makeType1(L)
  (cond
   ((null L) nil)
   ((null (cadr L)) (list (car l) 0))
   ((null (caddr L)) (append (list (car l) 1) (makeType1 (cadr L))))
   (T (append (list (car L) 2) (append (makeType1 (cadr L)) (makeType1 (caddr L)))))
  )
)