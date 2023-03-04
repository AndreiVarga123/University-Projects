package src_code.Model.Value;

import src_code.Model.Type.RefType;
import src_code.Model.Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public String toString() {
        return "("+address+","+locationType.toString()+")";
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address,locationType);
    }
}
