package src_code.Model.Expression;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.Type.BoolType;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.BoolValue;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class RelationalExp implements Exp{
    int op; /// < is 1; > is 2; <= is 3; >= is 4; == is 5; != is 6
    Exp e1;
    Exp e2;

    public RelationalExp(int op, Exp e1, Exp e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
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
                if (op==1) return new BoolValue(n1<n2);
                if (op ==2) return new BoolValue(n1>n2);
                if(op==3) return new BoolValue(n1<=n2);
                if(op==4) return new BoolValue(n1>=n2);
                if(op==5) return new BoolValue(n1==n2);
                if(op==6) return new BoolValue(n1!=n2);
            }
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(op,e1,e2);
    }

    @Override
    public String toString() {
        if (op==1) return e1.toString()+"<"+e2.toString();
        if (op==2) return e1.toString()+">"+e2.toString();
        if (op==3) return e1.toString()+"<="+e2.toString();
        if (op==4) return e1.toString()+">="+e2.toString();
        if (op==5) return e1.toString()+"=="+e2.toString();
        if (op==6) return e1.toString()+"!="+e2.toString();
        return null;
    }

    @Override
    public Type typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type t1,t2;
        t1 = e1.typecheck(typeEnv);
        t2 = e2.typecheck(typeEnv);
        if(t1.equals(new IntType())){
            if(t2.equals(new IntType()))
                return new BoolType();
            else
                throw new MyException("Second operand is not an integer");
        }
        else
            throw new MyException("First operand is not an integer");
    }
}
