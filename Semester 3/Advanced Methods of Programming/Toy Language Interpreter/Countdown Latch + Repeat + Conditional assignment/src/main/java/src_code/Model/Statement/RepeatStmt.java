package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.Expression.NegExp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.BoolType;
import src_code.Model.Type.Type;

public class RepeatStmt implements Stmt{
    Stmt stmt;
    Exp exp;

    @Override
    public String toString() {
        return "repeat "+stmt.toString()+" until "+exp.toString();
    }

    public RepeatStmt(Stmt stmt, Exp exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        Stmt toPush = new CompStmt(stmt, new WhileStmt(new NegExp(exp), stmt));
        prgState.getExeStack().push(toPush);
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new RepeatStmt(stmt.deepCopy(),exp.deepCopy());
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!exp.typecheck(typeEnv).equals(new BoolType()))
            throw new MyException("typecheck - repeat");
        return stmt.typecheck(typeEnv);
    }
}
