package db.helpers;

import db.helperstructures.SimpleFKConstraintTuple;
import db.helperstructures.TableAttributeTuple;

import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {
    private TableAttributeTuple[] attributes;
    private SimpleFKConstraintTuple[] fkConstraints;
    private Statement statement;

    public TableCreator(Statement statement) {
        this.statement = statement;
    }

    public void createTables() throws SQLException {
        createTheFirstTables();
        createTheSecondTables();
        createTheThirdTables();
        createTheForthTables();
        createTheFifthTables();
        createTheSixthTables();
        createTheSeventhTables();
    }

    private void createRoomType() throws SQLException {
        attributes = new TableAttributeTuple[1];
        attributes[0] = new TableAttributeTuple("type", "VARCHAR(30)", false);
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithoutDependencies("roomtype", attributes)
        );
    }

    private void createDepartment() throws SQLException {
        attributes = new TableAttributeTuple[1];
        attributes[0] = new TableAttributeTuple("name", "VARCHAR(30)", false);
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithoutDependencies("department", attributes)
        );
    }

    private void createFloor() throws SQLException {
        attributes = new TableAttributeTuple[1];
        attributes[0] = new TableAttributeTuple("floor", "INTEGER", false);
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithoutDependencies("floor", attributes)
        );
    }

    private void createAddress() throws SQLException {
        attributes = new TableAttributeTuple[8];
        attributes[0] = new TableAttributeTuple("country", "VARCHAR(30)", false);
        attributes[1] = new TableAttributeTuple("county", "VARCHAR(30)", false);
        attributes[2] = new TableAttributeTuple("city", "VARCHAR(30)", false);
        attributes[3] = new TableAttributeTuple("street", "VARCHAR(30)", false);
        attributes[4] = new TableAttributeTuple("number", "VARCHAR(10)", false);
        attributes[5] = new TableAttributeTuple("building", "VARCHAR(10)", true);
        attributes[6] = new TableAttributeTuple("stair", "VARCHAR(10)", true);
        attributes[7] = new TableAttributeTuple("apartment", "VARCHAR(10)", true);
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithoutDependencies("address", attributes)
        );
    }

    private void createDiagnostic() throws SQLException {
        attributes = new TableAttributeTuple[1];
        attributes[0] = new TableAttributeTuple("name", "VARCHAR(30)", false);
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithoutDependencies("diagnostic", attributes)
        );
    }

    private void createSpecialty() throws SQLException {
        attributes = new TableAttributeTuple[1];
        attributes[0] = new TableAttributeTuple("name", "VARCHAR(30)", false);
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithoutDependencies("specialty", attributes)
        );
    }

    private void createTheFirstTables() throws SQLException {
        createRoomType();
        createDepartment();
        createFloor();
        createDiagnostic();
        createAddress();
        createSpecialty();
    }

    private void createRoom() throws SQLException {
        attributes = new TableAttributeTuple[5];
        attributes[0] = new TableAttributeTuple("capacity", "INTEGER", false);
        attributes[1] = new TableAttributeTuple("label", "VARCHAR(5)", false);
        attributes[2] = new TableAttributeTuple("roomtype_id", "INTEGER", false);
        attributes[3] = new TableAttributeTuple("department_id", "INTEGER", false);
        attributes[4] = new TableAttributeTuple("floor_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[3];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_roomtype", "roomtype_id", "roomtype", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_department", "department_id", "department", "id");
        fkConstraints[2] = new SimpleFKConstraintTuple
                ("fk_floor", "floor_id", "floor", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("room", attributes, fkConstraints)
        );
    }

    private void createPerson() throws SQLException {
        attributes = new TableAttributeTuple[6];
        attributes[0] = new TableAttributeTuple("pin", "VARCHAR(30)", false);
        attributes[1] = new TableAttributeTuple("second_name", "VARCHAR(30)", false);
        attributes[2] = new TableAttributeTuple("first_name", "VARCHAR(30)", false);
        attributes[3] = new TableAttributeTuple("date_of_birth", "DATE", false);
        attributes[4] = new TableAttributeTuple("phone_number", "VARCHAR(30)", false);
        attributes[5] = new TableAttributeTuple("address_id", "INTEGER", true);
        fkConstraints = new SimpleFKConstraintTuple[1];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_address", "address_id", "address", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("person", attributes, fkConstraints)
        );
    }

    private void createTheSecondTables() throws SQLException {
        createRoom();
        createPerson();
    }

    private void createEmployee() throws SQLException {
        attributes = new TableAttributeTuple[2];
        attributes[0] = new TableAttributeTuple("salary", "INTEGER", false);
        attributes[1] = new TableAttributeTuple("person_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[1];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_person", "person_id", "person", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("employee", attributes, fkConstraints)
        );
    }

    private void createTheThirdTables() throws SQLException {
        createEmployee();
    }

    private void createDoctor() throws SQLException {
        attributes = new TableAttributeTuple[1];
        attributes[0] = new TableAttributeTuple("employee_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[1];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_employee", "employee_id", "employee", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("doctor", attributes, fkConstraints)
        );
    }

    private void createTheForthTables() throws SQLException {
        createDoctor();
    }

    private void createRecord() throws SQLException {
        attributes = new TableAttributeTuple[4];
        attributes[0] = new TableAttributeTuple("date_of_creation", "DATE", false);
        attributes[1] = new TableAttributeTuple("person_id", "INTEGER", false);
        attributes[2] = new TableAttributeTuple("doctor_id", "INTEGER", false);
        attributes[3] = new TableAttributeTuple("room_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[3];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_person", "person_id", "person", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_doctor", "doctor_id", "doctor", "id");
        fkConstraints[2] = new SimpleFKConstraintTuple
                ("fk_room", "room_id", "room", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("record", attributes, fkConstraints)
        );
    }

    private void createConsultation() throws SQLException {
        attributes = new TableAttributeTuple[5];
        attributes[0] = new TableAttributeTuple("date_of_creation", "TIMESTAMP", false);
        attributes[1] = new TableAttributeTuple("date_of_completion", "TIMESTAMP", true);
        attributes[2] = new TableAttributeTuple("scheduled_date", "TIMESTAMP", false);
        attributes[3] = new TableAttributeTuple("person_id", "INTEGER", false);
        attributes[4] = new TableAttributeTuple("doctor_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[2];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_person", "person_id", "person", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_doctor", "doctor_id", "doctor", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("consultation", attributes, fkConstraints)
        );
    }

    private void createTheFifthTables() throws SQLException {
        createRecord();
        createConsultation();
    }

    private void createSurgery() throws SQLException {
        attributes = new TableAttributeTuple[3];
        attributes[0] = new TableAttributeTuple("date_of_creation", "TIMESTAMP", false);
        attributes[1] = new TableAttributeTuple("record_id", "INTEGER", false);
        attributes[2] = new TableAttributeTuple("room_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[2];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_record", "record_id", "record", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_room", "room_id", "room", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("surgery", attributes, fkConstraints)
        );
    }

    private void createTheSixthTables() throws SQLException {
        createSurgery();
    }

    private void createDiagnosticRecord() throws SQLException {
        attributes = new TableAttributeTuple[2];
        attributes[0] = new TableAttributeTuple("diagnostic_id", "INTEGER", false);
        attributes[1] = new TableAttributeTuple("record_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[2];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_diagnostic", "diagnostic_id", "diagnostic", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_record", "record_id", "record", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("diagnosticrecord", attributes, fkConstraints)
        );
    }

    private void createSpecialtyDoctor() throws SQLException {
        attributes = new TableAttributeTuple[2];
        attributes[0] = new TableAttributeTuple("specialty_id", "INTEGER", false);
        attributes[1] = new TableAttributeTuple("doctor_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[2];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_specialty", "specialty_id", "specialty", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_doctor", "doctor_id", "doctor", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("specialtydoctor", attributes, fkConstraints)
        );
    }

    private void createDoctorSurgery() throws SQLException {
        attributes = new TableAttributeTuple[2];
        attributes[0] = new TableAttributeTuple("doctor_id", "INTEGER", false);
        attributes[1] = new TableAttributeTuple("surgery_id", "INTEGER", false);
        fkConstraints = new SimpleFKConstraintTuple[2];
        fkConstraints[0] = new SimpleFKConstraintTuple
                ("fk_doctor", "doctor_id", "doctor", "id");
        fkConstraints[1] = new SimpleFKConstraintTuple
                ("fk_surgery", "surgery_id", "surgery", "id");
        statement.executeUpdate(
                QueriesGenerator.queryToCreateSimpleTableWithFKDependency("doctorsurgery", attributes, fkConstraints)
        );
    }

    private void createTheSeventhTables() throws SQLException {
        createDiagnosticRecord();
        createSpecialtyDoctor();
        createDoctorSurgery();
    }
}
