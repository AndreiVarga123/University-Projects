(defun myReverse(l r)
  (cond
   ((null l) r)
   (T (myReverse (cdr l) (cons (car l) r)))
  )
)
    


(defun reverse-deeply (list)
   (mapcar #'(lambda (li) 
               (cond
                ((consp li) (reverse-deeply li))
                (t li)))
           (myReverse list NIL))) 