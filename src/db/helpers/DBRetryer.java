package db.helpers;

import db.DBConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DBRetryer {
    public static boolean retryToCloseConnection(Connection connection, int attempts) {
        if (connection == null) {
            return false;
        }
        for (int i = 1; i <= attempts; ++i) {
            try {
                connection.close();
                return true;
            } catch (SQLException sqlException) {
                System.out.println(String.format("Retry to close connection, attempt %s.", String.valueOf(i)));
                if (!sleepCurrentThreadOneSecond()) {
                    return false;
                }
            }
        }
        return false;
    }

    public static Connection retryToGetConnection(DBConnectionManager dbcm, int attempts) {
        if (dbcm == null) {
            return null;
        }
        for (int i = 1; i <= attempts; ++i) {
            if (dbcm.getState() != DBConnectionManager.DBConnectionState.Established) {
                System.out.println(String.format("Retry to get connection, attempt %s.", String.valueOf(i)));
                if (!sleepCurrentThreadOneSecond()) {
                    return null;
                }
                dbcm.reverifyState();
                continue;
            }
            return dbcm.getConnection();
        }
        return null;
    }

    private static boolean sleepCurrentThreadOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
            return false;
        }
        return true;
    }
}
