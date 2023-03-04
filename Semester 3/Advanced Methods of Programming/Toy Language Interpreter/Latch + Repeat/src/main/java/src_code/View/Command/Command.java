package src_code.View.Command;

public abstract class Command {
    private String key,descr;

    public Command(String key, String descr) {
        this.key = key;
        this.descr = descr;
    }

    public  abstract void execute();

    public String getKey() {
        return key;
    }

    public String getDescr() {
        return descr;
    }
}
