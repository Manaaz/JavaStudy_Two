package messenger.chatServer.authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import messenger.chatServer.DbConnector;

public class DBAuthenticationService implements AuthenticationService{

    DbConnector dbConnector;

    public DBAuthenticationService() {
        this.dbConnector = new DbConnector();
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        try {
            dbConnector.connection();
            String userName =  dbConnector.getUsernameByLoginAndPassword(login, password);
            dbConnector.disconnect();
            return userName;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void startAuthentication() {
        System.out.println("Старт аутентификации");
    }

    @Override
    public void endAuthentication() {
        System.out.println("Конец аутентификации");
    }

    @Override
    public boolean renameUser(String login, String newUserName) {
        try {
            dbConnector.connection();
            dbConnector.updateUsername(login, newUserName);
            dbConnector.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}
