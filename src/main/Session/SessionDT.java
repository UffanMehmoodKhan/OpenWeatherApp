package main.Session; 

import main.UserInterface.UserInterface; 

import main.UserInterface.desktop; 

 

public class SessionDT implements Session { 

    UserInterface UIInterface; 

    public SessionDT(){ 
        System.out.println("Desktop is Operational"); 
        UIInterface = new desktop();
        this.getWelcomeScreen();
    }    
    @Override 
    public void getWelcomeScreen() 
    { 
        UIInterface.welcomeScreen(this); 
    } 
    @Override
    public void test(Session sessionInstance,String[] a ) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            System.out.println();
        }
    }
    @Override
    public void getWeatherLoc_LatInput(Session sessionInstance) {
        UIInterface.lat_locInputScreen(sessionInstance);
    }
    @Override
    public void getWeatherLoc_LatProcess(int [][] dataarr)
    {
        // this is where the data will be processed 
        // the return type of this method is also subject to change
        // after processing the data we will call UIinterface.displayscreen yet to be implemtned
    }
    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
       UIInterface.locationInputScreen(sessionInstance);
    }
 
}