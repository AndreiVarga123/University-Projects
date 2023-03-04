(defun nodesOnLvl(L lvl clvl)
  (cond 
   ((null L) 0)
   ((eql clvl lvl) 1)
   ((+ (nodesOnLvl (cadr L) lvl (+ 1 clvl)) (nodesOnLvl (caddr L) lvl (+ 1 clvl))))
  )
)

(defun makeList(L lvl)
  (cond
   ((null L) nil)
   ((eql 0 (nodesOnLvl L lvl 0)) nil)
   ((cons (nodesOnLvl L lvl 0) (makeList L (+ 1 lvl))))
  )
)

(defun myMax(L M)
  (cond
   ((null L) M)
   (T (max (car L) (myMax (cdr L) M)))
  )
)

(defun getMaxLvl(L M P)
 (cond
  ((null L) -1)
  ((eql M (car L)) P)
  (T (getMaxLvl (cdr L) M (+ 1 P)))
 )
)

(defun getList(L lvl clvl)
  (cond
   ((null L) nil)
   ((eql clvl lvl) (list (car L)))
   (T (append (getList (cadr L) lvl (+ 1 clvl)) (getList (caddr L) lvl (+ 1 clvl))))
  )
)

(defun main(L)
  (getList L (getMaxLvl (makeList L 0) (myMax (makeList L 0) -1) 0) 0)
)