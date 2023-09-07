package db.helpers;

import db.helperstructures.AttributeValuePair;
import db.helperstructures.SimpleFKConstraintTuple;
import db.helperstructures.TableAttributeTuple;

import java.util.ArrayList;

public class QueriesGenerator {
    public static String queryForTheExistenceOfTheDBNamed(String dbName) {
        String format = "SELECT EXISTS( SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower('%s') );";
        return String.format(format, dbName);
    }

    public static String queryToCreateDBWithNameAndOwner(String dbName, String ownerName) {
        String format = "CREATE DATABASE %s WITH OWNER = '%s'";
        return String.format(format, dbName, ownerName);
    }

    public static String queryToCreateSimpleTableWithoutDependencies
            (String tableName, TableAttributeTuple[] attributes)
            throws IllegalArgumentException {
        if (attributes == null || attributes.length < 1) {
            throw new IllegalArgumentException();
        }

        String tableCreationRear = ", PRIMARY KEY(id) );";
        String front = getTheAttributesPartOfTheTableCreationQuery(tableName, attributes);
        return front + tableCreationRear;
    }

    public static String queryToCreateSimpleTableWithFKDependency
            (String tableName, TableAttributeTuple[] attributes, SimpleFKConstraintTuple[] fkConstraints)
            throws IllegalArgumentException {
        if (attributes == null || attributes.length < 1 || fkConstraints == null || fkConstraints.length < 1) {
            throw new IllegalArgumentException();
        }

        String tableCreationRear = ", PRIMARY KEY(id) );";
        String front = getTheAttributesPartOfTheTableCreationQuery(tableName, attributes);
        StringBuilder stringBuilder = new StringBuilder(front);
        for (SimpleFKConstraintTuple constraint : fkConstraints) {
            stringBuilder.append(String.format(", %s", constraint.getFormattedTuple()));
        }
        stringBuilder.append(tableCreationRear);
        return stringBuilder.toString();
    }

    public static String queryToInsertRowInATable(String tableName, AttributeValuePair[] pairs)
            throws IllegalArgumentException {
        if (pairs == null || pairs.length < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder(String.format("INSERT INTO %s(", tableName));
        for (int i = 0; i < pairs.length - 1; ++i) {
            if (pairs[i].value != null) {
                stringBuilder.append(String.format("%s, ", pairs[i].attribute));
            }
        }
        stringBuilder.append(pairs[pairs.length - 1].attribute + ") VALUES(");
        for (int i = 0; i < pairs.length - 1; ++i) {
            if (pairs[i].value != null) {
                stringBuilder.append(String.format("%s, ", pairs[i].value));
            }
        }
        stringBuilder.append(pairs[pairs.length - 1].value + ");");
        return stringBuilder.toString();
    }

    public static String queryToInsertRowInATable(String tableName, ArrayList<AttributeValuePair> pairs)
            throws IllegalArgumentException {
        if (pairs == null || pairs.size() < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder(String.format("INSERT INTO %s(", tableName));
        for (int i = 0; i < pairs.size() - 1; ++i) {
            if (pairs.get(i).value != null && !pairs.get(i).value.isEmpty()) {
                stringBuilder.append(String.format("%s, ", pairs.get(i).attribute));
            }
        }
        if (pairs.get(pairs.size() - 1).value != null && !pairs.get(pairs.size() - 1).value.isEmpty()) {
            stringBuilder.append(pairs.get(pairs.size() - 1).attribute + ") VALUES(");
        } else {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(") VALUES(");
        }

        for (int i = 0; i < pairs.size() - 1; ++i) {
            if (pairs.get(i).value != null && !pairs.get(i).value.isEmpty()) {
                stringBuilder.append(String.format("'%s', ", pairs.get(i).value));
            }
        }
        if (pairs.get(pairs.size() - 1).value != null && !pairs.get(pairs.size() - 1).value.isEmpty()) {
            stringBuilder.append(String.format("'%s');", pairs.get(pairs.size() - 1).value));
        } else {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(");");
        }
        if (pairs.get(pairs.size() - 1).value != null && !pairs.get(pairs.size() - 1).value.isEmpty()) {
        }
        return stringBuilder.toString();
    }

    public static String queryToGetRowsFromATable
            (String tableName, ArrayList<AttributeValuePair> pairs) throws IllegalArgumentException {
        if (pairs == null || pairs.size() < 1) {
            return "SELECT * FROM " + tableName;
        }

        StringBuilder stringBuilder = new StringBuilder(String.format("SELECT * FROM %s WHERE ", tableName));
        for (int i = 0; i < pairs.size() - 1; ++i) {
            stringBuilder.append(String.format("%s = '%s' AND ", pairs.get(i).attribute, pairs.get(i).value));
        }
        stringBuilder.append(String.format("%s = '%s';", pairs.get(pairs.size() - 1).attribute, pairs.get(pairs.size() - 1).value));
        return stringBuilder.toString();
    }

    public static String queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues
            (String tableName, ArrayList<AttributeValuePair> pairs) throws IllegalArgumentException {
        if (pairs == null || pairs.size() < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder(String.format("SELECT id FROM %s WHERE ", tableName));
        for (int i = 0; i < pairs.size() - 1; ++i) {
            if (pairs.get(i).value != null && !pairs.get(i).value.isEmpty()) {
                stringBuilder.append(String.format("%s = '%s' AND ", pairs.get(i).attribute, pairs.get(i).value));
            }
        }
        if (pairs.get(pairs.size() - 1).value != null && !pairs.get(pairs.size() - 1).value.isEmpty()) {
            stringBuilder.append(String.format("%s = '%s';", pairs.get(pairs.size() - 1).attribute, pairs.get(pairs.size() - 1).value));
        } else {
            stringBuilder.delete(stringBuilder.length() - 5, stringBuilder.length());
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }

    public static String queryToCountRowsFromATable(String tableName) {
        return "SELECT COUNT(*) FROM " + tableName;
    }

    public static String queryToDeleteFromTableById(String tableName, String id) {
        return String.format("DELETE FROM %s WHERE id = %s;", tableName, id);
    }

    public static String queryToDeleteFromTableById(String tableName, int id) {
        return String.format("DELETE FROM %s WHERE id = %s;", tableName, id);
    }

    private static String getTheAttributesPartOfTheTableCreationQuery
            (String tableName, TableAttributeTuple[] attributes) {
        String front = String.format("CREATE TABLE %s ( id SERIAL", tableName);
        StringBuilder stringBuilder = new StringBuilder(front);
        for (TableAttributeTuple attribute : attributes) {
            stringBuilder.append(String.format(", %s", attribute.getFormattedTuple()));
        }
        return stringBuilder.toString();
    }

    public static String queryToUpdateFieldFromTableWithId(String tableName, AttributeValuePair pair, String id) {
        return String.format("UPDATE %s SET %s = '%s' WHERE id = '%s';", tableName, pair.attribute, pair.value, id);
    }

    public static String queryToUpdateFieldFromTableWithId(String tableName, AttributeValuePair pair, int id) {
        return String.format("UPDATE %s SET %s = '%s' WHERE id = '%s';", tableName, pair.attribute, pair.value, id);
    }
}
