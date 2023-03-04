(defun dotProduct(V1 V2)
  (cond
   ((or (null V1) (null V2)) 0)
   (T (+ (* (car V1) (car V2)) (dotProduct (cdr V1) (cdr V2))))
  )
)

(defun deepMax(L M)
 (cond
  ((null L) M)
  ((and (numberp (car L)) (< M (car L))) (deepMax (cdr L) (car L)))
  ((listp (car L)) (max (deepMax (car L) M) (deepMax (cdr L) M)))
  (T (deepMax (cdr L) M))
 )
)

(defun evalOp(op a b)
	(cond
		((string= op "+") (+ a b))
		((string= op "-") (- a b))
		((string= op "*") (* a b))
		((string= op "/") (/ b a))
	)
)

(defun evalExp(l s)
	(cond
		((null l) (car s))
		((numberp (car l)) (evalExp (cdr l) (cons (car l) s)))
		(T (evalExp (cdr l) (cons (evalOp (car l) (car s) (cadr s)) (cddr s))))
	)
)
				      
(defun mainEvalExp(l)
	(evalExp (reverse l) '())
)


(defun oddList (L)
  (cond
   ((null L) NIL)
   ((null (cdr L)) T)
   (T (oddList (cddr L)))
   )
)
