package db.helpers;

import db.dto.Record;
import db.dto.*;

import java.sql.Date;
import java.sql.Timestamp;

public class DBPopulator {
    public Department[] departments;
    public RoomType[] roomTypes;
    public Floor[] floors;
    public Diagnostic[] diagnostics;
    public Specialty[] specialties;
    public Person[] persons;
    public Address[] addresses;
    public Room[] rooms;
    public Employee[] employees;
    public Doctor[] doctors;
    public Record[] records;
    public Consultation[] consultations;
    public Surgery[] surgeries;

    public DBPopulator() {
        populateDTOLists();
    }

    private void populateDTOLists() {
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

    private void populateRoomType() {
        roomTypes = new RoomType[3];
        roomTypes[0] = new RoomType(0, "surgery");
        roomTypes[1] = new RoomType(0, "hospitalization");
        roomTypes[2] = new RoomType(0, "examination");
    }

    private void populateDepartment() {
        departments = new Department[10];
        departments[0] = new Department(0, "urology");
        departments[1] = new Department(0, "radiology");
        departments[2] = new Department(0, "cardiology");
        departments[3] = new Department(0, "anesthetics");
        departments[4] = new Department(0, "gynecology");
        departments[5] = new Department(0, "maternity");
        departments[6] = new Department(0, "neurology");
        departments[7] = new Department(0, "oncology");
        departments[8] = new Department(0, "ophthalmology");
        departments[9] = new Department(0, "orthopedics");
    }

    private void populateFloor() {
        floors = new Floor[3];
        floors[0] = new Floor(0, 0);
        floors[1] = new Floor(0, 1);
        floors[2] = new Floor(0, 2);
    }

    private void populateDiagnostic() {
        diagnostics = new Diagnostic[30];
        diagnostics[0] = new Diagnostic(0, "appendicitis");
        diagnostics[1] = new Diagnostic(0, "bronchitis");
        diagnostics[2] = new Diagnostic(0, "conjunctivitis");
        diagnostics[3] = new Diagnostic(0, "coronavirus");
        diagnostics[4] = new Diagnostic(0, "diabetes");
        diagnostics[5] = new Diagnostic(0, "epilepsy");
        diagnostics[6] = new Diagnostic(0, "flu");
        diagnostics[7] = new Diagnostic(0, "gastroenteritis");
        diagnostics[8] = new Diagnostic(0, "HIV");
        diagnostics[9] = new Diagnostic(0, "insomnia");
        diagnostics[10] = new Diagnostic(0, "kidney infection");
        diagnostics[11] = new Diagnostic(0, "lactose intolerance");
        diagnostics[12] = new Diagnostic(0, "migraine");
        diagnostics[13] = new Diagnostic(0, "nosebleed");
        diagnostics[14] = new Diagnostic(0, "obesity");
        diagnostics[15] = new Diagnostic(0, "pneumonia");
        diagnostics[16] = new Diagnostic(0, "restless legs syndrome");
        diagnostics[17] = new Diagnostic(0, "sinusitis");
        diagnostics[18] = new Diagnostic(0, "tinnitus");
        diagnostics[19] = new Diagnostic(0, "urinary tract infection");
        diagnostics[20] = new Diagnostic(0, "vertigo");
        diagnostics[21] = new Diagnostic(0, "warts and verrucas");
        diagnostics[22] = new Diagnostic(0, "yellow fever");
        diagnostics[23] = new Diagnostic(0, "gallstones");
        diagnostics[24] = new Diagnostic(0, "gout");
        diagnostics[25] = new Diagnostic(0, "hepatitis a");
        diagnostics[26] = new Diagnostic(0, "hepatitis b");
        diagnostics[27] = new Diagnostic(0, "hepatitis c");
        diagnostics[28] = new Diagnostic(0, "hyperhidrosis");
        diagnostics[29] = new Diagnostic(0, "indigestion");
    }

    private void populateAddress() {
        addresses = new Address[30];
        addresses[0] = new Address(0, "Romania", "Prahova", "Boldesti-Scaeni", "Bucovului", "10A");
        addresses[1] = new Address(0, "Romania", "Brasov", "Brasov", "Iuliu Maniu", "33");
        addresses[2] = new Address(0, "Romania", "Brasov", "Brasov", "Calea Fagarasului", "44");
        addresses[3] = new Address(0, "Romania", "Brasov", "Brasov", "Avram Iancu", "14");
        addresses[4] = new Address(0, "Romania", "Brasov", "Brasov", "De mijloc", "2");
        addresses[5] = new Address(0, "Romania", "Brasov", "Brasov", "Lunga", "87");
        addresses[6] = new Address(0, "Romania", "Brasov", "Brasov", "Paltinis", "3");
        addresses[7] = new Address(0, "Romania", "Brasov", "Brasov", "Romana", "6");
        addresses[8] = new Address(0, "Romania", "Brasov", "Brasov", "Nicolae Titulescu", "17");
        addresses[9] = new Address(0, "Romania", "Brasov", "Brasov", "Decebal", "20");
        addresses[10] = new Address(0, "Romania", "Brasov", "Brasov", "Neptun", "49");
        addresses[11] = new Address(0, "Romania", "Brasov", "Brasov", "Apollo", "3");
        addresses[12] = new Address(0, "Romania", "Brasov", "Brasov", "Venus", "11");
        addresses[13] = new Address(0, "Romania", "Brasov", "Brasov", "Soarelui", "4");
        addresses[14] = new Address(0, "Romania", "Brasov", "Brasov", "Cocorului", "10");
        addresses[15] = new Address(0, "Romania", "Brasov", "Brasov", "Artur Leiter", "15");
        addresses[16] = new Address(0, "Romania", "Brasov", "Brasov", "Poienilor", "23");
        addresses[17] = new Address(0, "Romania", "Brasov", "Brasov", "Panselelor", "39");
        addresses[18] = new Address(0, "Romania", "Brasov", "Brasov", "Carpatilor", "10A");
        addresses[19] = new Address(0, "Romania", "Brasov", "Brasov", "Barbu Lautaru", "8");
        addresses[20] = new Address(0, "Romania", "Brasov", "Brasov", "Zorilor", "52");
        addresses[21] = new Address(0, "Romania", "Brasov", "Brasov", "Muncitorilor", "44");
        addresses[22] = new Address(0, "Romania", "Prahova", "Ploiesti", "Fundeni", "28");
        addresses[23] = new Address(0, "Romania", "Prahova", "Ploiesti", "Municii", "21");
        addresses[24] = new Address(0, "Romania", "Prahova", "Ploiesti", "Praga", "4");
        addresses[25] = new Address(0, "Romania", "Prahova", "Ploiesti", "Italiana", "15A");
        addresses[26] = new Address(0, "Romania", "Prahova", "Ploiesti", "Cristianul", "16");
        addresses[27] = new Address(0, "Romania", "Prahova", "Ploiesti", "Penes Curcanul", "1");
        addresses[28] = new Address(0, "Romania", "Prahova", "Ploiesti", "Pacii", "2");
        addresses[29] = new Address(0, "Romania", "Prahova", "Ploiesti", "Facliei", "3");
    }

    private void populateSpecialty() {
        specialties = new Specialty[10];
        specialties[0] = new Specialty(0, "dermatology");
        specialties[1] = new Specialty(0, "neurology");
        specialties[2] = new Specialty(0, "pathology");
        specialties[3] = new Specialty(0, "pediatrics");
        specialties[4] = new Specialty(0, "psychiatry");
        specialties[5] = new Specialty(0, "surgery");
        specialties[6] = new Specialty(0, "urology");
        specialties[7] = new Specialty(0, "anesthesiology");
        specialties[8] = new Specialty(0, "immunology");
        specialties[9] = new Specialty(0, "radiology");
    }

    private void populatePerson() {
        persons = new Person[30];
        persons[0] = new Person(0, "5020306327289", "Stefan-Sebastian", "Neicu", Date.valueOf("2002-3-6"), "0737336788", addresses[0]);
        persons[1] = new Person(0, "4901120327290", "Andreea", "Nistor", Date.valueOf("1990-11-20"), "0744575466", addresses[1]);
        persons[2] = new Person(0, "4000812327291", "Sabina", "Craciun", Date.valueOf("2000-8-12"), "0724733065", addresses[2]);
        persons[3] = new Person(0, "5010811327292", "George", "Frone", Date.valueOf("2001-8-11"), "0741147722", addresses[3]);
        persons[4] = new Person(0, "4020710327293", "Paula-Alexandra", "Petre", Date.valueOf("2002-7-10"), "0721777884", addresses[4]);
        persons[5] = new Person(0, "5030709327294", "Serban-Vlad", "Murarasu", Date.valueOf("2003-7-9"), "0720819988", addresses[5]);
        persons[6] = new Person(0, "5040708327295", "Lucian-Marian", "Musca", Date.valueOf("2004-7-8"), "0744616049", addresses[6]);
        persons[7] = new Person(0, "5050707327296", "Ion-David", "Nicolae", Date.valueOf("2005-7-7"), "0744623164", addresses[7]);
        persons[8] = new Person(0, "5040706327297", "Pavel-Liviu", "Tiberiu", Date.valueOf("2004-7-6"), "0722554881", addresses[8]);
        persons[9] = new Person(0, "5020705327298", "Florin", "Dinu", Date.valueOf("2002-7-5"), "0741117497", addresses[9]);
        persons[10] = new Person(0, "4000704327299", "Stefania-Ioana", "Oprea", Date.valueOf("2000-7-4"), "0722141866", addresses[10]);
        persons[11] = new Person(0, "5900703327100", "Tudor-Gabriel", "Banica", Date.valueOf("1990-7-3"), "0726718493", addresses[11]);
        persons[12] = new Person(0, "4800702327101", "Ruxandra-Miruna", "Fronte", Date.valueOf("1980-7-2"), "0722365806", addresses[12]);
        persons[13] = new Person(0, "4710701327102", "Daria-Elena", "Branzea", Date.valueOf("1971-7-1"), "0721080859", addresses[13]);
        persons[14] = new Person(0, "5920507327103", "Darius-Viorel", "Lopataru", Date.valueOf("1992-5-7"), "0726921013", addresses[14]);
        persons[15] = new Person(0, "5930521327104", "Tiberiu-Alexandru", "Nuta", Date.valueOf("1993-5-21"), "0744244000", addresses[15]);
        persons[16] = new Person(0, "5940422327105", "Lazar-Florentin", "Baicoianu", Date.valueOf("1994-4-22"), "0720186301", addresses[16]);
        persons[17] = new Person(0, "5950411327106", "Alin-Alexandru", "Pildas", Date.valueOf("1995-4-11"), "0766404275", addresses[17]);
        persons[18] = new Person(0, "4960320327107", "Oana-Diana", "Radu", Date.valueOf("1996-3-20"), "0744361193", addresses[18]);
        persons[19] = new Person(0, "5970303327108", "Valentin-Adrian", "Crin", Date.valueOf("1997-3-3"), "0722649592", addresses[19]);
        persons[20] = new Person(0, "4980716327109", "Crina-Nicoleta", "Lungan", Date.valueOf("1998-7-16"), "0744569025", addresses[20]);
        persons[21] = new Person(0, "5990708327110", "Matei-Dan", "Traian", Date.valueOf("1999-7-8"), "0722624714", addresses[21]);
        persons[22] = new Person(0, "4850607327111", "Iulia-Ana", "Ososchi", Date.valueOf("1985-6-7"), "0744529744", addresses[22]);
        persons[23] = new Person(0, "5860506327112", "Andrei-Eusebiu", "Stan", Date.valueOf("1986-5-6"), "0744620500", addresses[23]);
        persons[24] = new Person(0, "4870406327113", "Iuliana-Maria", "Toma", Date.valueOf("1987-4-6"), "0723149404", addresses[24]);
        persons[25] = new Person(0, "4880405327114", "Rebecca-Daria", "Popescu", Date.valueOf("1988-4-5"), "0743146205", addresses[25]);
        persons[26] = new Person(0, "5890404327115", "Bogdan-Alin", "Nistor", Date.valueOf("1989-4-4"), "0722335150", addresses[26]);
        persons[27] = new Person(0, "4800403327116", "Alina-Ecaterina", "Ionescu", Date.valueOf("1980-4-3"), "0746064548", addresses[27]);
        persons[28] = new Person(0, "4810302327117", "Alina-Ioana", "Popa", Date.valueOf("1981-3-2"), "0788211670", addresses[28]);
        persons[29] = new Person(0, "5830101327118", "Ion-Gheorghe", "Dima", Date.valueOf("1983-1-1"), "0726270966", addresses[29]);
    }

    private void populateRoom() {
        rooms = new Room[10];
        rooms[0] = new Room(0, 30, "A1", roomTypes[1], departments[0], floors[0]);
        rooms[1] = new Room(0, 1, "S1", roomTypes[0], departments[1], floors[1]);
        rooms[2] = new Room(0, 1, "B2", roomTypes[2], departments[2], floors[0]);
        rooms[3] = new Room(0, 30, "A2", roomTypes[1], departments[3], floors[2]);
        rooms[4] = new Room(0, 30, "A3", roomTypes[1], departments[3], floors[0]);
        rooms[5] = new Room(0, 30, "A4", roomTypes[1], departments[2], floors[1]);
        rooms[6] = new Room(0, 30, "A5", roomTypes[1], departments[4], floors[1]);
        rooms[7] = new Room(0, 30, "A6", roomTypes[1], departments[4], floors[0]);
        rooms[8] = new Room(0, 30, "A7", roomTypes[1], departments[1], floors[1]);
        rooms[9] = new Room(0, 30, "A8", roomTypes[1], departments[0], floors[2]);
    }

    private void populateEmployee() {
        employees = new Employee[4];
        employees[0] = new Employee(0, 155000, persons[0]);
        employees[1] = new Employee(0, 90000, persons[11]);
        employees[2] = new Employee(0, 95000, persons[22]);
        employees[3] = new Employee(0, 150000, persons[16]);
    }

    private void populateDoctor() {
        doctors = new Doctor[2];
        Specialty[] specialtiesForDoc = new Specialty[2];
        specialtiesForDoc[0] = specialties[0];
        specialtiesForDoc[1] = specialties[1];
        doctors[0] = new Doctor(0, employees[0], specialtiesForDoc);
        specialtiesForDoc = new Specialty[3];
        specialtiesForDoc[0] = specialties[3];
        specialtiesForDoc[1] = specialties[4];
        specialtiesForDoc[2] = specialties[5];
        doctors[1] = new Doctor(0, employees[3], specialtiesForDoc);
    }

    private void populateRecord() {
        records = new Record[10];
        Diagnostic[] diagnosticsForRecord = new Diagnostic[1];
        diagnosticsForRecord[0] = diagnostics[3];
        records[0] = new Record(0, Date.valueOf("2023-09-01"), rooms[4], diagnosticsForRecord, persons[11], doctors[0]);
        records[1] = new Record(0, Date.valueOf("2023-09-02"), rooms[4], diagnosticsForRecord, persons[12], doctors[0]);
        records[2] = new Record(0, Date.valueOf("2023-09-03"), rooms[4], diagnosticsForRecord, persons[13], doctors[1]);
        records[3] = new Record(0, Date.valueOf("2023-08-01"), rooms[4], diagnosticsForRecord, persons[14], doctors[1]);
        records[4] = new Record(0, Date.valueOf("2023-08-11"), rooms[4], diagnosticsForRecord, persons[15], doctors[1]);
        records[5] = new Record(0, Date.valueOf("2023-08-11"), rooms[4], diagnosticsForRecord, persons[16], doctors[1]);
        records[6] = new Record(0, Date.valueOf("2023-08-13"), rooms[4], diagnosticsForRecord, persons[17], doctors[1]);
        records[7] = new Record(0, Date.valueOf("2023-08-21"), rooms[4], diagnosticsForRecord, persons[18], doctors[0]);
        records[8] = new Record(0, Date.valueOf("2023-08-21"), rooms[4], diagnosticsForRecord, persons[19], doctors[0]);
        records[9] = new Record(0, Date.valueOf("2023-08-30"), rooms[4], diagnosticsForRecord, persons[20], doctors[0]);
    }

    private void populateConsultation() {
        consultations = new Consultation[3];
        consultations[0] = new Consultation(0, Timestamp.valueOf("2023-8-22 11:30:01"), Timestamp.valueOf("2023-8-23 11:35:00"), Timestamp.valueOf("2023-8-23 11:30:00"), persons[27], doctors[0]);
        consultations[1] = new Consultation(0, Timestamp.valueOf("2023-8-22 15:20:03"), Timestamp.valueOf("2023-8-24 11:35:00"), Timestamp.valueOf("2023-8-24 11:30:00"), persons[28], doctors[1]);
        consultations[2] = new Consultation(0, Timestamp.valueOf("2023-8-22 16:10:14"), Timestamp.valueOf("2023-8-25 11:35:00"), Timestamp.valueOf("2023-8-25 11:30:00"), persons[28], doctors[1]);
    }

    private void populateSurgery() {
        surgeries = new Surgery[1];
        surgeries[0] = new Surgery(0, Timestamp.valueOf("2023-8-27 15:10:00"), records[0], rooms[1]);
    }
}
