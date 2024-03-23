package main.UserInterface; 

import main.Session.Session;
import main.UserInterface.Screens.welcomeScreen; 
import main.UserInterface.Screens.lat_locInputScreen; 

public class desktop implements UserInterface { 
    @Override
    public void welcomeScreen(Session sessionInstance) { 
        new welcomeScreen(sessionInstance); 
    }

    @Override
    public void lat_locInputScreen(Session sessionInstance) {
         new lat_locInputScreen(sessionInstance);
        
    } 
} 
