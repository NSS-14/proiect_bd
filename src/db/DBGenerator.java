package db;

import db.helpers.DBRetryer;
import db.helpers.QueriesGenerator;
import db.helpers.TableCreator;
import db.helpers.TablePopulator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBGenerator {
    private static volatile DBGenerator instance;
    private final String dbName;
    private String url;
    private String port;
    private String user;
    private String password;
    private DBGeneratorState state;
    private DBConnectionManager dbcm;

    private DBGenerator() {
        state = DBGeneratorState.DBConnectionNotEstablished;
        dbName = "hospitalrocure";
    }

    public static DBGenerator getInstance() {
        DBGenerator result = instance;
        if (result == null) {
            synchronized (DBGenerator.class) {
                result = instance;
                if (result == null) {
                    result = instance = new DBGenerator();
                }
            }
        }
        return result;
    }

    public void generateDB() throws IllegalStateException {
        synchronized (DBGenerator.class) {
            if (state != DBGeneratorState.Ready) {
                throw new IllegalStateException(String.format("The state is in a illegal state: %s.", state.toString()));
            }

            state = DBGeneratorState.InProgress;
            try {
                createDB();
                createTables();
                populateTables();
            } catch (SQLException sqlException) {
                System.out.println(sqlException);
                state = DBGeneratorState.DBConnectionNotEstablished;
                return;
            }

            state = DBGeneratorState.Done;
        }
    }

    public DBGeneratorState getState() {
        return state;
    }

    public void reverifyState() {
        updateTheState();
    }

    public void setAll(String port, String user, String password) {
        initialize(port, user, password);
    }

    public String getUrl() {
        return url;
    }

    public DBConnectionManager getDbcm() {
        return dbcm;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        initialize(port, user, password);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        initialize(user, password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        initialize(user, password);
    }

    public String getDbName() {
        return dbName;
    }

    public String getDBUrl() {
        return url + dbName;
    }

    private void initialize(String port, String user, String password) {
        this.url = String.format("jdbc:postgresql://localhost:%s/", port);
        this.port = port;
        this.user = user;
        this.password = password;
        dbcm = new DBConnectionManager(url, user, password);
        updateTheState();
    }

    private void initialize(String user, String password) {
        this.user = user;
        this.password = password;
        dbcm = new DBConnectionManager(url, user, password);
        updateTheState();
    }

    private void updateTheState() {
        if (!verifyConnection()) {
            return;
        }
        verifyTheExistenceOfDB();
    }

    private boolean verifyConnection() {
        boolean result = true;
        Connection connection = DBRetryer.retryToGetConnection(dbcm, 10);
        if (connection == null) {
            result = false;
        }
        if (!DBRetryer.retryToCloseConnection(connection, 10)) {
            result = false;
        }
        if (!result) {
            state = DBGeneratorState.DBConnectionNotEstablished;
        }
        return result;
    }

    private void verifyTheExistenceOfDB() {
        Connection connection = DBRetryer.retryToGetConnection(dbcm, 10);
        if (connection == null) {
            state = DBGeneratorState.DBConnectionNotEstablished;
            return;
        }
        state = DBGeneratorState.Ready;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(QueriesGenerator.queryForTheExistenceOfTheDBNamed(dbName));
            while (rs.next()) {
                if (rs.getBoolean(1)) {
                    dbcm = new DBConnectionManager(getDBUrl(), user, password);
                    state = DBGeneratorState.Done;
                    break;
                }
            }
            statement.close();
            if (!DBRetryer.retryToCloseConnection(connection, 10)) {
                throw new SQLException("Connection lost.");
            }
        } catch (SQLException sqlException) {
            state = DBGeneratorState.DBConnectionNotEstablished;
        }
    }

    private void createDB() throws SQLException {
        Connection connection = DBRetryer.retryToGetConnection(dbcm, 10);
        if (connection == null) {
            throw new SQLException("Connection lost.");
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate(QueriesGenerator.queryToCreateDBWithNameAndOwner(dbName, user));
        statement.close();
        if (!DBRetryer.retryToCloseConnection(connection, 10)) {
            throw new SQLException("Connection lost.");
        }
        dbcm = new DBConnectionManager(getDBUrl(), user, password);
    }

    private void createTables() throws SQLException {
        Connection connection = DBRetryer.retryToGetConnection(dbcm, 10);
        if (connection == null) {
            throw new SQLException("Connection lost.");
        }
        Statement statement = connection.createStatement();
        TableCreator tableCreator = new TableCreator(statement);
        tableCreator.createTables();
        statement.close();
        if (!DBRetryer.retryToCloseConnection(connection, 10)) {
            throw new SQLException("Connection lost.");
        }
    }

    private void populateTables() throws SQLException {
        Connection connection = DBRetryer.retryToGetConnection(dbcm, 10);
        if (connection == null) {
            throw new SQLException("Connection lost.");
        }
        Statement statement = connection.createStatement();
        TablePopulator tablePopulator = new TablePopulator(statement);
        tablePopulator.populateTables();
        statement.close();
        if (!DBRetryer.retryToCloseConnection(connection, 10)) {
            throw new SQLException("Connection lost.");
        }
    }

    public enum DBGeneratorState {
        Done,
        Ready,
        InProgress,
        DBConnectionNotEstablished
    }
}
