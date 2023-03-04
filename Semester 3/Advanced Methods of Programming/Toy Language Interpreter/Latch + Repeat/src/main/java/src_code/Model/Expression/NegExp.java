package src_code.Model.Expression;


import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.BoolType;
import src_code.Model.Type.Type;
import src_code.Model.Value.BoolValue;
import src_code.Model.Value.Value;

public class NegExp implements Exp{
    Exp exp;

    public NegExp(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "!"+exp.toString();
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value val = exp.eval(tbl,heap);
        if(!val.getType().equals(new BoolType()))
            throw new MyException("neg - exp not bool");

        BoolValue value = (BoolValue) val;
        return new BoolValue(!value.getVal());
    }

    @Override
    public Exp deepCopy() {
        return new NegExp(exp);
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type type = exp.typecheck(typeEnv);
        if(!type.equals(new BoolType()))
            throw new MyException("neg -typecheck - exp not boool");
        return new BoolType();
    }
}
