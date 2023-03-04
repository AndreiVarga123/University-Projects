package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.*;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;

import java.util.Stack;

public class ForkStmt implements Stmt{
    Stmt stmt;

    public ForkStmt(Stmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        PrgState pr = new PrgState(new MyStack<>(new Stack<>()), state.getSymTableStack().deepCopy(), state.getOut(), state.getFileTable(), state.getMemHeap(),state.getProcTable(), stmt);
        return pr;
    }

    @Override
    public Stmt deepCopy() {
        return new ForkStmt(stmt);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";


    }
}
