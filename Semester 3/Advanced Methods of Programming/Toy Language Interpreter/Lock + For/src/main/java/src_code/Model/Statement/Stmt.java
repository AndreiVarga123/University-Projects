package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;

public interface Stmt {
    PrgState execute(PrgState state) throws MyException;
    Stmt deepCopy();
    MyIDict<String, Type> typecheck(MyIDict<String,Type> typeEnv) throws MyException;
}
