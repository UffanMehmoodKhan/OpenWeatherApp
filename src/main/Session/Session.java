package main.Session; 
import main.API.API;

  //Methods for API calls ----> made to DB first ----> then to API calls ----> then back ----------------< 

public interface Session
{ 

    public static API APIInterface = new API();  
    API OpenAI = new API();
    //------------------------
    public void getLoc_LatInpu(Session sessionInstance,int choice); // location by cords
    public void getWeatherLocationInput(Session sessionInstance); // choice needs to be implemtned
    public void GetForecastLocationInput(Session sessionInstance); // forceast location input
    public void GetForecastLocationbyNameInput(Session sessionInstance); // forecast location input by name
    //------------------------
    public void displayWeatherscreen(Session sessionInstance,String[][]arr,int count); // display weather screen
    public void displayAirPollutionScreen(Session sessionInstance,String[][]arr,int count); // display air pollution screen
    public void display5DayForecastScreen(Session sessionInstance,String[][]arr,int count); // display 5 day forecast screen
    public void getWelcomeScreen(); 
    //------------------------
    public void getWeatherLoc_LatProcess(double[][] dataarra); // weather by cords
    public void getWeatherLocationProcess(String[] dataarra); // weather by location
    public void getAirPollutionLoc_LatProcess(double[][] dataarr); // air pollution by cords
    public void get5DayForecastDataProcess(double[][] dataarr); // 5 day forecast by cords
    public void get5DayForecastDataProcess(String cityName);// 5 day forecast by city name

  
} 

