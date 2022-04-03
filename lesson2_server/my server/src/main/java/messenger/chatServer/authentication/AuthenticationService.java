package messenger.chatServer.authentication;

import java.sql.SQLException;

public interface AuthenticationService {
    String getUsernameByLoginAndPassword(String login, String password);

    void startAuthentication();
    void endAuthentication();
    boolean renameUser(String login, String newUserName);
}
