package src_code.Model.Value;

import src_code.Model.Type.Type;

public interface Value {
    Type getType();
    Value deepCopy();
}
