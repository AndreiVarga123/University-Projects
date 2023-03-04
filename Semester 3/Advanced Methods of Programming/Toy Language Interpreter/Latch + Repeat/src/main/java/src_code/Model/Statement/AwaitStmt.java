package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class AwaitStmt implements Stmt{
    String varName;

    @Override
    public String toString() {
        return "await("+varName+")";
    }

    public AwaitStmt(String varName) {
        this.varName = varName;
    }


    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyITable<Integer,Integer> latchTable = prgState.getLatchTable();

        Value val = symTable.lookup(varName);
        if(val==null)
            throw new MyException("await - var not defined");

        if(!val.getType().equals(new IntType()))
            throw new MyException("await - var not int");

        IntValue foundIndex = (IntValue) val;

        if (!latchTable.isDefined(foundIndex.getVal()))
            throw new MyException("await - foundIndex not in latchTable");

        if(latchTable.lookup(foundIndex.getVal())!=0){
            prgState.getExeStack().push(new AwaitStmt(varName));
        }


        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new AwaitStmt(varName);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typecheck - await - var not int");
        return typeEnv;
    }
}
