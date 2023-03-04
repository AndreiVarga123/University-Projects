package src_code.Model.Statement;



import javafx.util.Pair;
import src_code.Exception.MyException;
import src_code.Model.Expression.Exp;
import src_code.Model.ProgramState.ADT.MyDict;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.ADT.MyITable;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;
import src_code.Model.Value.Value;

import java.util.ArrayList;
import java.util.HashMap;

public class CallStmt implements Stmt{
    String procName;
    ArrayList<Exp> expressions;

    @Override
    public String toString() {
        return "call "+procName+" ("+expressions.toString()+")";
    }

    public CallStmt(String procName, ArrayList<Exp> expressions) {
        this.procName = procName;
        this.expressions = expressions;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyITable<String, Pair<ArrayList<String>, Stmt>> procTable = prgState.getProcTable();
        MyIDict<String, Value> symTable = prgState.getSymTable();

        if (procTable.lookup(procName)==null)
            throw new MyException("n-am gasit porcul");

        Pair<ArrayList<String>,Stmt> pair = procTable.lookup(procName);
        MyIDict<String, Value> newSymTable = new MyDict<>(new HashMap<>());

        for (int i=0;i< expressions.size();i++){
            Value val = expressions.get(i).eval(symTable, prgState.getMemHeap());
            newSymTable.add(pair.getKey().get(i),val);
        }
        prgState.getSymTableStack().push(newSymTable);

        prgState.getExeStack().push(new ReturnStmt());
        prgState.getExeStack().push(pair.getValue());
        return null;
    }

    @Override
    public Stmt deepCopy() {
        return new CallStmt(procName,expressions);
    }

    @Override
    public MyIDict<String, Type> typecheck(MyIDict<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
