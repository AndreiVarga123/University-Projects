package src_code.View.Command;

import src_code.Controller.Controller;
import src_code.Exception.MyException;
import src_code.Controller.Controller;

public class RunExample extends Command{
    private Controller ctr;

    public RunExample(String key, String descr, Controller ctr) {
        super(key, descr);
        this.ctr = ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allStep();
        }
        catch (MyException me)
        {
            System.out.println(me.getMessage());
        }
    }
}
