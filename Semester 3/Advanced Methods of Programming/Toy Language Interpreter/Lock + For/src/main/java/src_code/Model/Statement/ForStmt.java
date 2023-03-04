package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.Expression.RelationalExp;
import src_code.Model.Expression.VarExp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;

public class ForStmt implements Stmt{

    String varName;
    Exp exp1,exp2,exp3;

    Stmt stmt;

    public ForStmt(String varName, Exp exp1, Exp exp2, Exp exp3, Stmt stmt) {
        this.varName = varName;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "for("+varName+"="+exp1.toString()+";"+varName+"<"+exp2.toString()+";"+varName+"="+exp3.toString()+")"+stmt.toString();
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        Stmt forSt = new CompStmt(new AssignStmt(varName,exp1),new WhileStmt(new RelationalExp(1,new VarExp(varName),exp2),new CompStmt(stmt,new AssignStmt(varName,exp3))));

        prgState.getExeStack().push(forSt);

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new ForStmt(varName,exp1,exp2,exp3,stmt);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeExp1 = exp1.typecheck(typeEnv);
        Type typeExp2 = exp2.typecheck(typeEnv);
        Type typeExp3 = exp3.typecheck(typeEnv);
        if(typeExp1.equals(new IntType()) && typeExp2.equals(new IntType()) && typeExp3.equals(new IntType())){
            stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("expressions in for no good");
    }
}
