package main.Session; 

 

import main.UserInterface.UserInterface; 

import main.UserInterface.desktop; 

 

public class SessionDT implements Session { 

    UserInterface UIInterface; 

    public SessionDT(){ 

        System.out.println("Desktop is Operational"); 

        UIInterface = new desktop(); 

        
    } 

 

  
    @Override 

    public void getWelcomeScreen() 

    { 

        UIInterface.welcomeScreen(); 

    } 

    @Override 
    public void process() 
    { 
        // all methods should be executed here as per user specs, with exception handling
        // boolean status = true;
        // while (status == true) { 
        
		//} 
        SessionDT deskkptopSession = new SessionDT(); 

        deskkptopSession.getWelcomeScreen(); 
    } 
} 

 