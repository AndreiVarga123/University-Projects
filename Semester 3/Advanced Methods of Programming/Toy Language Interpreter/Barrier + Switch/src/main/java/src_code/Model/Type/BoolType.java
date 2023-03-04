package src_code.Model.Type;

import src_code.Model.Value.BoolValue;
import src_code.Model.Value.Value;

public class BoolType implements Type{
    public BoolType() {
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
