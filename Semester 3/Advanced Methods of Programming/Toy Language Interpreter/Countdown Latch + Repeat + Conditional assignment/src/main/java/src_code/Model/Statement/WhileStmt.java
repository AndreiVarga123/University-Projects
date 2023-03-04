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

public class WhileStmt implements Stmt{
    Exp exp;
    Stmt stmt;

    public WhileStmt(Exp exp, Stmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();
        MyIStack<Stmt> stk=state.getExeStack();

        Value val = exp.eval(tbl,heap);
        if(!(val instanceof BoolValue))
            throw new MyException("Expression "+exp.toString()+" is not boolean");

        if(((BoolValue) val).getVal()) {
            stk.push(new WhileStmt(exp,stmt));
            stk.push(stmt);
        }
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return null;
    }

    @Override
    public String toString() {
        return "while("+exp+")";
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typecheck(typeEnv);
        if(typeExp.equals(new BoolType())){
            stmt.typecheck(typeEnv);
            return typeEnv;
        }
        else
            throw new MyException("While condition is not boolean");
    }
}
