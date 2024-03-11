package ksv.core.clientmvc;

import ksv.core.servermvc.ServerServices;
import ksv.core.ui.ClientView;

import java.util.Objects;

public class ClientServices {
    ServerServices serverServices;
    ClientView view;
    boolean loggedIn = false;

    public ClientServices(ClientView view, ServerServices serverServices) {
        this.serverServices = serverServices;
        this.view = view;
    }


    public boolean sendLogin(String login, String password) {
        try {
            loggedIn = serverServices.loginUser(login, password, this);
        } catch (Exception e) {
            view.addLog("Server is not running");
            return false;
        }
        if (loggedIn) {
            view.addLog("Login successful");

            return true;
        } else {
            view.addLog("Login failed");
            return false;
        }

    }

    public void logout() {
        loggedIn = false;
        view.addLog("Logout");
        view.toTitle();

    }



    public void sendMessage(String text) {
        if (loggedIn) {
            serverServices.sendMessage(text, this);
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientServices that = (ClientServices) o;
        return Objects.equals(serverServices, that.serverServices)  && Objects.equals(view, that.view);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverServices, view);
    }

    public void getMessage(String text) {
        view.addLog(text);
    }
}
