package ksv;


import ksv.core.clientmvc.ui.ClientUI;
import ksv.core.clientmvc.ui.ClientView;
import ksv.core.servermvc.ui.ServerUI;
import ksv.core.servermvc.ui.ServerView;

public class Main {
    public static void main(String[] args) {
       ServerView serverUI = new ServerUI();
        ClientView clientUI = new ClientUI(serverUI.getServices());
        ClientView clientUI1 = new ClientUI(serverUI.getServices());
    }
}