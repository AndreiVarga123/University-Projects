package src_code.Model.Value;

import src_code.Model.Type.StringType;
import src_code.Model.Type.Type;

public class StringValue implements Value{
    String str;

    public StringValue(String str) {
        this.str = str;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(str);
    }

    @Override
    public String toString() {
        return "\""+str+"\"";
    }

    public String getVal(){
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringValue;
    }
}
