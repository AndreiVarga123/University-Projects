package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

public class AssignStmt implements Stmt{
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();
        if (tbl.isDefined(id))
        {
            Value val=exp.eval(tbl,heap);
            Type typeId=tbl.lookup(id).getType();
            if (val.getType().equals(typeId))
                tbl.update(id, val);
            else
                throw new MyException("Declared type of variable"+id+" and type of  the assigned expression do not match");
        }
        else
            throw new MyException("The used variable " +id + " was not declared before");
        return null;
    }

    @Override
    public String toString() {
        return id+"="+exp.toString();
    }

    @Override
    public Stmt deepCopy() {
        return new AssignStmt(id,exp);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(id);
        Type typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(typeExp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have diffrent types");
    }
}
