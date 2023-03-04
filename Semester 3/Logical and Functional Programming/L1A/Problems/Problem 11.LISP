(defun makeLinear(L)
  (cond
   ((null L) nil)
   ((atom L) (list L))
   (T (apply #'append (mapcar #'makeLinear L)))
  )
)

(defun findMax(L M)
  (cond
   ((null L) M)
   ((> M (car L)) (findMax (cdr L) M))
   (T (findMax (cdr L) (car L)))
  )
)

(defun leastCoMul(L M)
  (cond
   ((eql (apply #'+ (mapcar #'(lambda (X) (mod M X)) L)) 0) M)
   (T (leastCoMul L (+ 1 M)))
  )
)

(defun mainLeastMul(L)
  (leastCoMul (makeLinear L) (findMax (cdr (makeLinear L)) (car (makeLinear L))))
)

(defun isMountain(L F)
  (cond
   ((null (cadr L)) T)
   ((and (< (car L) (cadr L)) (eql F 1)) nil)
   ((and (> (car L) (cadr L)) (eql F 0)) (isMountain (cdr L) 1))
   (T (isMountain (cdr L) F))
  )
)

(defun mainMountain(L)
  (cond
   ((null (cddr L)) nil)
   (T (isMountain L 0))
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

(defun noMax(L M)
  (cond
   ((null L) nil)
   ((eql (car L) M) (noMax (cdr L) M))
   ((listp (car L)) (cons (noMax (car L) M) (noMax (cdr L) M)))
   (T (cons (car L) (noMax (cdr L) M)))
  )
)

(defun mainNoMax(L)
  (noMax L (deepMax L -1))
)

(defun deepEvenProd(L)
  (cond
   ((null L) 1)
   ((and (numberp (car L)) (eql 0 (mod (car L) 2))) (* (car L) (deepEvenProd (cdr L))))
   ((listp (car L)) (* (deepEvenProd (car L)) (deepEvenProd (cdr L))))
   (T (deepEvenProd (cdr L)))
  )
)

