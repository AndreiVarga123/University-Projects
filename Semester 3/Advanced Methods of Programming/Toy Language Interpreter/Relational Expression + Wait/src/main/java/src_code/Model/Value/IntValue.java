package src_code.Model.Value;

import src_code.Model.Type.IntType;
import src_code.Model.Type.Type;

public class IntValue implements Value{
    int val;

    public IntValue(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    @Override
    public Value deepCopy() {
        return new IntValue(val);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntValue;
    }
}
