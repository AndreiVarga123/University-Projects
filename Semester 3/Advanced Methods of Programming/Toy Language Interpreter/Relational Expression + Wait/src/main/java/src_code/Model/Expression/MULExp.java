package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class MULExp implements Exp{
    Exp exp1;
    Exp exp2;

    public MULExp(Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public String toString() {
        return "MUL("+exp1.toString()+","+exp2.toString()+")";
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value val1,val2;
        val1 = exp1.eval(tbl,heap);
        val2 = exp2.eval(tbl,heap);

        if(!val1.getType().equals(new IntType()))
            throw new MyException("exp1 is not an integer");

        if(!val2.getType().equals(new IntType()))
            throw new MyException("exp2 is not an integer");

        IntValue v1 = (IntValue) val1;
        IntValue v2 = (IntValue) val2;

        return new IntValue((v1.getVal()*v2.getVal())-(v1.getVal()+v2.getVal()));
    }

    @Override
    public Exp deepCopy() {
        return new MULExp(exp1,exp2);
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!exp1.typecheck(typeEnv).equals(new IntType()))
            throw new MyException("not of correct type exp1 in MUL");
        if (!exp2.typecheck(typeEnv).equals(new IntType()))
            throw new MyException("not of correct type exp2 in MUL");
        return new IntType();
    }
}
