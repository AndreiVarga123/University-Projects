package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyIHeap;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.IntType;
import src_code.Model.Type.StringType;
import src_code.Model.Type.Type;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.StringValue;
import src_code.Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements Stmt{
    Exp exp;
    String var_name;

    public ReadFileStmt(Exp exp, String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> stbl = state.getSymTable();
        MyIDict<StringValue, BufferedReader> ftbl = state.getFileTable();
        MyIHeap<Integer,Value> heap = state.getMemHeap();

        Value varVal = stbl.lookup(var_name);
        if(varVal == null)
            throw new MyException("Variable " +var_name + " was not declared before");
        if(!varVal.getType().equals(new IntType()))
            throw new MyException("Variable "+var_name +" is not of type int");
        Value expVal = exp.eval(stbl,heap);
        if(!expVal.getType().equals(new StringType()))
            throw new MyException("Expression "+exp.toString()+" is not of type string");

        StringValue expStrVal = (StringValue) expVal;

        BufferedReader reader = ftbl.lookup(expStrVal);
        if(reader == null)
            throw new MyException("File path " + expStrVal.getVal() + " not in file table");

        try {
            int result;
            String line = reader.readLine();
            if(line == null) result=0;
            else result = Integer.parseInt(line);
            stbl.update(var_name,new IntValue(result));
        } catch (IOException e) {
            throw new MyException("Error executing readFile operation");
        }

        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new ReadFileStmt(exp,var_name);
    }

    @Override
    public String toString() {
        return "readFile("+exp.toString()+","+var_name+")";
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typExp = exp.typecheck(typeEnv);

        if(typeVar.equals(new IntType())){
            if(typExp.equals(new StringType()))
            {
                return typeEnv;
            }
            else
                throw new MyException("Read file stmt: expression does not evaluate to a string");
        }
        else
            throw new MyException("Read file stmt: values is not of type int");
    }
}