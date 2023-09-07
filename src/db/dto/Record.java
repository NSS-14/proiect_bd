package db.dto;

import db.helpers.PostgresqlTableAttribute;
import db.helperstructures.AttributeValuePair;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;

public class Record {
    public int id;
    public Date dateOfCreation;
    public Room room;
    public Diagnostic[] diagnostics;
    public Person person;
    public Doctor doctor;

    public Record() {
    }

    public Record(int id, Date dateOfCreation, Room room, Person person, Doctor doctor) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.room = room;
        this.person = person;
        this.doctor = doctor;
    }

    public Record(int id, Date dateOfCreation, Room room, Diagnostic[] diagnostics, Person person, Doctor doctor) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
        this.room = room;
        this.diagnostics = diagnostics;
        this.person = person;
        this.doctor = doctor;
    }

    public ArrayList<AttributeValuePair> getPairs() {
        String fieldStringValue;
        String attributeName;
        Object fieldValue;
        Field[] fields = Record.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals("id")) {
                continue;
            }
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "diagnostics": {
                    continue;
                }
                case "room": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(room.id);
                    break;
                }
                case "person": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(person.id);
                    break;
                }
                case "doctor": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(doctor.id);
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
        Field[] fields = Record.class.getDeclaredFields();
        ArrayList<AttributeValuePair> result = new ArrayList<>();
        for (int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            switch (fieldName) {
                case "diagnostics": {
                    continue;
                }
                case "room": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(room.id);
                    break;
                }
                case "person": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(person.id);
                    break;
                }
                case "doctor": {
                    attributeName = PostgresqlTableAttribute.toPostgresFKAttribute(fieldName);
                    fieldStringValue = String.valueOf(doctor.id);
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
