package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.BoolType;
import src_code.Model.Type.Type;
import src_code.Model.Value.BoolValue;
import src_code.Model.Value.Value;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    public LogicExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException {
        Value v1,v2;
        v1=e1.eval(tbl,heap);
        if(v1.getType().equals(new BoolType()))
        {
            v2=e2.eval(tbl,heap);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean l1,l2;
                l1 = b1.getVal();
                l2 = b2.getVal();
                if(op==1) return new BoolValue(l1&&l2);
                if(op==2) return new BoolValue(l1||l2);
            }
            else
                throw new MyException("Second operand is not a boolean");
        }
        else
            throw new MyException("First operand is not a boolean");
        return null;
    }

    @Override
    public String toString() {
        if(op==1) return e1.toString()+"&&"+e2.toString();
        if(op==2) return e1.toString()+"||"+e2.toString();
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(e1.deepCopy(),e2.deepCopy(),op);
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if(t1.equals(new BoolType())){
            if(t2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("Second operand is not a boolean");
        }
        else
            throw new MyException("First operand is not a boolean");
    }
}
