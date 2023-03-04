(defun getNthElem(L N P)
 (cond
  ((null L) nil)
  ((eql N P) (car L))
  (T (getNthElem (cdr L) N (+ 1 P)))
 )
)

(defun memberDeep(X L)
  (cond
   ((null L) nil)
   ((eql (car L) X) T)
   ((atom (car L)) (memberDeep X (cdr L)))
   (T (or (memberDeep X (car L)) (memberDeep X (cdr L))))
  )
)

(defun allSl(L)
  (cond
   ((null L) nil)
   ((listp (car L)) (cons (car L) (append (allSl (car L)) (allSl (cdr L)))))
   (T (allSl (cdr L)))
  )
)

(defun mainSl(L)
  (allSl (list L))
)

(defun makeSet(L C)
  (cond
   ((null L) C)
   ((not (memberDeep (car L) C)) (makeSet (cdr L) (cons (car L) C)))
   (T (makeSet (cdr L) C))
  )
)

(defun mainMakeSet(L)
  (makeSet L NIL)
)