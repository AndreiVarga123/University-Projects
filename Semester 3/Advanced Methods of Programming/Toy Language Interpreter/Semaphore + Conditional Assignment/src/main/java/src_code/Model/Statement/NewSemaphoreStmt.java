package src_code.Model.Statement;



import com.example.test1.Triple;
import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

import java.util.ArrayList;

public class NewSemaphoreStmt implements Stmt{
    String varName;
    Exp exp1,exp2;

    @Override
    public String toString() {
        return "newSemaphore("+varName+","+exp1.toString()+","+exp2.toString()+")";
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyIHeap<Integer,Value> memHeap = prgState.getMemHeap();
        MyITable<Integer, Triple<Integer, ArrayList<Integer>, Integer>> semaphoreTable = prgState.getSemaphoreTable();

        Value val1 = exp1.eval(symTable,memHeap);
        if(!val1.getType().equals(new IntType()))
            throw new MyException("newSemaphore - exp1 not int");
        Value val2 = exp2.eval(symTable,memHeap);
        if(!val2.getType().equals(new IntType()))
            throw new MyException("newSemaphore - exp2 not int");
        IntValue iv1 = (IntValue) val1;
        IntValue iv2 = (IntValue) val2;

        Integer newFreeLocation = semaphoreTable.add(new Triple<>(iv1.getVal(), new ArrayList<>(), iv2.getVal()));

        if (symTable.lookup(varName)==null)
            throw new MyException("newSemaphore - var not defined");
        symTable.update(varName,new IntValue(newFreeLocation));

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new NewSemaphoreStmt(varName,exp1,exp2);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookup(varName)==null)
            throw new MyException("typecheck - newSemaphore - varname not defined");
        if (!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typecheck - newSemaphore - varname not int");
        if (!exp1.typecheck(typeEnv).equals(new IntType()))
            throw new MyException("typecheck - newSemaphore - exp1 not int");
        if(!exp2.typecheck(typeEnv).equals(new IntType()))
            throw new MyException("typecheck - newSemaphore - exp2 not int");
        return typeEnv;
    }

    public NewSemaphoreStmt(String varName, Exp exp1, Exp exp2) {
        this.varName = varName;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
}
