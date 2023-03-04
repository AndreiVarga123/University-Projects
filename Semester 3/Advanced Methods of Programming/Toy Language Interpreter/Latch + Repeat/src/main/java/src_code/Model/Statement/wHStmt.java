package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.RefType;
import src_code.Model.Type.Type;
import src_code.Model.Value.RefValue;
import src_code.Model.Value.Value;

public class wHStmt implements Stmt{
    String var_name;
    Exp exp;

    public wHStmt(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();
        if(!tbl.isDefined(var_name))
            throw new MyException("Variable "+var_name +" not declared");
        if(!(tbl.lookup(var_name).getType() instanceof RefType))
            throw new MyException("Variable "+var_name +" is not refType");
        RefValue rval = (RefValue) tbl.lookup(var_name);
        if(!heap.isDefined(rval.getAddress()))
            throw new MyException("Adrres associated with " + var_name+" does not exist in heap");

        Value val = exp.eval(tbl,heap);
        if(!rval.getLocationType().equals(val.getType()))
            throw new MyException("Location type of "+ var_name + " is not the same as the expression");

        heap.update(rval.getAddress(),val);
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new wHStmt(var_name,exp);
    }

    @Override
    public String toString() {
        return "wH("+var_name+","+exp.toString()+")";
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typExp = exp.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typExp))){
            return typeEnv;
        }
        else
            throw new MyException("wH stmt: right hand side and left hand side have different types");

    }
}
