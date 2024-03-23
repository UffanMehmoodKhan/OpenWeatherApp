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
    
    @Override
    public void lat_locInputScreen(Session sessionInstance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lat_locInputScreen'");
    }    

} 