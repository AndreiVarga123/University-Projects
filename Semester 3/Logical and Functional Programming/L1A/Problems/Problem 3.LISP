(defun insertAfterP(L A P)
  (cond
   ((null L) NIL)
   ((eql (mod P 2) 0) (cons (car L) (cons A (insertAfterP (cdr L) A (+ 1 P)))))
   (T (cons (car L) (insertAfterP (cdr L) A (+ 1 P))))
  )
)

(defun makeLinear(l)
  (cond
   ((atom l) (list l))
   (T (apply #'append (mapcar #'makeLinear l)))
  )
)

(defun reverseAtoms(L)
  (reverse (makeLinear L))
)

(defun greatestD(L D)
  (cond
   ((eql 0 (apply #'+ (mapcar #'(lambda (X) (mod X D)) L))) D)
   (T (greatestD L (- D 1)))
  )
)

(defun mainGreatestD(L)
  (greatestD L (apply #'min L))
)

(defun nrApps(L A)
  (cond 
   ((eql L A) 1)
   ((atom L) 0)
   (T (apply #'+ (mapcar #'(lambda (X) (nrApps X A)) L)))
  )
)
