(defun setDiff (L1 L2)
  (cond
   ((null L1) nil)
   ((not (member (car L1) L2)) (cons (car L1) (setDiff (cdr L1) L2)))
   (T (setDiff (cdr L1) L2))
  )
)

(defun reverseAll (L)
  (mapcar #'(lambda (X)
              (cond
                ((listp X) (reverseAll X))
                (T X)
               )) (reverse L))
)

(defun howManyElems(L)
  (cond
   ((null L) 0)
   (T (+ 1 (howManyElems (cdr L))))
  )
)

(defun makeList(L)
  (cond
   ((null L) nil)
   ((and (listp (car L)) (eql 1 (mod (howManyElems (car L)) 2))) 
    (append (makeList (car L)) (cons (car (car L)) (makeList (cdr L)))))
   (T (makeList (cdr L)))
  )
)

(defun mainMakeList(L)
  (cond
   ((eql 1 (mod (howManyElems L) 2)) (cons (car L) (makeList L)))
   (T (makeList L))
  )
)

(defun atomSum(L)
  (cond
   ((null L) 0)
   ((numberp (car L)) (+ (car L) (atomSum (cdr L))))
   (T (atomSum (cdr L)))
  )
)