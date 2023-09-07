package db.helperstructures;

public class TableAttributeTuple {
    private String propertyName;
    private String typeName;
    private boolean nullable;
    private String formattedTuple;

    public TableAttributeTuple(String propertyName, String typeName, boolean nullable) {
        this.propertyName = propertyName;
        this.typeName = typeName;
        this.nullable = nullable;
        createFormattedTuple();
    }

    public String getFormattedTuple() {
        return formattedTuple;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        createFormattedTuple();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
        createFormattedTuple();
    }

    public boolean getNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
        createFormattedTuple();
    }

    private void createFormattedTuple() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%s %s", propertyName, typeName));
        if (!nullable) {
            stringBuilder.append(" NOT NULL");
        }
        formattedTuple = stringBuilder.toString();
    }
}
