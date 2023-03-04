package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class ArithExp implements Exp{
    Exp e1;
    Exp e2;
    int op;

    public ArithExp(int op, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDict<String, Value> tbl, MyIHeap<Integer,Value> heap) throws MyException {
        Value v1,v2;
        v1=e1.eval(tbl,heap);
        if(v1.getType().equals(new IntType()))
        {
            v2=e2.eval(tbl,heap);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1= (IntValue) v1;
                IntValue i2= (IntValue) v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op==1)  return new IntValue(n1+n2);
                if (op ==2)  return new IntValue(n1-n2);
                if(op==3)  return new IntValue(n1*n2);
                if(op==4)
                    if(n2!=0) return new IntValue(n1/n2);
                    else throw new MyException("Division by zero");
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
        return null;
    }

    @Override
    public String toString() {
        if (op==1) return e1.toString()+"+"+e2.toString();
        if (op==2) return e1.toString()+"-"+e2.toString();
        if (op==3) return e1.toString()+"*"+e2.toString();
        if (op==4) return e1.toString()+"/"+e2.toString();
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op,e1.deepCopy(),e2.deepCopy());
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if(t1.equals(new IntType())){
            if(t2.equals(new IntType()))
                return new IntType();
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
    }
}
