package main.Session; 

import main.API.API;

public interface Session { 
    public static API APIInterface = new API(); 
    //------------------------
    public void getWelcomeScreen(); 
    //------------------------
    public void test(Session sessionInstance,String[] a); // will be used for testing purpose
    //------------------------
    public void getLoc_LatInpu(Session sessionInstance,int choice); // it will take the session instance and choice as input
    public void getWeatherLoc_LatProcess(double[][] dataarra);
    //------------------------
    public void getWeatherLocationInput(Session sessionInstance);
    public void getWeatherLocationProcess(String[] dataarra);
    //------------------------
    public void displayWeatherscreen(Session sessionInstance,String[][]arr,int count); // it will have parameters that consist of data which is to be displayed
    // display air poolution screen
    public void displayAirPollutionScreen(Session sessionInstance,String[][]arr,int count);
    //------------------------
    public void getAirPollutionLoc_LatProcess(double[][] dataarr);
    
    

    //Methods for API calls ----> made to DB first ----> then to API calls ----> then back ----------------< 

} 