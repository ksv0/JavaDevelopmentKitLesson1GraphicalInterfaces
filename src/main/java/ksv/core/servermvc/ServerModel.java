package ksv.core.servermvc;

import ksv.core.clientmvc.ClientServices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private boolean isServerWorking = false;
    private List<User> users;
    private List<String> logs;
    private List<User> onlineUsers ;

    public ServerModel() {
        users = loadUsers();
        logs = loadLogs();
        onlineUsers = new ArrayList<>();
    }

    private List<String> loadLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader("logs.txt"))) {
            List<String> logs = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
            return logs;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveLogs() {
        try (FileWriter fw = new FileWriter("logs.txt")) {
            fw.write(getLogsAsString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private List<User> loadUsers() {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader("users.txt"))) {
            List<User> users = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String login = parts[0];
                String password = parts[1];
                users.add(new User(login, password));
            }
            return users;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void addUser(String login, String password) {
        users.add(new User(login, password));
    }

    public boolean checkUser(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.checkPassword(password)) {
                return true;
            }
        }
        return false;
    }



    public String startServer() {
        String message = "";
        if (isServerWorking) {
            message = "Server already started";
            addLog(message);
            return message;
        }
        message = "Server started";
        addLog(message);
        isServerWorking = true;
        return message;
    }

    public String stopServer() {
        String message = "";
        if (!isServerWorking) {
            message = "Server already stopped";
            addLog(message);
            return message;

        }
        isServerWorking = false;
        message = "Server stopped";
        addLog(message);
        saveUsers();
        return message;}

    public void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("users.txt"))) {
            for (User user : users) {
                writer.write(user.getLogin() + ":" + user.getPassword() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getLogs() {
        return logs;
    }

    public String getLogsAsString() {
        return String.join("\n", logs).concat("\n");
    }

    public void addLog(String log) {
        logs.add(log);
    }

    public boolean checkLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void addOnlineUser(String login, ClientServices client) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                user.setCurrentClient(client);
                onlineUsers.add(user);
            }
        }
    }



    public List<User> getOnlineUsers() {
        return onlineUsers;
    }

    public User getUserByClient(ClientServices client) {
        for (User user : onlineUsers) {
            if (user.getCurrentClient().equals(client)) {
                return user;
            }
        }
        return null;
    }
}
