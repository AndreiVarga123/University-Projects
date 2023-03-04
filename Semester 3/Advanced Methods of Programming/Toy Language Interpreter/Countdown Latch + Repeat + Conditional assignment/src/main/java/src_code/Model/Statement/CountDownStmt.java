package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIList;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class CountDownStmt implements Stmt{
    String varName;

    @Override
    public String toString() {
        return "countDown("+varName+")";
    }

    public CountDownStmt(String varName) {
        this.varName = varName;
    }


    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyITable<Integer,Integer> latchTable = prgState.getLatchTable();
        MyIList<Value> output = prgState.getOut();

        Value val = symTable.lookup(varName);
        if(val==null)
            throw new MyException("countdown - var not defined");

        if(!val.getType().equals(new IntType()))
            throw new MyException("countdown - var not int");

        IntValue foundIndex = (IntValue) val;

        if(latchTable.isDefined(foundIndex.getVal())){
            if (latchTable.lookup(foundIndex.getVal())>0) {
                latchTable.update(foundIndex.getVal(), latchTable.lookup(foundIndex.getVal()) - 1);
            }
            output.add(new IntValue(prgState.getId()));
        }

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new CountDownStmt(varName);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if(!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typecheck - countdown - var not int");
        return typeEnv;
    }
}
