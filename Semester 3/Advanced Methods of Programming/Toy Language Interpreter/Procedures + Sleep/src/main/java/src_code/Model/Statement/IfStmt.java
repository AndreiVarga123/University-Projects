package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.ADT.MyIStack;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.BoolType;
import src_code.Model.Type.Type;
import src_code.Model.Value.BoolValue;
import src_code.Model.Value.Value;

public class IfStmt implements Stmt{
    Exp exp;
    Stmt thenStmt;
    Stmt elseStmt;

    public IfStmt(Exp exp, Stmt thenStmt, Stmt elseStmt) {
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<Stmt> stk=state.getExeStack();
        MyIDict<String, Value> tbl=state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();
        Value cond=exp.eval(tbl,heap);
        if(cond.getType().equals(new BoolType()))
        {
            BoolValue boolCond= (BoolValue) cond;
            if(boolCond.getVal())
                stk.push(thenStmt);
            else
                stk.push(elseStmt);
        }
        else
            throw new MyException("Conditional expression is not a boolean");

        return null;
    }

    @Override
    public String toString() {
        return "if("+exp.toString()+") then "+thenStmt.toString()+" else "+elseStmt.toString();
    }

    @Override
    public Stmt deepCopy() {
        return new IfStmt(exp.deepCopy(),thenStmt.deepCopy(),elseStmt.deepCopy());
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typecheck(typeEnv);
        if(typeExp.equals(new BoolType())){
            thenStmt.typecheck(typeEnv);
            elseStmt.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("The condition of the if statement is not a boolean");
    }
}
