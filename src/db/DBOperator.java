package db;

import db.helpers.QueriesGenerator;
import db.helperstructures.AttributeValuePair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOperator {
    public static ResultSet obtainRowsFromATable
            (String tableName, ArrayList<AttributeValuePair> pairs, Statement statement) throws SQLException {
        return statement.executeQuery(QueriesGenerator.queryToGetRowsFromATable(tableName, pairs));
    }

    public static void insertRowInATable
            (String tableName, AttributeValuePair[] pairs, Statement statement) throws SQLException {
        statement.executeUpdate(QueriesGenerator.queryToInsertRowInATable(tableName, pairs));
    }

    public static void insertRowInATable
            (String tableName, ArrayList<AttributeValuePair> pairs, Statement statement) throws SQLException {
        statement.executeUpdate(QueriesGenerator.queryToInsertRowInATable(tableName, pairs));
    }

    public static int obtainRowsCountFromATable(String tableName, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(QueriesGenerator.queryToCountRowsFromATable(tableName));
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static int obtainIdOfARow(String tableName, ArrayList<AttributeValuePair> pairs, Statement statement)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues(tableName, pairs));
        resultSet.next();
        return resultSet.getInt(1);
    }

    public static void deleteById(String tableName, String id, Statement statement) throws SQLException {
        statement.executeUpdate(QueriesGenerator.queryToDeleteFromTableById(tableName, id));
    }

    public static void deleteById(String tableName, int id, Statement statement) throws SQLException {
        statement.executeUpdate(QueriesGenerator.queryToDeleteFromTableById(tableName, id));
    }

    public static void updateField(String tableName, int id, AttributeValuePair pair, Statement statement) throws SQLException {
        statement.executeUpdate(QueriesGenerator.queryToUpdateFieldFromTableWithId(tableName, pair, id));
    }

    public static void updateField(String tableName, String id, AttributeValuePair pair, Statement statement) throws SQLException {
        statement.executeUpdate(QueriesGenerator.queryToUpdateFieldFromTableWithId(tableName, pair, id));
    }
}
