package main.UserInterface;

import main.Session.Session;

public interface UserInterface { 
    public void welcomeScreen(Session sessionInstance); 
    public void lat_locInputScreen(Session sessionInstance);
    public void locationInputScreen(Session sessionInstance);
} 

