(defun numberProd(l)
	(cond
		((null l) 1)
		((numberp (car l)) (* (car l) (numberProd (cdr l))))
		(T (numberProd (cdr l)))
	)
)
;l - list
;flow(i)
; numberProd(l1,l2..ln) = return 1, if list is empty
;			  l1 * numberProd(l2..ln), if l1 is a number
;			  numberProd(l2..ln), otherwise

(defun replaceFirst(l e o)
	(cond
		((eq (car l) e) (cons o (cdr l)))
		(T (cons (car l) (replaceFirst (cdr l) e o)))
	)
)          
;l - list e - elem o - elem
;flow(i,i,i)
;replaceFirst([l1,l2..ln], e, o) = o + [l2..ln], if l1 = e
;				   l1 + replaceFirst([l2..ln], e, o), otherwise

(defun evalOp(op a b)
	(cond
		((string= op "+") (+ a b))
		((string= op "-") (- a b))
		((string= op "*") (* a b))
		((string= op "/") (/ b a))
	)
)
;op - string, a - number, b -number
;flow(i,i,i)
;evalOP(op, a, b) = return a + b, if op = "+"
;		    return a - b, if op = "-"
;		    return a * b, if op = "*"
;		    return a / b, if op = "/"

(defun evalExp(l s)
	(cond
		((null l) (car s))
		((numberp (car l)) (evalExp (cdr l) (cons (car l) s)))
		(T (evalExp (cdr l) (cons (evalOp (car l) (car s) (cadr s)) (cddr s))))
	)
)
;l - list, s - stack
;flow(i,i)
;evalExp([l1,l2,..ln], [s1,s2,..sn]) = return s1, if l is empty
;				       evalExp([l2..ln], [l1,s1,s2..sn])
;				       evalExp([l2..ln], [evalOp(l1, s1, s2),s3..sn])

(defun mainEvalExp(l)
	(evalExp (reverse l) '())
)
;l - list
;flow(i)
;mainEvalExp([l1,l2..ln]) = evalExp([ln,ln-1,..l1], [])

(defun nrApp(l e)
	(cond 
		((null l) 0)
		((eql (car l) e) (+ 1 (nrApp (cdr l) e)))
		(T (nrApp (cdr l) e))
	)
)
;l - list, e - elem
;flow(i, i)
;nrApp([l1,l2..ln], e) = return 0, if l is empty
;		         1 + nrApp([l2..ln], e), if l1 = e
;			 nrApp([l2..ln], e), otherwise

(defun delE(l e)
	(cond 
		((null l) NIL)
		((not (eql (car l) e)) (cons (car l) (delE (cdr l) e)))
		(T (delE (cdr l) e))
	)
)
;l - list, e - elem
;flow(i, i)
;delE([l1,l2..ln], e) = [] if l is empty
;			l1 + delE([l2..ln], e), if l1 != e
;			delE([l2..ln], e) otherwise

;l - list
;flow(i)
(defun makeL(l)
	(cond
		((null l) NIL)
		(T (cons (list (car l) (nrApp l (car l))) (makeL (delE l (car l)))))
	)
)
;makeL([l1,l2..ln]) = [] if l is empty
;		      [l1, nrApp([l1,l2..ln], l1)] + makeL(delE([l1,l2..ln], l1)), otherwise

