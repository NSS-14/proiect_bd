package db.helpers;

import db.DBOperator;
import db.dto.Record;
import db.dto.*;
import db.helperstructures.AttributeValuePair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TablePopulator {
    Statement statement;
    private DBPopulator dbPopulator;

    public TablePopulator(Statement statement) {
        this.statement = statement;
    }

    public void populateTables() throws SQLException {
        dbPopulator = new DBPopulator();
        populateRoomType();
        populateDepartment();
        populateFloor();
        populateDiagnostic();
        populateAddress();
        populateSpecialty();
        populatePerson();
        populateRoom();
        populateEmployee();
        populateDoctor();
        populateRecord();
        populateConsultation();
        populateSurgery();
    }

    private void populateRoomType() throws SQLException {
        AttributeValuePair[] pairs;
        RoomType[] rows = dbPopulator.roomTypes;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[1];
            pairs[0] = new AttributeValuePair("type", String.format("'%s'", rows[i].type));
            DBOperator.insertRowInATable("roomtype", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("roomtype", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateDepartment() throws SQLException {
        AttributeValuePair[] pairs;
        Department[] rows = dbPopulator.departments;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[1];
            pairs[0] = new AttributeValuePair("name", String.format("'%s'", rows[i].name));
            DBOperator.insertRowInATable("department", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("department", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateFloor() throws SQLException {
        AttributeValuePair[] pairs;
        Floor[] rows = dbPopulator.floors;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[1];
            pairs[0] = new AttributeValuePair("floor", String.format("'%s'", rows[i].floor));
            DBOperator.insertRowInATable("floor", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("floor", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateDiagnostic() throws SQLException {
        AttributeValuePair[] pairs;
        Diagnostic[] rows = dbPopulator.diagnostics;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[1];
            pairs[0] = new AttributeValuePair("name", String.format("'%s'", rows[i].name));
            DBOperator.insertRowInATable("diagnostic", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("diagnostic", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateAddress() throws SQLException {
        AttributeValuePair[] pairs;
        Address[] rows = dbPopulator.addresses;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[8];
            pairs[0] = new AttributeValuePair("country", String.format("'%s'", rows[i].country));
            pairs[1] = new AttributeValuePair("county", String.format("'%s'", rows[i].county));
            pairs[2] = new AttributeValuePair("city", String.format("'%s'", rows[i].city));
            pairs[3] = new AttributeValuePair("street", String.format("'%s'", rows[i].street));
            pairs[4] = new AttributeValuePair("number", String.format("'%s'", rows[i].number));
            pairs[5] = new AttributeValuePair("building", String.format("'%s'", rows[i].building));
            pairs[6] = new AttributeValuePair("stair", String.format("'%s'", rows[i].stair));
            pairs[7] = new AttributeValuePair("apartment", String.format("'%s'", rows[i].apartment));
            DBOperator.insertRowInATable("address", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("address", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateSpecialty() throws SQLException {
        AttributeValuePair[] pairs;
        Specialty[] rows = dbPopulator.specialties;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[1];
            pairs[0] = new AttributeValuePair("name", String.format("'%s'", rows[i].name));
            DBOperator.insertRowInATable("specialty", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("specialty", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populatePerson() throws SQLException {
        AttributeValuePair[] pairs;
        Person[] rows = dbPopulator.persons;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[6];
            pairs[0] = new AttributeValuePair("pin", String.format("'%s'", rows[i].pin));
            pairs[1] = new AttributeValuePair("second_name", String.format("'%s'", rows[i].secondName));
            pairs[2] = new AttributeValuePair("first_name", String.format("'%s'", rows[i].firstName));
            pairs[3] = new AttributeValuePair("date_of_birth", String.format("'%s'", rows[i].dateOfBirth));
            pairs[4] = new AttributeValuePair("phone_number", String.format("'%s'", rows[i].phoneNumber));
            pairs[5] = new AttributeValuePair("address_id", String.format("'%d'", rows[i].address.id));
            DBOperator.insertRowInATable("person", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("person", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateRoom() throws SQLException {
        AttributeValuePair[] pairs;
        Room[] rows = dbPopulator.rooms;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[5];
            pairs[0] = new AttributeValuePair("capacity", String.format("'%s'", rows[i].capacity));
            pairs[1] = new AttributeValuePair("label", String.format("'%s'", rows[i].label));
            pairs[2] = new AttributeValuePair("roomtype_id", String.format("'%d'", rows[i].roomType.id));
            pairs[3] = new AttributeValuePair("department_id", String.format("'%d'", rows[i].department.id));
            pairs[4] = new AttributeValuePair("floor_id", String.format("'%d'", rows[i].floor.id));
            DBOperator.insertRowInATable("room", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("room", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateEmployee() throws SQLException {
        AttributeValuePair[] pairs;
        Employee[] rows = dbPopulator.employees;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[2];
            pairs[0] = new AttributeValuePair("salary", String.format("'%s'", rows[i].salary));
            pairs[1] = new AttributeValuePair("person_id", String.format("'%d'", rows[i].person.id));
            DBOperator.insertRowInATable("employee", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("employee", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateDoctor() throws SQLException {
        AttributeValuePair[] pairs;
        Doctor[] rows = dbPopulator.doctors;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[1];
            pairs[0] = new AttributeValuePair("employee_id", String.format("'%d'", rows[i].employee.id));
            DBOperator.insertRowInATable("doctor", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("doctor", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateRecord() throws SQLException {
        AttributeValuePair[] pairs;
        Record[] rows = dbPopulator.records;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[4];
            pairs[0] = new AttributeValuePair("date_of_creation", String.format("'%s'", rows[i].dateOfCreation));
            pairs[1] = new AttributeValuePair("person_id", String.format("'%d'", rows[i].person.id));
            pairs[2] = new AttributeValuePair("doctor_id", String.format("'%d'", rows[i].doctor.id));
            pairs[3] = new AttributeValuePair("room_id", String.format("'%d'", rows[i].room.id));
            DBOperator.insertRowInATable("record", pairs, statement);
            ResultSet resultSet;
            String debugQuery = QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("record", rows[i].getPairs());

            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("record", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateConsultation() throws SQLException {
        AttributeValuePair[] pairs;
        Consultation[] rows = dbPopulator.consultations;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[5];
            pairs[0] = new AttributeValuePair("date_of_creation", String.format("'%s'", rows[i].dateOfCreation));
            pairs[1] = new AttributeValuePair("date_of_completion", String.format("'%s'", rows[i].dateOfCompletion));
            pairs[2] = new AttributeValuePair("scheduled_date", String.format("'%s'", rows[i].scheduledDate));
            pairs[3] = new AttributeValuePair("person_id", String.format("'%s'", rows[i].person.id));
            pairs[4] = new AttributeValuePair("doctor_id", String.format("'%s'", rows[i].doctor.id));
            DBOperator.insertRowInATable("consultation", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("consultation", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }

    private void populateSurgery() throws SQLException {
        AttributeValuePair[] pairs;
        Surgery[] rows = dbPopulator.surgeries;
        for (int i = 0; i < rows.length; ++i) {
            pairs = new AttributeValuePair[3];
            pairs[0] = new AttributeValuePair("date_of_creation", String.format("'%s'", rows[i].dateOfCreation));
            pairs[1] = new AttributeValuePair("room_id", String.format("'%s'", rows[i].room.id));
            pairs[2] = new AttributeValuePair("record_id", String.format("'%s'", rows[i].record.id));
            DBOperator.insertRowInATable("surgery", pairs, statement);
            ResultSet resultSet;
            resultSet = statement.executeQuery(QueriesGenerator.queryToGetIdOfTheRowFromATableBasedOnAllAttributesValues("surgery", rows[i].getPairs()));
            while (resultSet.next()) {
                rows[i].id = resultSet.getInt("id");
                break;
            }
        }
    }
}
