package main.Session; 

 

import main.UserInterface.UserInterface; 

import main.UserInterface.terminal; 

 

public class SessionT implements Session { 

     
    UserInterface UIInterface = new terminal(); 

    public SessionT(){ 

        System.out.println("Terminal is Operational"); 

        this.getWelcomeScreen();
    } 

 

    @Override 

    public void getWelcomeScreen() 

    { 
      UIInterface.welcomeScreen(this);

    } 

    @Override  

    public void process() 

    { 

		System.out.println("This will be the control mechanism of the terminal, where all of the tasks will occur"); 
        
         

        // all methods should be executed here as per user specs, with exception handling 

        // boolean status = true; 

        // while (status == true) { 

        // 

        //  // TODO 

        // 

        // } 

    }



    @Override
    public void test() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    } 


    } 

    