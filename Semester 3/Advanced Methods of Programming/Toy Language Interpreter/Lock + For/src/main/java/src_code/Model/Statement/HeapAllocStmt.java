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

public class HeapAllocStmt implements Stmt{
    String var_name;
    Exp exp;

    public HeapAllocStmt(String var_name, Exp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();

        if(!tbl.isDefined(var_name))
            throw new MyException("Variable " + var_name + " not declared");

        if(!(tbl.lookup(var_name).getType() instanceof RefType))
            throw new MyException("Variable "+ var_name + " is not of type ref");

        Value val = exp.eval(tbl,heap);
        RefValue var_val = (RefValue) tbl.lookup(var_name);

        if(!var_val.getLocationType().equals(val.getType()))
            throw new MyException("Location type of "+ var_name + " is not the same as the expression");

        int key = heap.add(val);
        tbl.update(var_name,new RefValue(key,val.getType()));
        return null;
    }

    @Override
    public String toString() {
        return "new("+var_name+","+exp.toString()+")";}

    @Override
    public Stmt deepCopy() {
        return new HeapAllocStmt(var_name,exp);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp))){
            return typeEnv;
        }
        else
            throw new MyException("New stmt: right hand side and left hand side have different types");
    }
}
