package src_code.Repo;

import src_code.Exception.MyException;
import src_code.Model.ProgramState.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepo {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> newPrgList);
    void addPrgState(PrgState state);
    void logPrgStateExec(PrgState state) throws MyException, IOException;


}
