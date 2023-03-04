package src_code.Controller;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.ADT.MyDict;
import src_code.Model.ProgramState.ADT.MyIDict;
import src_code.Model.ProgramState.PrgState;
import src_code.Model.Type.Type;
import src_code.Model.Value.RefValue;
import src_code.Model.Value.Value;
import src_code.Repo.IRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepo repo;
    ExecutorService executor;
    List<PrgState> prgList;

    public List<PrgState> getPrgList(){
        return repo.getPrgList();
    }

    public int getnrOfPrgs(){
        return repo.getPrgList().size();
    }

    public Controller(IRepo repo) {
        this.repo = repo;
        for (PrgState state: repo.getPrgList()) {
            MyIDict<String, Type> typeEnv = new MyDict<>(new HashMap<>());
            state.getExeStack().peek().typecheck(typeEnv);
        }

        executor = Executors.newFixedThreadPool(2);
        prgList = removeCompletedPrg(repo.getPrgList());
    }

    public void addPrg(PrgState prg)
    {
        repo.addPrgState(prg);
    }

    Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Map<Integer,Value> heap, List<PrgState> prgList){
        List<Integer> toReturn = new ArrayList<>();
        for(PrgState p : prgList) {
            p.getSymTable().getContent().values().stream()
                    .filter(v -> v instanceof RefValue)
                    .forEach(v -> {
                        while (v instanceof RefValue) {
                            if(!toReturn.contains(((RefValue) v).getAddress())) {
                                toReturn.add(((RefValue) v).getAddress());
                            }
                            v = heap.get(((RefValue) v).getAddress());
                        }
                    });
        }
        return toReturn;
    }

    /*
    PrgState oneStep(PrgState state) throws MyException{
        MyIStack<Stmt> stk= state.getExeStack();
        if(!stk.isEmpty())
        {
            Stmt crtStmt=stk.pop();
            return crtStmt.execute(state);
        }
        else
            throw new MyException("Execution stack is empty");
    }
    */

    /*
    public void allStep() throws IOException {
        PrgState prg=repo.getPrgList().get(0);
        repo.logPrgStateExec();
        while (!prg.getExeStack().isEmpty())
        {
            oneStep(prg);
            repo.logPrgStateExec();
            prg.getMemHeap().setContent(garbageCollector(
                    getAddrFromSymTable(prg.getSymTable().getContent().values(),prg.getMemHeap().getContent())
                    ,prg.getMemHeap().getContent()));
            repo.logPrgStateExec();
        }
        ///System.out.println(prg.getOut().toString());
    }
     */

    private void oneStepForAllPrg(List<PrgState> prgList) throws MyException{
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                throw new MyException("Error creating file");
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p)->(Callable<PrgState>)(()->{return p.oneStep();}))
                .collect(Collectors.toList());
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future-> {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {
                            throw new MyException("Error geting return value from future");
                        } catch (ExecutionException e) {
                            throw new MyException("Error geting return value from future");
                        }
                    })
                    .filter(p->p!=null)
                    .collect(Collectors.toList());
            prgList.addAll(newPrgList);
        } catch (InterruptedException e) {
            throw new MyException("Error getting stream of futures");
        }

        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                throw new MyException("Error creating file");
            }
        });

        repo.setPrgList(prgList);
    }

    public void allStep(){
        if(!prgList.isEmpty()){
            oneStepForAllPrg(prgList);
            prgList.get(0).getMemHeap().setContent(garbageCollector(
                    getAddrFromSymTable(prgList.get(0).getMemHeap().getContent(),prgList),
                    prgList.get(0).getMemHeap().getContent()));
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        else {
            executor.shutdownNow();
            repo.setPrgList(prgList);
        }
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(p->p.isNotCompleted()).collect(Collectors.toList());
    }


}
