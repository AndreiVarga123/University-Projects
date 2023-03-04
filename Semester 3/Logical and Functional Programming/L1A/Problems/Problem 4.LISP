(defun vectorSum(L1 L2)
 (cond
  ((null L1) nil)
  (T (cons (+ (car L1) (car L2) ) (vectorSum (cdr L1) (cdr L2))))
 )
)

(defun makeLinear(l)
  (cond
   ((atom l) (list l))
   (T (apply #'append (mapcar #'makeLinear l)))
  )
)
  
(defun reverseCont(L C)
 (cond
  ((null L) C)
  ((atom (car L)) (reverseCont (cdr L) (cons (car L) C)))
  (T (append C (list (reverseCont (car L) NIL)) (reverseCont (cdr L) NIL)))
 )
)

(defun mainReverse(L)
  (reverseCont L NIL)
)
 
(defun notDeepM(L M)
  (cond 
   ((null L) M)
   ((and (numberp (car L)) (< M (car L))) (notDeepM (cdr L) (car L)))
   (T (notDeepM (cdr L) M))
  )
)

(defun mainMax(L)
  (notDeepM L -1)
)