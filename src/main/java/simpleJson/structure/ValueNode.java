package simpleJson.structure;

import java.util.Deque;

public class ValueNode extends Node {
    private Object value;

    public ValueNode(Object value) {
      this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value instanceof Integer integer){
            return integer.toString();
        } else if (value instanceof Double doubleValue){
            return doubleValue.toString();
        } else if (value instanceof Boolean booleanValue){
            return booleanValue.toString();
        } else if (value instanceof String stringValue){
            return stringValue;
        }
        return value.toString();
    }
}
