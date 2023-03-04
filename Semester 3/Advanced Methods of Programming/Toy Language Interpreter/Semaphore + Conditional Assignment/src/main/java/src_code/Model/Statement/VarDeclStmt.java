package src_code.Model.Statement;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

public class VarDeclStmt implements Stmt{
    String name;
    Type type;

    public VarDeclStmt(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDict<String, Value> tbl = state.getSymTable();
        if(!tbl.isDefined(name))
        {
            tbl.add(name,type.defaultValue());
        }
        else
            throw new MyException("Variable is already declared");

        return null;
    }

    @Override
    public String toString() {
        return type.toString()+" "+name;
    }

    @Override
    public Stmt deepCopy() {
        return new VarDeclStmt(name,type.deepCopy());
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        typeEnv.add(name,type);
        return typeEnv;
    }
}
