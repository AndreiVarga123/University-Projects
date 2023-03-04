package src_code.Model.Type;

import src_code.Model.Value.Value;

public interface Type {
    Type deepCopy();
    Value defaultValue();
}
