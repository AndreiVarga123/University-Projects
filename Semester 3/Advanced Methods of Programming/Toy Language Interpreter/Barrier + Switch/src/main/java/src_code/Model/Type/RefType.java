package src_code.Model.Type;

import src_code.Model.Value.RefValue;
import src_code.Model.Value.Value;

public class RefType implements Type{
    Type inner;
    public RefType(Type inner){this.inner=inner;}
    public Type getInner(){return inner;}

    @Override
    public boolean equals(Object another) {
        if(another instanceof RefType)
            return inner.equals(((RefType) another).inner);
        else
            return false;
    }

    @Override
    public String toString() {
        return "Ref "+inner.toString();
    }

    @Override
    public Type deepCopy() {
        return new RefType(inner);
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0,inner);
    }
}
