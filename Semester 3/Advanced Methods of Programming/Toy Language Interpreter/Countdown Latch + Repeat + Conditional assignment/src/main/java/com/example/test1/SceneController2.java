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
    @FXML private TableView<Pair<Integer,Integer>> latchTable;
    @FXML private TableColumn<Pair<Integer,Integer>,Integer> locationColumn;
    @FXML private TableColumn<Pair<Integer,Integer>,Integer> lvalueColumn;
    private Stmt stmt;
    private PrgState pr;
    private IRepo repo;
    private Controller ctrl;

    private ExecutorService executor = Executors.newFixedThreadPool(2);
    private Integer id;
    private Integer index;

    public void initScene()
    {
        pr = new PrgState(new MyStack<>(new Stack<>()), new MyDict<>(new HashMap<>()), new MyList<>(new ArrayList<>()), new MyDict<>(new HashMap<>()), new MyHeap<>(new HashMap<>()),new MyTable<>(new HashMap<>()), stmt);
        ArrayList<PrgState> list = new ArrayList<>();
        list.add(pr);
        repo = new Repo(list, "log"+index+".txt");
        ctrl = new Controller(repo);
        for (PrgState state: repo.getPrgList()) {
            MyIDict<String, Type> typeEnv = new MyDict<>(new HashMap<>());
            state.getExeStack().peek().typecheck(typeEnv);
        }
        heapAddress.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Value>,Integer>("first"));
        heapValue.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Value>,Value>("second"));
        symTableName.setCellValueFactory(new PropertyValueFactory<Pair<String,Value>,String>("first"));
        symTableValue.setCellValueFactory(new PropertyValueFactory<Pair<String,Value>,Value>("second"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Integer>,Integer>("first"));
        lvalueColumn.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Integer>,Integer>("second"));

        id=0;

        prgStateId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(prgStateId.getSelectionModel().getSelectedItem()!=null)
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
        populateLatchTable();
        getPrgWithId();
    }

    private void populateLatchTable() {
        if(!ctrl.getPrgList().isEmpty()) {
            latchTable.getItems().clear();
            ObservableList<Pair<Integer, Integer>> obsList = FXCollections.observableArrayList();
            for (Map.Entry<Integer, Integer> r : ctrl.getPrgList().get(0).getLatchTable().getSet()) {
                Pair<Integer, Integer> pair = new Pair<>(r.getKey(), r.getValue());
                obsList.add(pair);
            }
            latchTable.setItems(obsList);
            latchTable.refresh();
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
        this.index = ind;
    }

    @FXML
    public void runOneStep(){
        ctrl.allStep();
        populate();
    }

    @FXML
    public void getPrgWithId()
    {
        int i;
        boolean ok =false;
        for(i=0;i<repo.getPrgList().size();i++)
        {
            if(repo.getPrgList().get(i).getId()==id)
            {
                populateExeStack(i);
                populateSymTable(i);
                ok = true;
                break;
            }
        }
        if(!ok) {
            populateExeStack(0);
            populateSymTable(0);
        }
    }
}
