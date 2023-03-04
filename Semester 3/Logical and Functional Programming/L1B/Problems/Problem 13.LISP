(defun memberDeep(X L)
  (cond
   ((null L) nil)
   ((eql (car L) X) T)
   ((atom (car L)) (memberDeep X (cdr L)))
   (T (or (memberDeep X (car L)) (memberDeep X (cdr L))))
  )
)

(defun findPath(X L)
  (cond
   ((eql (car L) X) (list X)) 
   ((memberDeep X (cadr L)) (cons (car L) (findPath X (cadr L))))
   ((memberDeep X (caddr L)) (cons (car L) (findPath X (caddr L))))
   (T nil)
  )
)