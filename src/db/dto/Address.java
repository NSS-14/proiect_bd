package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Address {
    public int id;
    public String country;
    public String county;
    public String city;
    public String street;
    public String number;
    public String building;
    public String stair;
    public String apartment;

    public Address() {
    }

    public Address(int id, String country, String county, String city, String street, String number) {
        this.id = id;
        this.country = country;
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
        this.building = null;
        this.stair = null;
        this.apartment = null;
    }

    public Address(int id, String country, String county, String city, String street, String number, String building, String stair, String apartment) {
        this.id = id;
        this.country = country;
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
        this.building = building;
        this.stair = stair;
        this.apartment = apartment;
    }

    public static String[] getFieldsName() {
        Field[] fields = Address.class.getDeclaredFields();
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
            result[i][1] = resultSet.getString("country");
            result[i][2] = resultSet.getString("county");
            result[i][3] = resultSet.getString("city");
            result[i][4] = resultSet.getString("street");
            result[i][5] = resultSet.getString("number");
            result[i][6] = resultSet.getString("building");
            result[i][7] = resultSet.getString("stair");
            result[i][8] = resultSet.getString("apartment");
            ++i;
        }
        return result;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Address.class.getDeclaredFields();
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
        Field[] fields = Address.class.getDeclaredFields();
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
