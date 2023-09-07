package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomType {
    public int id;
    public String type;

    public RoomType() {
    }

    public RoomType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public static String[] getFieldsName() {
        Field[] fields = RoomType.class.getDeclaredFields();
        String[] fieldsName = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            fieldsName[i] = fields[i].getName();
        }
        return fieldsName;
    }

    public static Object[][] toTable(ResultSet resultSet, int numberOfRows) throws SQLException {

        String[] headers = getFieldsName();
        Object[][] result = new Object[numberOfRows][headers.length];

        int i = 0;
        while (resultSet.next()) {
            result[i][0] = resultSet.getInt("id");
            result[i][1] = resultSet.getString("type");
            ++i;
        }
        return result;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = RoomType.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals("id")) {
                continue;
            }
            String fieldName = fields[i].getName();
            attributeName = PostgresqlTableAttribute.toPostgresAttribute(fieldName);
            try {
                fieldValue = fields[i].get(this).toString();
                if (fieldValue == null) {
                    throw new Exception();
                }
                fieldStringValue = fieldValue.toString();
            } catch (Exception e) {
                continue;
            }
            result.add(new AttributeValuePair(attributeName, fieldStringValue));
        }
        return result;
    }

    public ArrayList<AttributeValuePair> getPairsWithId() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = RoomType.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            attributeName = PostgresqlTableAttribute.toPostgresAttribute(fieldName);
            try {
                fieldValue = fields[i].get(this).toString();
                if (fieldValue == null) {
                    throw new Exception();
                }
                fieldStringValue = fieldValue.toString();
            } catch (Exception e) {
                continue;
            }
            result.add(new AttributeValuePair(attributeName, fieldStringValue));
        }
        return result;
    }
}
