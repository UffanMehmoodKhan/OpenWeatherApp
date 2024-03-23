package main.UserInterface;

import main.Session.Session;

public interface UserInterface { 
    public void welcomeScreen(Session sessionInstance); 
    public void lat_locInputScreen(Session sessionInstance);
    public void displayscreen(Session sessionInstance);// it will have parameters that consist of data which is to be displayed
} 

