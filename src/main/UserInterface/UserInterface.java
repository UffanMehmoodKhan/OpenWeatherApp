package main.UserInterface;

import main.Session.Session;

public interface UserInterface { 
    public void welcomeScreen(Session sessionInstance); 
    public void lat_locInputScreen(Session sessionInstance,int choice);
    public void locationInputScreen(Session sessionInstance);
    public void displayscreen(Session sessionInstance,String[][]weatherData,int count);// it will have parameters that consist of data which is to be displayed
    public void displayAirPollutionScreen(Session sessionInstance,String[][]arr,int count);
    public void display5DayForecastScreen(Session sessionInstance,String[][]arr,int count);
} 

