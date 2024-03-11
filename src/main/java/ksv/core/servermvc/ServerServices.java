package ksv.core.servermvc;

import ksv.core.clientmvc.ClientServices;
import ksv.core.ui.ServerView;

public class ServerServices {
    ServerModel model;
    ServerView view;

    public ServerServices(ServerView view) {
        this.view = view;
        model = new ServerModel();
    }

    public void saveLogs() {
        model.saveLogs();
    }

    public void startServer() {
        view.addLog(model.startServer());

    }

    public void stopServer() {
        logoutAllClients();
        view.addLog(model.stopServer());
        saveLogs();
    }

    private void logoutAllClients() {
        for (User user : model.getOnlineUsers()) {
            user.getCurrentClient().logout();
        }
    }

    public String getLogsAsString() {
        return model.getLogsAsString();
    }

    public boolean loginUser(String login, String password, ClientServices client) {
        String message;

        if (!model.isServerWorking()) {
            throw new RuntimeException("Server is not running");
        }
        if (model.checkLogin(login)) {
            message = "Login: " + login + " try to login";
            view.addLog(message);
            model.addLog(message);
            saveLogs();
            if (model.checkUser(login, password)) {
                message = "Login: " + login + " login successful";
                model.addOnlineUser(login, client);
                view.addLog(message);
                model.addLog(message);
                mail(login + " joined.");
                saveLogs();
                return true;
            } else {
                message = "Login: " + login + " login failed";
                view.addLog(message);
                model.addLog(message);
                saveLogs();
                return false;
            }
        } else {
            message = "Login: " + login + " try to register";
            model.addUser(login, password);
            model.addOnlineUser(login, client);
            view.addLog(message);
            mail(login + " joined.");
            model.addLog(message);
            model.saveUsers();
            saveLogs();
            return true;
        }
    }

    public void sendMessage(String text, ClientServices client) {
        if (model.isServerWorking()) {
            User user = model.getUserByClient(client);
            if (user != null) {
                String s = String.format("%s: %s", user.getLogin(), text);
                model.addLog(s);
                view.addLog(s);
                mail(s);
            }
        }
    }

    private void mail(String text) {
        for (User user : model.getOnlineUsers()) {
            user.getCurrentClient().getMessage(text);
        }
    }
}
