package ksv.core.clientmvc.services;

public interface ClientServicesInterface {
    boolean sendLogin(String login, String password);

    boolean isLoggedIn();

    void logout();

    void sendMessage(String message);

    void getMessage(String text);
}
