package messenger.chatServer;

import java.sql.*;

public class DbConnector {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    public void connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Messenger", "root", "Qa7Fg_9&zxC");
        //connection = DriverManager.getConnection("jdbc:mysql//localhost:3306/Messenger","root","Qa7Fg_9&zxC");
        stmt = connection.createStatement();
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public String getUsernameByLoginAndPassword(String login, String password) throws SQLException {
        rs = stmt.executeQuery(String.format("SELECT password, userName FROM users WHERE login = '%s'", login));

        if (rs.isClosed()) {
            return null;
        }

        if(rs.next()){
            String passwordDB = rs.getString(1);
            String userName = rs.getString(2);

            return ((passwordDB != null) && (passwordDB.equals(password))) ? userName : null;
        }
//        String userName = rs.getString("userName");
//      String passwordDB = rs.getString("password");

        return null;

    }

    public void updateUsername(String login, String userName) throws SQLException {
        stmt.executeUpdate(String.format("UPDATE users SET userName = '%s' WHERE login = '%s'", userName, login));
    }

}
