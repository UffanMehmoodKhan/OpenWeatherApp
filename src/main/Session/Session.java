package main.Session; 

import main.API.API; 

public interface Session { 
    public static API APIInterface = null; 
    //------------------------
    public void getWelcomeScreen(); 
    //------------------------
    public void test(Session sessionInstance,String[] a); // will be used for testing purpose
    //------------------------
    public void getWeatherLoc_LatInput(Session sessionInstance);
    public void getWeatherLoc_LatProcess(int[][] dataarra);
    //------------------------
    public void getWeatherLocationInput(Session sessionInstance);
    //Methods for API calls ----> made to DB first ----> then to API calls ----> then back ----------------< 

} 