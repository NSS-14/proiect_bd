package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private final String driverClassName;
    private final String url;
    private final String user;
    private final String password;
    private DBConnectionState state;

    public DBConnectionManager(String url, String user, String password) {
        driverClassName = "org.postgresql.Driver";
        this.url = url;
        this.user = user;
        this.password = password;
        verifyTheState();
    }

    public DBConnectionManager(String url, String user, String password, String driverClassName) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.password = password;
        verifyTheState();
    }

    public Connection getConnection() {
        if (state != DBConnectionState.Established) {
            return null;
        }
        return generateConnection();
    }

    public DBConnectionState getState() {
        return state;
    }

    public void reverifyState() {
        verifyTheState();
    }

    private Connection generateConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            verifyTheState();
            return null;
        }
    }

    private void verifyTheState() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException driverNotFoundException) {
            state = DBConnectionState.DriverNotFound;
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            state = DBConnectionState.Established;
        } catch (SQLException connectionException) {
            state = DBConnectionState.InvalidAuthorizationOrWrongUrl;
        }
    }

    public enum DBConnectionState {
        Established,
        DriverNotFound,
        InvalidAuthorizationOrWrongUrl
    }
}
