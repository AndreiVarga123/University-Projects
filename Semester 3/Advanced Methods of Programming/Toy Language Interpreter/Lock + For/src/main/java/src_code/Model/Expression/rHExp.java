 package src_code.Model.Expression;

 import src_code.Exception.MyException;
 import src_code.Model.ProgramState.ADT.MyIDict;
 import src_code.Model.ProgramState.ADT.MyIHeap;
 import src_code.Model.Type.RefType;
 import src_code.Model.Type.Type;
 import src_code.Model.Value.RefValue;
 import src_code.Model.Value.Value;

public class rHExp implements Exp{
    Exp exp;

    public rHExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException {
        Value val = exp.eval(tbl,heap);
        if(!(val instanceof RefValue))
            throw new MyException("Expression "+ exp + " is not RefValue");

        RefValue rval = (RefValue) val;
        if(!heap.isDefined(rval.getAddress()))
            throw new MyException("Value "+rval.toString()+" not defined in memHeap");

        return heap.lookup(rval.getAddress());
    }

    @Override
    public Exp deepCopy() {
        return null;
    }

    @Override
    public String toString() {
        return "rH("+exp+")";
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type t = exp.typecheck(typeEnv);
        if(t instanceof RefType){
            RefType refT = (RefType) t;
            return refT.getInner();
        }
        else
            throw new MyException("The rH argument is not a RefType");
    }
}
