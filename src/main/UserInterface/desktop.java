package main.UserInterface; 
import main.Session.Session;
import main.UserInterface.Screens.welcomeScreen; 
public class desktop implements UserInterface{ 
    public desktop() { 
    } 
    @Override
    public void welcomeScreen(Session sessionInstance) 
    { 
     new welcomeScreen(sessionInstance); 
    } 
} 

 

 