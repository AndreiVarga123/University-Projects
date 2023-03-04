package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.Expression.RelationalExp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;

public class SwitchStmt implements Stmt {

    Exp exp, exp1, exp2;
    Stmt stmt1, stmt2, stmt3;

    @Override
    public String toString() {
        return "switch("+exp.toString()+") (case "+exp1.toString()+": "+stmt1.toString()+") (case "+exp2.toString()+": "+stmt2.toString()+") (default: "+stmt3.toString();
    }

    public SwitchStmt(Exp exp, Exp exp1, Stmt stmt1, Exp exp2, Stmt stmt2, Stmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        Stmt toPush = new IfStmt(new RelationalExp(5,exp,exp1),stmt1, new IfStmt(new RelationalExp(5, exp, exp2), stmt2,stmt3));
        prgState.getExeStack().push(toPush);
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new SwitchStmt(exp,exp1,stmt1,exp2,stmt2,stmt3);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!exp.typecheck(typeEnv).equals(exp1.typecheck(typeEnv)))
            throw new MyException("exp and exp1 aren't the same type");
        if (!exp.typecheck(typeEnv).equals(exp2.typecheck(typeEnv)))
            throw new MyException("exp and exp2 aren't the same type");
        return stmt3.typecheck(stmt2.typecheck(stmt1.typecheck(typeEnv)));
    }
}
