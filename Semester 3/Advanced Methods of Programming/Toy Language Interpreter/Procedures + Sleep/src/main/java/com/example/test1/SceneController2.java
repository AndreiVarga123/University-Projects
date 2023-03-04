package com.example.test1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import src_code.Controller.Controller;
import src_code.Model.ProgramState.ADT.*;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Statement.Stmt;
import src_code.Model.Type.Type;
import src_code.Model.Value.StringValue;
import src_code.Model.Value.Value;
import src_code.Repo.IRepo;
import src_code.Repo.Repo;

import java.io.BufferedReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class SceneController2 {
    @FXML private TextField textField;
    @FXML private TableView<Pair<Integer,Value>> heap;
    @FXML private TableColumn<Pair<Integer, Value>, Integer> heapAddress;
    @FXML private TableColumn<Pair<Integer, Value>, Value> heapValue;
    @FXML private ListView out;
    @FXML private ListView fileTable;
    @FXML private ListView prgStateId;
    @FXML private TableView symTable;
    @FXML private TableColumn<Pair<String ,Value>, String> symTableName;
    @FXML private TableColumn<Pair<String ,Value>, Value> symTableValue;
    @FXML private ListView exeStack;
    @FXML private Button oneStep;
    @FXML private TableView<Pair<String,String>> procTable;
    @FXML private TableColumn<Pair<String,String>,String> procName;
    @FXML private TableColumn<Pair<String,String>,String> procParamBody;
    private Stmt stmt;
    private PrgState pr;
    private IRepo repo;
    private Controller ctrl;

    private ExecutorService executor = Executors.newFixedThreadPool(2);
    private Integer id;
    private Integer index;

    public void initScene()
    {
        MyIStack symTable = new MyStack(new Stack());
        symTable.push(new MyDict<>(new HashMap<>()));
        pr = new PrgState(new MyStack<>(new Stack<>()), symTable, new MyList<>(new ArrayList<>()), new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()), stmt);
        ArrayList<PrgState> list = new ArrayList<>();
        list.add(pr);
        repo = new Repo(list, "log" +index+".txt");
        ctrl = new Controller(repo);
        for (PrgState state: repo.getPrgList()) {
            MyIDict<String, Type> typeEnv = new MyDict<>(new HashMap<>());
            state.getExeStack().peek().typecheck(typeEnv);
        }
        heapAddress.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Value>,Integer>("first"));
        heapValue.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Value>,Value>("second"));
        symTableName.setCellValueFactory(new PropertyValueFactory<Pair<String,Value>,String>("first"));
        symTableValue.setCellValueFactory(new PropertyValueFactory<Pair<String,Value>,Value>("second"));
        procName.setCellValueFactory(new PropertyValueFactory<Pair<String,String>,String>("first"));
        procParamBody.setCellValueFactory(new PropertyValueFactory<Pair<String,String>,String>("second"));

        prgStateId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                id = (Integer) prgStateId.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void populate(){
        populateTextField();
        populateHeap();
        populateOut();
        populateFiletable();
        populatePrgStateId();
        populateProcTable();
    }

    public void populateProcTable(){
        procTable.getItems().clear();
        if(!ctrl.getPrgList().isEmpty()) {
            ObservableList<Pair<String,String>> obsList = FXCollections.observableArrayList();
            for (Map.Entry<String, javafx.util.Pair<ArrayList<String>, Stmt>> r : ctrl.getPrgList().get(0).getProcTable().getSet()) {
                Pair<String,String> pair = new Pair<>(r.getKey(), r.getValue().toString());
                obsList.add(pair);
            }
            procTable.setItems(obsList);
            procTable.refresh();
        }
    }

    private void populateTextField()
    {
        if(ctrl.getPrgList().isEmpty())
            textField.setText(String.valueOf(0));
        else
            textField.setText(String.valueOf(ctrl.getnrOfPrgs()));
    }

    private void populateHeap()
    {
        if(!ctrl.getPrgList().isEmpty()) {
            heap.getItems().clear();
            ObservableList<Pair<Integer, Value>> obsList = FXCollections.observableArrayList();
            for (Map.Entry<Integer, Value> r : ctrl.getPrgList().get(0).getMemHeap().getSet()) {
                Pair<Integer, Value> pair = new Pair<>(r.getKey(), r.getValue());
                obsList.add(pair);
            }
            heap.setItems(obsList);
            heap.refresh();
        }
    }

    private void populateOut()
    {
        if(!ctrl.getPrgList().isEmpty()){
            out.getItems().clear();
            Iterator<Value> i = ctrl.getPrgList().get(0).getOut().getIter();
            while (i.hasNext())
            {
                out.getItems().add(i.next());
            }
            out.refresh();
        /*
        if(!ctrl.getPrgList().get(0).getOut().isEmpty()) {
            out.getItems().addAll(ctrl.getPrgList().get(0).getOut());
        }
        */}
    }

    private void  populateFiletable(){
        if(!ctrl.getPrgList().isEmpty()) {
            fileTable.getItems().clear();
            for (Map.Entry<StringValue, BufferedReader> r : ctrl.getPrgList().get(0).getFileTable().getSet()) {
                fileTable.getItems().add(r.getKey());
            }
            fileTable.refresh();
        }
    }

    private void populatePrgStateId(){
        prgStateId.getItems().clear();
        if(!ctrl.getPrgList().isEmpty()) {
            for (PrgState prg : ctrl.getPrgList()) {
                prgStateId.getItems().add(prg.getId());
            }
            prgStateId.refresh();
        }
    }

    private void populateSymTable(Integer id){
        symTable.getItems().clear();
        if(!ctrl.getPrgList().isEmpty()) {
            ObservableList<Pair<String, Value>> obsList = FXCollections.observableArrayList();
            for (Map.Entry<String, Value> r : ctrl.getPrgList().get(id).getSymTable().getSet()) {
                Pair<String, Value> pair = new Pair<>(r.getKey(), r.getValue());
                obsList.add(pair);
            }
            symTable.setItems(obsList);
        }
        symTable.refresh();
    }

    private void populateExeStack(Integer id){
        exeStack.getItems().clear();
        if(!ctrl.getPrgList().isEmpty()) {
            Iterator<Stmt> i = ctrl.getPrgList().get(id).getStackAsList().getIter();
            while (i.hasNext()) {
                exeStack.getItems().add(i.next());
            }

        /*
        if(!ctrl.getPrgList().get(id).getStackAsList().isEmpty()) {
            exeStack.getItems().addAll(ctrl.getPrgList().get(id).getStackAsList());
        }
        */
        }
        exeStack.refresh();
    }

    public void setCurr(Stmt stmt,Integer ind)
    {
        this.stmt=stmt;
        this.index=ind;
    }

    @FXML
    public void runOneStep(){
        ctrl.allStep();
        populate();
    }

    @FXML
    public void getPrgWithId(MouseEvent event)
    {
        if(id!=null) {
            int i;
            for(i=0;i<repo.getPrgList().size();i++)
            {
                if(repo.getPrgList().get(i).getId()==id)
                {
                    id = i;
                    break;
                }
            }
            populateExeStack(i);
            populateSymTable(i);
        }
    }
}
