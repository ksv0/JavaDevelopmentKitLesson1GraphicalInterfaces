package ksv.core.servermvc.services;

import ksv.core.clientmvc.services.ClientServices;
import ksv.core.clientmvc.services.ClientServicesInterface;

public interface ServerServicesInterface {
    String getLogsAsString();

    void startServer();

    void stopServer();

    boolean loginUser(String login, String password, ClientServicesInterface clientServices);

    void sendMessage(String text, ClientServicesInterface clientServices);
}
