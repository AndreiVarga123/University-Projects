package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

public class ValueExp implements Exp{
    Value val;

    public ValueExp(Value val) {
        this.val = val;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException {
        return val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(val.deepCopy());
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return val.getType();
    }
}
