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
    public void test(Session sessionInstance,int[][] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }







    @Override
    public void getWeatherLoc_LatInput(Session sessionInstance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherLoc_LatInput'");
    }



    @Override
    public void getWeatherLoc_LatProcess(int [][] dataarr) {
        // this is where the data will be processed 
        // the return type of this method is also subject to change
        // after processing the data we will call UIinterface.displayscreen
    } 


    } 

    