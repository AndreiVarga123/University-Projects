package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;

public class SleepStmt implements Stmt{

    int number;

    @Override
    public String toString() {
        return "sleep("+number+")";
    }

    public SleepStmt(int number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {

        if (number!=0){
            prgState.getExeStack().push(new SleepStmt(number-1));
        }
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new SleepStmt(number);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
