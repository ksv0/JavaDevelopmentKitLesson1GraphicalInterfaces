package ksv;


import ksv.core.ui.ClientView;
import ksv.core.ui.ServerView;

public class Main {
    public static void main(String[] args) {
       ServerView serverView = new ServerView();
        ClientView clientView = new ClientView(serverView.getServices());
        ClientView clientView1 = new ClientView(serverView.getServices());
    }
}