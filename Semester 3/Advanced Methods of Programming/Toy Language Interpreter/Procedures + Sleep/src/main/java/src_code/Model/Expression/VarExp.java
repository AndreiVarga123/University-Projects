package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException {
        if(tbl.isDefined(id))
            return tbl.lookup(id);
        else
            throw new MyException("Variable "+id+" is not defined");
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}
