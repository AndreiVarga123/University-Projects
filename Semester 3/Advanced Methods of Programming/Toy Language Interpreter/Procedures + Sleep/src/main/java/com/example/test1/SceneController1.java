package com.example.test1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import src_code.Controller.Controller;
import src_code.Model.Expression.*;
import src_code.Model.ProgramState.ADT.MyDict;
import src_code.Model.ProgramState.ADT.MyHeap;
import src_code.Model.ProgramState.ADT.MyList;
import src_code.Model.ProgramState.ADT.MyStack;
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

import java.net.URL;
import java.util.*;

public class SceneController1 implements Initializable {

    @FXML private Stage stage;
    @FXML private Button choose;
    @FXML private ListView<Stmt> examples;
    @FXML private Parent root;
    @FXML private Scene scene;
    private Stmt curr=null;
    private Integer index=-1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        Stmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

        Stmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        Stmt ex4 = new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("C:\\Users\\varga\\IdeaProjects\\test1\\src\\test.in"))),new CompStmt(new OpenFileStmt(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc", new IntType()),new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new CloseFileStmt(new VarExp("varf"))))))))));

        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),new PrintStmt(new ArithExp(1,new rHExp(new rHExp(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new PrintStmt(new rHExp(new VarExp("v"))),new CompStmt(new wHStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new ArithExp(1,new rHExp(new VarExp("v")),new ValueExp(new IntValue(5))))))));
        Stmt ex5 = new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(20))),new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new HeapAllocStmt("a",new VarExp("v")),new CompStmt(new HeapAllocStmt("v",new ValueExp(new IntValue(30))),new PrintStmt(new rHExp(new rHExp(new VarExp("a")))))))));
        //Stmt ex5 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationalExp(2,new VarExp("v"),new ValueExp(new IntValue(0))),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp(2,new VarExp("v"),new ValueExp(new IntValue(1)))))),new PrintStmt(new VarExp("v")))));

        Stmt ex6 = new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),new CompStmt(new HeapAllocStmt("a",new ValueExp(new IntValue(22))),new CompStmt(new ForkStmt(new CompStmt(new wHStmt("a",new ValueExp(new IntValue(30))),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rHExp(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rHExp(new VarExp("a")))))))));

        ArrayList<Exp> sumCall = new ArrayList<>();
        sumCall.add(new ArithExp(3, new VarExp("v"), new ValueExp(new IntValue(10))));
        sumCall.add(new VarExp("w"));

        ArrayList<Exp> broadcast = new ArrayList<>();
        broadcast.add(new VarExp("v"));
        broadcast.add(new VarExp("w"));

        Stmt ex7 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))), new CompStmt(
                new WhileStmt(new RelationalExp(1, new VarExp("v"), new ValueExp(new IntValue(3))), new CompStmt(
                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(1, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                        new AssignStmt("v", new ArithExp(1, new VarExp("v"), new ValueExp(new IntValue(1)))))), new CompStmt(
                new SleepStmt(5), new PrintStmt(new ArithExp(3, new VarExp("v"), new ValueExp(new IntValue(10))))
        )
        )));

        Stmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("w", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(5))),
                        new CompStmt(new CallStmt("sum", sumCall),new CompStmt(new PrintStmt(new VarExp("v")),
                                new CompStmt(new ForkStmt(new CallStmt("product", broadcast)),
                                        new ForkStmt(new CallStmt("sum", broadcast)))))))));

        List<Stmt> exampleList = new ArrayList<>();

        exampleList.add(ex1);
        exampleList.add(ex2);
        exampleList.add(ex3);
        exampleList.add(ex4);
        exampleList.add(ex5);
        exampleList.add(ex6);
        exampleList.add(ex7);
        exampleList.add(ex8);

        examples.getItems().addAll(exampleList);

        examples.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stmt>() {
            @Override
            public void changed(ObservableValue<? extends Stmt> observableValue, Stmt stmt, Stmt t1) {
                curr = examples.getSelectionModel().getSelectedItem();
                index = examples.getSelectionModel().getSelectedIndex();
            }
        });
    }

    @FXML
    public void switchToScene2(ActionEvent event)
    {
        if(curr!= null)
        {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
                root = loader.load();
                SceneController2 scene2 = loader.getController();
                scene2.setCurr(curr,index);
                scene2.initScene();
                scene2.populate();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("NU BUN");
        }
    }
}