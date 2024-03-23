package main.Session; 

 

import main.UserInterface.UserInterface; 

import main.UserInterface.terminal; 

 

public class SessionT implements Session 
{ 

    public SessionT()
    { 
        System.out.println("Terminal is Operational"); 

        UserInterface UIInterface = new terminal(); 
        
    } 

 

    @Override 
    public void getWelcomeScreen() 
    { 

        UIInterface.welcomeScreenT(); 

    } 

    @Override  

    public void process() 

    { 

		System.out.println("This will be the control mechanism of the terminal, where all of the tasks will occur"); 

        SessionT TerminalSession = new SessionT(); 

        TerminalSession.getWelcomeScreen(); 

        // all methods should be executed here as per user specs, with exception handling 

        // boolean status = true; 

        // while (status == true) { 

        // 

        //  // TODO 

        // 

        // } 

    } 


    } 

    