package src_code.Model.Value;

import src_code.Model.Type.BoolType;
import src_code.Model.Type.Type;

public class BoolValue implements Value{
    boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    public boolean getVal() {
        return val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolValue;
    }
}
