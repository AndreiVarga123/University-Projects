package src_code.Model.Statement;


import com.example.test1.Triple;
import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

import java.util.ArrayList;

public class ReleaseStmt implements Stmt{
    String varName;

    @Override
    public String toString() {
        return "release("+varName+")";
    }

    public ReleaseStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyITable<Integer, Triple<Integer, ArrayList<Integer>, Integer>> semaphoreTable = prgState.getSemaphoreTable();
        Value val = symTable.lookup(varName);

        if(val==null)
            throw new MyException("release - var not defined");
        if(!val.getType().equals(new IntType()))
            throw new MyException("release - var not int");
        Integer foundIndex = ((IntValue) val).getVal();

        if(!semaphoreTable.isDefined(foundIndex))
            throw new MyException("release - var not defined in semaphore table");

        Triple<Integer, ArrayList<Integer>, Integer> triple = semaphoreTable.lookup(foundIndex);
        if (triple.getSecond().contains(prgState.getId()))
            triple.getSecond().remove(triple.getSecond().indexOf(prgState.getId()));

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new ReleaseStmt(varName);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typecheck - release");
        return typeEnv;
    }
}
