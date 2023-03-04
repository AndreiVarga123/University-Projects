package src_code.Model.Statement;


import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyHeap;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.Value;

public class NewLatchStmt implements Stmt{
    String varName;
    Exp exp;


    public NewLatchStmt(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }


    @Override
    public String toString() {
        return "newLatch("+varName+","+exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDict<String, Value> symTable = prgState.getSymTable();
        MyIHeap<Integer,Value> memHeap = prgState.getMemHeap();
        MyITable<Integer,Integer> latchTable = prgState.getLatchTable();

        Value val = exp.eval(symTable,memHeap);
        if(!val.getType().equals(new IntType()))
            throw new MyException("new latch - exp not int");

        IntValue intValue = (IntValue) val;

        Integer newFreeLocation = latchTable.add(intValue.getVal());

        if(symTable.lookup(varName)==null){
            symTable.add(varName, new IntValue(newFreeLocation));
        }
        else{
            symTable.update(varName,new IntValue(newFreeLocation));
        }
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new NewLatchStmt(varName,exp);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        if (!exp.typecheck(typeEnv).equals(new IntType()))
            throw new MyException("typecheck - new latch - exp not int");
        if (typeEnv.lookup(varName)==null)
            typeEnv.add(varName, new IntType());
        if (!typeEnv.lookup(varName).equals(new IntType()))
            throw new MyException("typechek - new latch - var not int");
        return typeEnv;
    }
}
