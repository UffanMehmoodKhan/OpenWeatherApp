package main.Session; 

import main.API.API;

public interface Session { 
    public static API APIInterface = new API(); 
    //------------------------
    public void getWelcomeScreen(); 
    //------------------------
    public void test(Session sessionInstance,String[] a); // will be used for testing purpose
    //------------------------
    public void getWeatherLoc_LatInput(Session sessionInstance);
    public void getWeatherLoc_LatProcess(int[][] dataarra);
    //------------------------
    public void getWeatherLocationInput(Session sessionInstance);
    public void getWeatherLocationProcess(String[] dataarra);
    //------------------------
    public void displayscreen(Session sessionInstance,String[]arr); // it will have parameters that consist of data which is to be displayed
    

    //Methods for API calls ----> made to DB first ----> then to API calls ----> then back ----------------< 

} 