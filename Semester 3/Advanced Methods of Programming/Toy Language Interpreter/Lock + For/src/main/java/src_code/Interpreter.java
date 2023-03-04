package src_code;

import src_code.Controller.Controller;
import src_code.Model.Expression.*;
import src_code.Model.ProgramState.ADT.*;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Statement.*;
import src_code.Model.Type.BoolType;
import src_code.Model.Type.IntType;
import src_code.Model.Type.RefType;
import src_code.Model.Type.StringType;
import src_code.Model.Value.BoolValue;
import src_code.Model.Value.IntValue;
import src_code.Model.Value.StringValue;
import src_code.Repo.IRepo;
import src_code.Repo.Repo;
import src_code.View.Command.ExitCommand;
import src_code.View.Command.RunExample;
import src_code.View.TextMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Interpreter {
    public static void main(String[] args){

        /*
        System.out.println("Name of text file:");
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        IRepo repo = new Repo(new ArrayList<>(),file);
        Controller controller = new Controller(repo);
        View view = new View(controller);
        view.start();
        */



        Stmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState pr1 = new PrgState(new MyStack<>(new Stack<>()), new MyDict<>(new HashMap<>()), new MyList<>(new ArrayList<>()), new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()), ex1);
        ArrayList<PrgState> list1 = new ArrayList<>();
        list1.add(pr1);
        IRepo repo1 = new Repo(list1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        Stmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState pr2 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()),ex2);
        ArrayList<PrgState> list2 = new ArrayList<>();
        list2.add(pr2);
        IRepo repo2 = new Repo(list2,"log2.txt");
        Controller ctr2 = new Controller(repo2);

        Stmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState pr3 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()),ex3);
        ArrayList<PrgState> list3 = new ArrayList<>();
        list3.add(pr3);
        IRepo repo3 = new Repo(list3,"log3.txt");
        Controller ctr3 = new Controller(repo3);

        Stmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),new CompStmt(new OpenFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc", new IntType()),new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
        PrgState pr4 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()),ex4);
        ArrayList<PrgState> list4 = new ArrayList<>();
        list4.add(pr4);
        IRepo repo4 = new Repo(list4,"log4.txt");
        Controller ctr4 = new Controller(repo4);

        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),new PrintStmt(new ArithExp(1,new rHExp(new rHExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),new CompStmt(new wHStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new ArithExp(1,new rHExp(new VarExp("v")),new ValueExp(new IntValue(5))))))));
        Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationalExp(2,new VarExp("v"),new ValueExp(new IntValue(0))),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),new PrintStmt(new VarExp("v")))));
        PrgState pr5 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()),ex5);
        ArrayList<PrgState> list5 = new ArrayList<>();
        list5.add(pr5);
        IRepo repo5 = new Repo(list5,"log5.txt");
        Controller ctr5 = new Controller(repo5);

        Stmt ex6 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),new CompStmt(new HeapAllocStmt("a",new ValueExp(new IntValue(22))),new CompStmt(new ForkStmt(new CompStmt(new wHStmt("a",new ValueExp(new IntValue(30))),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rHExp(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rHExp(new VarExp("a")))))))));
        PrgState pr6 = new PrgState(new MyStack<>(new Stack<>()),new MyDict<>(new HashMap<>()),new MyList<>(new ArrayList<>()),new MyDict<>(new HashMap<>()),new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()),ex6);
        ArrayList<PrgState> list6 = new ArrayList<>();
        list6.add(pr6);
        IRepo repo6 = new Repo(list6,"log6.txt");
        Controller ctr6 = new Controller(repo6);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1", ex1.toString(),ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(),ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(),ctr6));
        menu.show();
    }
}