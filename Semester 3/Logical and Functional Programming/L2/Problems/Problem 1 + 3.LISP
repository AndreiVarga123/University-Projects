(defun isMember(e l)
  (cond
   ((eql e l) T)
   ((atom l) NIL)
   (T (some #'identity (mapcar #'(lambda (l) (isMember e l)) l)))
  )
)