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
        String[] weatherData ;
        weatherData = APIInterface.getWeather(0.0, 0.0); // Sample data
        displayscreen(this, weatherData);
    }
    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
       UIInterface.locationInputScreen(sessionInstance);
    }
    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        
        // will process the data and then call the display screen
        
         String[] weatherData; 
        // Api call 
        weatherData = APIInterface.getWeather("Lahore");
        // System.err.println("Data received from API: ");
        this.displayscreen(this, weatherData);


    }
    @Override
    public void displayscreen(Session sessionInstance,String[]weatherarr) {
        UIInterface.displayscreen(sessionInstance,weatherarr);
    }
 
}