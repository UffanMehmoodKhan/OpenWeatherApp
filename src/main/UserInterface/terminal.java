package main.UserInterface;

import main.Session.Session;

public class terminal implements UserInterface{ 

     

    public terminal() { 
    } 
    public void readAPIkey(){ 

        //TODO 

    }
    @Override
    public void welcomeScreen(Session sessionInstance) {
        System.out.println("welcome to the terminal");
    }    

} 