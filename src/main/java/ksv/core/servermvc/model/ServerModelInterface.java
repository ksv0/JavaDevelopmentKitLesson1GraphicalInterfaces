package ksv.core.servermvc.model;

import ksv.core.clientmvc.services.ClientServices;
import ksv.core.clientmvc.services.ClientServicesInterface;

import java.util.List;

public interface ServerModelInterface {
    void saveLogs();

    String startServer();

    String stopServer();

    List<User> getOnlineUsers();

    String getLogsAsString();

    boolean isServerWorking();

    boolean checkLogin(String login);

    void addLog(String message);

    boolean checkUser(String login, String password);

    void addOnlineUser(String login, ClientServicesInterface client);

    void addUser(String login, String password);

    void saveUsers();

    User getUserByClient(ClientServicesInterface client);
}
