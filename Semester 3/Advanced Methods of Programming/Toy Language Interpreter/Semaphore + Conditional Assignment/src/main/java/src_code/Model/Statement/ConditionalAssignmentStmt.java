package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;

public class ConditionalAssignmentStmt implements Stmt{
    String varName;
    Exp exp1, exp2, exp3;

    public ConditionalAssignmentStmt(String varName, Exp exp1, Exp exp2, Exp exp3) {
        this.varName = varName;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    @Override
    public String toString() {
        return varName+"=("+exp1.toString()+")?"+exp2.toString()+":"+exp3.toString();
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        Stmt toPush = new IfStmt(exp1, new AssignStmt(varName,exp2), new AssignStmt(varName,exp3));
        prgState.getExeStack().push(toPush);

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new ConditionalAssignmentStmt(varName,exp1,exp2,exp3);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
