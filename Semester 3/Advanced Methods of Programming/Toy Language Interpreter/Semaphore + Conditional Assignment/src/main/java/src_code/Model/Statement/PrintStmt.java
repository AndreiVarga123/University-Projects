package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.ADT.MyIList;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

public class PrintStmt implements Stmt{
    Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> list = state.getOut();
        MyIDict<String,Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();
        list.add(exp.eval(tbl,heap));
        return null;
    }

    @Override
    public String toString() {
        return "print("+exp.toString()+")";
    }

    @Override
    public Stmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
