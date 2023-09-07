package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Surgery {
    public int id;
    public Timestamp dateOfCreation;
    public Record record;
    public Room room;
    public Doctor[] doctors;

    public Surgery() {
    }

    public Surgery(int id, Timestamp dateOfCreation, Record record, Room room) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.record = record;
        this.room = room;
    }

    public Surgery(int id, Timestamp dateOfCreation, Record record, Room room, Doctor[] doctors) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.record = record;
        this.room = room;
        this.doctors = doctors;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Surgery.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals("id")) {
                continue;
            }
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "record": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(record.id);
                    break;
                }
                case "room": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(room.id);
                    break;
                }
                case "doctors": {
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
        Field[] fields = Surgery.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "record": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(record.id);
                    break;
                }
                case "room": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(room.id);
                    break;
                }
                case "doctors": {
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
