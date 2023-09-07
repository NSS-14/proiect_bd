package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Employee {
    public int id;
    public int salary;
    public Person person;

    public Employee() {
    }

    public Employee(int id, int salary, Person person) {
        this.id = id;
        this.salary = salary;
        this.person = person;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Employee.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals("id")) {
                continue;
            }
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "person": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(person.id);
                    break;
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
        Field[] fields = Employee.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "person": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(person.id);
                    break;
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
