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
       
    }
    @Override
    public void getWeatherLoc_LatInput(Session sessionInstance) {
        UIInterface.lat_locInputScreen(sessionInstance);
    }
    @Override
    public void getWeatherLoc_LatProcess(int [][] dataarr)
    {
        // this is where the data will be processed 
        // api call will be made here
        String[] weatherData = {
            "New York", "USA", "06:30 AM", "06:00 PM", "40.7128", "-74.0060",
            "Sunny", "Clear sky", "25°C", "28°C", "30°C", "20°C"
        };
        displayscreen(this, weatherData);
    }
    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
       UIInterface.locationInputScreen(sessionInstance);
    }
    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        
        // will process the data and then call the display screen
        
        String[] weatherData = {
            "New York", "USA", "06:30 AM", "06:00 PM", "40.7128", "-74.0060",
            "Sunny", "Clear sky", "25°C", "28°C", "30°C", "20°C"
        };
        // Api call will be made here

        this.displayscreen(this, weatherData);


    }
    @Override
    public void displayscreen(Session sessionInstance,String[]weatherarr) {
        UIInterface.displayscreen(sessionInstance,weatherarr);
    }
 
}