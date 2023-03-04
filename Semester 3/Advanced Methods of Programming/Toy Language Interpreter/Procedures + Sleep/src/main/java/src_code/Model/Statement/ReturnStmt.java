package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;

public class ReturnStmt implements Stmt{
    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        prgState.getSymTableStack().pop();
        return null;
    }

    @Override
    public String toString() {
        return "return";
    }

    @Override
    public Stmt deepCopy() {
        return new ReturnStmt();
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
