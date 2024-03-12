package ksv.core.servermvc.model;

import ksv.core.clientmvc.services.ClientServices;
import ksv.core.clientmvc.services.ClientServicesInterface;

public class User {
    private static long counterID ;
    private final long id = ++counterID;
    private final String login;
    private String password;
    private ClientServicesInterface currentClient;

    public ClientServicesInterface getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(ClientServicesInterface currentClient) {
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
