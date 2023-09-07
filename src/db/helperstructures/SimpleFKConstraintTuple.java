package db.helperstructures;

public class SimpleFKConstraintTuple {
    private String constraintName;
    private String foreignKeyName;
    private String foreignTableName;
    private String foreignTableIdName;
    private String formattedTuple;

    public SimpleFKConstraintTuple(String constraintName,
                                   String foreignKeyName,
                                   String foreignTableName,
                                   String foreignTableIdName) {

        this.constraintName = constraintName;
        this.foreignKeyName = foreignKeyName;
        this.foreignTableName = foreignTableName;
        this.foreignTableIdName = foreignTableIdName;
        createFormattedTuple();
    }

    public String getFormattedTuple() {
        return formattedTuple;
    }

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
        createFormattedTuple();
    }

    public String getForeignKeyName() {
        return foreignKeyName;
    }

    public void setForeignKeyName(String foreignKeyName) {
        this.foreignKeyName = foreignKeyName;
        createFormattedTuple();
    }

    public String getForeignTableName() {
        return foreignTableName;
    }

    public void setForeignTableName(String foreignTableName) {
        this.foreignTableName = foreignTableName;
    }

    public String getForeignTableIdName() {
        return foreignTableIdName;
    }

    public void setForeignTableIdName(String foreignTableIdName) {
        this.foreignTableIdName = foreignTableIdName;
        createFormattedTuple();
    }

    private void createFormattedTuple() {
        formattedTuple = String.format("CONSTRAINT %s FOREIGN KEY(%s) REFERENCES %s(%s)",
                constraintName, foreignKeyName, foreignTableName, foreignTableIdName);
    }
}
