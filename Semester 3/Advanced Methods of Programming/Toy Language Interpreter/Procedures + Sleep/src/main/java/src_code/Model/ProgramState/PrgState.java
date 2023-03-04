package src_code.Model.ProgramState;

import javafx.util.Pair;
import src_code.Exception.MyException;
import src_code.Model.Expression.ArithExp;
import src_code.Model.Expression.VarExp;
import src_code.Model.ProgramState.ADT.*;
import src_code.Model.Statement.*;
import src_code.Model.Type.IntType;
import src_code.Model.Value.StringValue;
import src_code.Model.Value.Value;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;


public class PrgState {
    MyIStack<Stmt> exeStack;
    MyIStack<MyIDict<String, Value>> symTable;
    MyIList<Value> out;
    MyIDict<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer,Value> memHeap;
    MyITable<String, Pair<ArrayList<String>,Stmt>> procTable;
    Stmt originalProgram;
    int thread_id;
    static int id=0;

    public PrgState(MyIStack<Stmt> exeStack, MyIStack<MyIDict<String, Value>> symTable, MyIList<Value> out, MyIDict<StringValue, BufferedReader> fileTable,MyIHeap<Integer,Value> memHeap,MyITable<String, Pair<ArrayList<String>,Stmt>> procTable, Stmt program) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.fileTable = fileTable;
        this.memHeap = memHeap;
        this.procTable = procTable;
        this.out = out;
        initProcTable();
        originalProgram = program.deepCopy();
        setId();
        exeStack.push(program);
    }

    synchronized private void setId(){
        thread_id=id;
        id++;
    }

    public int getId(){
        return thread_id;
    }

    public MyITable<String, Pair<ArrayList<String>, Stmt>> getProcTable() {
        return procTable;
    }

    private void initProcTable(){
        ArrayList<String> arr =new ArrayList<>();
        arr.add("a"); arr.add("b");
        Stmt sum = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(
                new AssignStmt("v", new ArithExp(1, new VarExp("a"), new VarExp("b"))), new PrintStmt(new VarExp("v"))
        ));
        Pair<ArrayList<String>, Stmt> pair = new Pair<>(arr,sum);
        procTable.add("sum", pair);

        Stmt product = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(
                new AssignStmt("v", new ArithExp(3, new VarExp("a"), new VarExp("b"))), new PrintStmt(new VarExp("v"))
        ));
        Pair<ArrayList<String>, Stmt> pair2 = new Pair<>(arr,product);
        procTable.add("product", pair2);
    }

    public MyIList<Stmt> getStackAsList(){
        MyIList<Stmt> stack = new MyList<>(new ArrayList<>());
        Stack<Stmt> helper = new Stack<>();
        while(!exeStack.isEmpty()) {
            Stmt crtStmt = exeStack.pop();
            stack.add(crtStmt);
            helper.push(crtStmt);
        }
        while(!helper.isEmpty())
        {
            Stmt crtStmt = helper.pop();
            exeStack.push(crtStmt);
        }

        return stack;
    }
    public MyIStack<Stmt> getExeStack() {
        return exeStack;
    }

    public MyIDict<String, Value> getSymTable() {
        return symTable.peek();
    }

    public MyIStack<MyIDict<String, Value>> getSymTableStack(){
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap<Integer, Value> getMemHeap() {
        return memHeap;
    }

    @Override
    public String toString() {
        StringBuilder myString = new StringBuilder("Thread "+thread_id+"\nExecution stack:\n");
        String initialString;
        String[] strings;

        Stack<Stmt> helper = new Stack<>();
        while(!exeStack.isEmpty()) {
            Stmt crtStmt = exeStack.pop();
            myString.append(crtStmt.toString()).append('\n');
            helper.push(crtStmt);
        }
        while(!helper.isEmpty())
        {
            Stmt crtStmt = helper.pop();
            exeStack.push(crtStmt);
        }

        myString.append("SymTable:\n");
        if(!symTable.isEmpty()) {
            for(Map.Entry<String,Value> r : symTable.peek().getSet())
            {
                myString.append(r.toString().replace("=","->")).append('\n');
            }
        }

        myString.append("Out:\n");
        if(!out.isEmpty()) {
            Iterator<Value> i = out.getIter();
            while (i.hasNext())
            {
                myString.append(i.next().toString()).append('\n');
            }
        }

        myString.append("FileTable:\n");
        if(!fileTable.isEmpty()) {
            for(Map.Entry<StringValue,BufferedReader> r : fileTable.getSet())
            {
                myString.append(r.toString().replace("=","->")).append('\n');
            }
        }

        myString.append("Memory heap:\n");
        if(!memHeap.isEmpty()) {
            for(Map.Entry<Integer,Value> r : memHeap.getSet())
            {
                myString.append(r.toString().replace("=","->")).append('\n');
            }
        }

        return myString.toString();
        ///return "Execution stack: "+exeStack.toString()+"\n"+"Symbols table: "+symTable.toString()+"\n"+"Output: "+out.toString();
    }

    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(!exeStack.isEmpty())
        {
            Stmt crtStmt=exeStack.pop();
            return crtStmt.execute(this);
        }
        else
            throw new MyException("Execution stack is empty");
    }
}
