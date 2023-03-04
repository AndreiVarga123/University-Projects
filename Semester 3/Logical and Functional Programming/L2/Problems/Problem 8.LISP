(defun nrNodesOnLvl(l lvl curr)
  (cond
   ((and (atom l) (eql lvl curr)) 1)
   ((atom l) 0)
   (T (apply #'+ (mapcar #'(lambda (l) (nrNodesOnLvl l lvl (+ 1 curr))) l)))
  )
)

(defun main(l lvl)
  (nrNodesOnLvl l lvl -1)
)