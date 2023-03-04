package src_code.Model.Statement;

import javafx.util.Pair;
import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyHeap;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

import java.util.ArrayList;

public class NewBarrierStmt implements Stmt{
    String varName;
    Exp exp;

    public NewBarrierStmt(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "newBarrier("+varName+","+exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyIHeap<Integer,Value> memHeap = prgState.getMemHeap();

        Value val = exp.eval(symTable,memHeap);
        if(!val.getType().equals(new IntType()))
            throw new MyException("not the right type my dude");
        IntValue number = (IntValue) val;

        int newFreeLocation = prgState.getBarrierTable().add(new Pair<>(number.getVal(), new ArrayList<>()));

        if(symTable.lookup(varName)!=null){
            prgState.getSymTable().update(varName,new IntValue(newFreeLocation));
        }
        else{
            prgState.getSymTable().add(varName,new IntValue(newFreeLocation));
        }


        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new NewBarrierStmt(varName,exp);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!exp.typecheck(typeEnv).equals(new IntType()))
            throw new MyException("typecheck - newBarrier - exp is not int");
        if(typeEnv.lookup(varName)==null)
            typeEnv.add(varName, new IntType());
        return typeEnv;
    }
}
