package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Room {
    public int id;
    public int capacity;
    public String label;
    public RoomType roomType;
    public Department department;
    public Floor floor;

    public Room() {
    }

    public Room(int id, int capacity, String label, RoomType roomType, Department department, Floor floor) {
        this.id = id;
        this.capacity = capacity;
        this.label = label;
        this.roomType = roomType;
        this.department = department;
        this.floor = floor;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Room.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals("id")) {
                continue;
            }
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "roomType": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(roomType.id);
                    break;
                }
                case "department": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(department.id);
                    break;
                }
                case "floor": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(floor.id);
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
        Field[] fields = Room.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "roomType": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(roomType.id);
                    break;
                }
                case "department": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(department.id);
                    break;
                }
                case "floor": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(floor.id);
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
