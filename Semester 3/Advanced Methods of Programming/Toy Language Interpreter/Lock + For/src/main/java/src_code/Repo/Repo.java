package src_code.Repo;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repo implements IRepo{
    List<PrgState> stateList;
    String logFilePath;

    public Repo(List<PrgState> stateList, String logFilePath) {
        this.stateList = stateList;
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgList() {
        return stateList;
    }

    @Override
    public void setPrgList(List<PrgState> newPrgList) {
        stateList.clear();
        stateList.addAll(newPrgList);
    }

    @Override
    public void addPrgState(PrgState state) {
        stateList.add(state);
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException,IOException{
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
            PrgState myPrg = state;
            logFile.print(myPrg.toString());
            logFile.close();
    }
}
