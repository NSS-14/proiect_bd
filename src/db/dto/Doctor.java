package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Doctor {
    public int id;
    public Employee employee;
    public Specialty[] specialities;

    public Doctor() {
    }

    public Doctor(int id, Employee employee) {
        this.id = id;
        this.employee = employee;
    }

    public Doctor(int id, Employee employee, Specialty[] specialities) {
        this.id = id;
        this.employee = employee;
        this.specialities = specialities;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Doctor.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals("id")) {
                continue;
            }
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "employee": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(employee.id);
                    break;
                }
                case "specialities": {
                    continue;
                }
                default: {
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
                }
            }
            result.add(new AttributeValuePair(attributeName, fieldStringValue));
        }
        return result;
    }

    public ArrayList<AttributeValuePair> getPairsWithId() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Doctor.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "employee": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(employee.id);
                    break;
                }
                case "specialities": {
                    continue;
                }
                default: {
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
                }
            }
            result.add(new AttributeValuePair(attributeName, fieldStringValue));
        }
        return result;
    }
}
