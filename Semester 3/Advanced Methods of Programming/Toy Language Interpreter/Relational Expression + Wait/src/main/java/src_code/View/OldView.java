package src_code.View;//package View;
//
//import Controller.Controller;
//import Model.Expression.ArithExp;
//import Model.Expression.ValueExp;
//import Model.Expression.VarExp;
//import Model.ProgramState.ADT.*;
//import Model.ProgramState.PrgState;
//import Model.Statement.*;
//import Model.Type.BoolType;
//import Model.Type.IntType;
//import Model.Type.StringType;
//import Model.Value.BoolValue;
//import Model.Value.IntValue;
//import Model.Value.StringValue;
//import Model.Value.Value;
//import Exception.MyException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class OldView {
//    Controller controller;
//
//    public OldView(Controller controller) {
//        this.controller = controller;
//    }
//
//    public void printMenu(){
//        System.out.println("0.Exit");
//        System.out.println("1.Input a program");
//        System.out.println("2.Run program");
//    }
//
//    public void printProgramMenu(){
//        System.out.println("Example1:"+"int v; v=2;Print(v)");
//        System.out.println("Example2:"+"int a;int b; a=2+3*5;b=a+1;Print(b)");
//        System.out.println("Example3:"+"bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
//        System.out.println("Example4:"+"string varf; varf=\"test.in\"; openFile(varf); int varc; readFile(varf,varc); print(varc); readFile(varf,varc); print(varc); closeFile(varf)");
//    }
//
//    public void start() throws IOException {
//        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//            this.printMenu();
//            System.out.println("Choose an option:");
//            String option = reader.readLine();
//            try {
//                if (option.equals("0")) return;
//                else if (option.equals("1")) {
//                    this.printProgramMenu();
//                    System.out.println("Choose a program:");
//                    option = reader.readLine();
//                    if (option.equals("1")) {
//                        Stmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
//                        MyIStack<Stmt> exeStk = new MyStack<>(new Stack<>());
//                        MyIDict<String, Value> symTbl = new MyDict<>(new HashMap<>());
//                        MyIList<Value> out = new MyList<>(new ArrayList<>());
//                        MyIDict<StringValue,BufferedReader> fileTable = new MyDict<>(new HashMap<>());
//                        controller.addPrg(new PrgState(exeStk, symTbl, out, fileTable, ex1));
//                    } else if (option.equals("2")) {
//                        Stmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
//                        MyIStack<Stmt> exeStk = new MyStack<>(new Stack<>());
//                        MyIDict<String, Value> symTbl = new MyDict<>(new HashMap<>());
//                        MyIList<Value> out = new MyList<>(new ArrayList<>());
//                        MyIDict<StringValue,BufferedReader> fileTable = new MyDict<>(new HashMap<>());
//                        controller.addPrg(new PrgState(exeStk, symTbl, out, fileTable, ex2));
//                    } else if (option.equals("3")) {
//                        Stmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
//                        MyIStack<Stmt> exeStk = new MyStack<>(new Stack<>());
//                        MyIDict<String, Value> symTbl = new MyDict<>(new HashMap<>());
//                        MyIList<Value> out = new MyList<>(new ArrayList<>());
//                        MyIDict<StringValue,BufferedReader> fileTable = new MyDict<>(new HashMap<>());
//                        controller.addPrg(new PrgState(exeStk, symTbl, out, fileTable, ex3));
//                    } else if(option.equals("4")){
//                        Stmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),new CompStmt(new OpenFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc", new IntType()),new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));
//                        MyIStack<Stmt> exeStk = new MyStack<>(new Stack<>());
//                        MyIDict<String, Value> symTbl = new MyDict<>(new HashMap<>());
//                        MyIList<Value> out = new MyList<>(new ArrayList<>());
//                        MyIDict<StringValue,BufferedReader> fileTable = new MyDict<>(new HashMap<>());
//                        controller.addPrg(new PrgState(exeStk, symTbl, out, fileTable, ex4));
//                    } else System.out.println("Input a valid option");
//                } else if (option.equals("2")) {
//                    controller.allStep();
//                    System.out.println("Successful execution");
//                }
//                else System.out.println("Input a valid option");
//            }
//            catch (MyException me)
//            {
//                System.out.println(me.getMessage());
//            }
//        }
//    }
//}
