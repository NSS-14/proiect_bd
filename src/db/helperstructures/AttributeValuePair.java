package db.helperstructures;

import com.sun.jdi.ClassType;
import com.sun.jdi.Field;

import java.util.List;

public class AttributeValuePair {
    public String attribute;
    public String value;

    public AttributeValuePair() {
    }

    public AttributeValuePair(String attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }
}
