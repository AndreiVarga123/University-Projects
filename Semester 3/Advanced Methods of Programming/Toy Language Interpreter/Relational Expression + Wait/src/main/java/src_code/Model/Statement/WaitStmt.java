package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.Expression.ValueExp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;

public class WaitStmt implements Stmt{

    IntValue number;

    public WaitStmt(IntValue number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        if (number.getVal()>0)
        {
            prgState.getExeStack().push(new CompStmt(new PrintStmt(new ValueExp(number)), new WaitStmt(new IntValue(number.getVal()-1))));
        }
        return null;
    }

    @Override
    public String toString() {
        return "wait("+number.getVal()+")";
    }

    @Override
    public Stmt deepCopy() {
        return new WaitStmt(number);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
