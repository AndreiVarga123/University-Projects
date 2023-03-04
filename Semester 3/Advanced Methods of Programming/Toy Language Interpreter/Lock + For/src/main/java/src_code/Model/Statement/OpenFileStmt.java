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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStmt implements Stmt{
    Exp exp;

    public OpenFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<StringValue, BufferedReader> ftbl = state.getFileTable();
        MyIDict<String, Value> stbl = state.getSymTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();
        Value val =exp.eval(stbl,heap);
        if(!val.getType().equals(new StringType()))
            throw new MyException("Expression "+exp.toString()+" is not of type string");
        StringValue sval = (StringValue) val;
        if(ftbl.lookup(sval) != null)
            throw new MyException("Filepath " + sval + " already exists in file table");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sval.getVal()));
            ftbl.add(sval,reader);
        } catch (FileNotFoundException e) {
            throw new MyException("Error executing openFile operation");
        }
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new OpenFileStmt(exp);
    }

    @Override
    public String toString() {
        return "openFile("+exp.toString()+')';
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
