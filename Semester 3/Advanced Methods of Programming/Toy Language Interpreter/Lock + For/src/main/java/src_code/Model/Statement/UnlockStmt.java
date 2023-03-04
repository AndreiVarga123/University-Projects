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

public class UnlockStmt implements Stmt{
    String varName;

    public UnlockStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String,Value> symTable = prgState.getSymTable();
        MyITable<Integer,Integer> lockTable = prgState.getLockTable();
        MyIStack<Stmt> exeStack = prgState.getExeStack();

        if(symTable.lookup(varName)==null)
            throw new MyException("variable isn't in symTable");
        if (!(symTable.lookup(varName) instanceof IntValue))
            throw new MyException("variable isn't of int value");
        IntValue foundIndex = (IntValue) symTable.lookup(varName);

        if (lockTable.isDefined(foundIndex.getVal())) {
            if(lockTable.lookup(foundIndex.getVal())== prgState.getId())
                lockTable.update(foundIndex.getVal(),-1);
        }
        return null;
    }

    @Override
    public String toString() {
        return "unlock(" +varName+")";
    }

    @Override
    public Stmt deepCopy() {
        return new UnlockStmt(varName);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!typeEnv.lookup(varName).equals(new IntType())){
            throw new MyException("typecheck - unlock - var is not int");
        }
        return typeEnv;
    }
}
