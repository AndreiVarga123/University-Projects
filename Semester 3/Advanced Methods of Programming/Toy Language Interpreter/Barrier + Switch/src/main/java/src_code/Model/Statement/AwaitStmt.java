package src_code.Model.Statement;


import javafx.util.Pair;
import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

import java.util.ArrayList;

public class AwaitStmt implements Stmt{
    String varName;

    public AwaitStmt(String varName) {
        this.varName = varName;
    }


    @Override
    public String toString() {
        return "await("+varName+")";
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {

        Value foundIndex = prgState.getSymTable().lookup(varName);
        if(foundIndex==null){
            throw new MyException("not in sims 4 today");
        }
        IntValue value = (IntValue) foundIndex;

        if(!prgState.getBarrierTable().isDefined(value.getVal()))
            throw new MyException("the barrier is the error you made");

        Pair<Integer, ArrayList<Integer>> pair = prgState.getBarrierTable().lookup(value.getVal());

        if(pair.getKey()>pair.getValue().size()){
            if(pair.getValue().contains(prgState.getId()))
                prgState.getExeStack().push(new AwaitStmt(varName));
            else{
                pair.getValue().add(prgState.getId());
                prgState.getExeStack().push(new AwaitStmt(varName));
            }
        }
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new AwaitStmt(varName);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typecheck - await - var is not int");
        return typeEnv;
    }
}
