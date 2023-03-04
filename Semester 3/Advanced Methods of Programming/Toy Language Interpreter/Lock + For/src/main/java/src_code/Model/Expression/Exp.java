package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

public interface Exp {
    Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException;
    Exp deepCopy();
    Type typecheck(MyIDict<String,Type> typeEnv) throws MyException;
}
