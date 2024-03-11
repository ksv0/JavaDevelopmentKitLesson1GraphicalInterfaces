package ksv.core.servermvc;

import ksv.core.clientmvc.ClientServices;

public class User {
    private static long counterID ;
    private final long id = ++counterID;
    private final String login;
    private String password;
    private ClientServices currentClient;

    public ClientServices getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(ClientServices currentClient) {
        this.currentClient = currentClient;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword(String password) {
        if (password.equals(this.password)) {
            return true;
        } else {
            return false;
        }
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
