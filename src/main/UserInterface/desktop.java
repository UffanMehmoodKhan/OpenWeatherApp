package main.UserInterface; 

import main.Session.Session;
import main.UserInterface.Screens.welcomeScreen; 
import main.UserInterface.Screens.lat_locInputScreen; 
import main.UserInterface.Screens.LocationInputScreen;
import main.UserInterface.Screens.WeatherInfoDisplayScreen; 
import main.UserInterface.Screens.airPollutionDisplayScreen;
import main.UserInterface.Screens.weatherForcastDisplayScreen;

public class desktop implements UserInterface 
{ 
    @Override
    public void welcomeScreen(Session sessionInstance) 
    { 
        new welcomeScreen(sessionInstance); 
    }

    @Override
    public void lat_locInputScreen(Session sessionInstance,int choice) {
        new lat_locInputScreen(sessionInstance,choice);
    }

    @Override
    public void locationInputScreen(Session sessionInstance) {
        new LocationInputScreen(sessionInstance);
    }

    @Override
    public void displayscreen(Session sessionInstance,String[][]weatherarr,int count) {
        new WeatherInfoDisplayScreen(sessionInstance, weatherarr,count);
    } 
    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {
        new airPollutionDisplayScreen(sessionInstance, arr, count);
    }
    @Override
    public void display5DayForecastScreen(Session sessionInstance, String[][] arr, int count) {
       new weatherForcastDisplayScreen(sessionInstance, arr, count);
    }
} 
