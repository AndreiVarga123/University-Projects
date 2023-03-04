package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIStack;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.StringType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class LockStmt implements Stmt{

    String varName;

    @Override
    public String toString() {
        return "lock(" + varName +")";
    }

    public LockStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyITable<Integer,Integer> lockTable = prgState.getLockTable();
        MyIStack<Stmt> exeStack = prgState.getExeStack();

        if(symTable.lookup(varName)==null)
            throw new MyException("variable isn't in symTable");
        if (!(symTable.lookup(varName) instanceof IntValue))
            throw new MyException("variable isn't of int value");
        IntValue foundIndex = (IntValue) symTable.lookup(varName);

        if (!lockTable.isDefined(foundIndex.getVal()))
            throw new MyException("variable isn't in lockTable");

        if (lockTable.lookup(foundIndex.getVal())==-1)
            lockTable.update(foundIndex.getVal(), prgState.getId());
        else
            exeStack.push(new LockStmt(varName));

        return null;
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!typeEnv.lookup(varName).equals(new IntType())){
                throw new MyException("typecheck - lock - var is not int");
        }
        return typeEnv;
    }

    @Override
    public Stmt deepCopy() {
        return new LockStmt(varName);
    }
}
