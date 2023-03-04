package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.StringType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class NewLockStmt implements Stmt{
    String varName;

    public NewLockStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "newLock(" + varName +")";
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyITable<Integer, Integer> lockTable = prgState.getLockTable();
        Integer newFreeLocation = lockTable.add(-1);
        if (symTable.lookup(varName) == null){
            symTable.add(varName, new IntValue(newFreeLocation));
        }
        else{
            symTable.update(varName, new IntValue(newFreeLocation));
        }
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new NewLockStmt(varName);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!typeEnv.isDefined(varName))
            typeEnv.add(varName,new IntType());

        if (!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typeCheck - newLock - var not int");
        return typeEnv;
    }
}
