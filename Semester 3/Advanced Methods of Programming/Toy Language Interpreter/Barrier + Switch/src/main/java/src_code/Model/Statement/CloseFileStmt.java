package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.StringType;
import src_code.Model.Type.Type;
import src_code.Model.Value.StringValue;
import src_code.Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStmt implements Stmt{
    Exp exp;

    public CloseFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<StringValue, BufferedReader> ftbl = state.getFileTable();
        MyIDict<String,Value> stbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();

        Value val = exp.eval(stbl,heap);
        if(!val.getType().equals(new StringType()))
            throw new MyException("Expression "+exp.toString()+" is not of type string");
        StringValue strVal = (StringValue) val;
        BufferedReader reader = ftbl.lookup(strVal);
        if(reader == null)
            throw new MyException("File path " + strVal.getVal() + " not in file table");
        try {
            reader.close();
            ftbl.remove(strVal);
        } catch (IOException e) {
            throw new MyException("Error executing closeFile operation");
        }

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new CloseFileStmt(exp);
    }

    @Override
    public String toString() {
        return "closeFile("+exp.toString()+")";
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typecheck(typeEnv);
        if(typeExp.equals(new StringType()))
        {
            return typeEnv;
        }
        else
            throw new MyException("Open file stmt: operand is not a string");
    }
}
