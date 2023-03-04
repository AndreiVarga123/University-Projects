package src_code.Model.Type;

import src_code.Model.Value.StringValue;
import src_code.Model.Value.Value;

public class StringType implements Type{

    public StringType() {

    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringType;
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }
}
