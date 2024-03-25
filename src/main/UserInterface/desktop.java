package main.UserInterface; 

import main.Session.Session;
import main.UserInterface.Screens.welcomeScreen; 
import main.UserInterface.Screens.lat_locInputScreen; 
import main.UserInterface.Screens.LocationInputScreen;
import main.UserInterface.Screens.WeatherInfoDisplayScreen; 


public class desktop implements UserInterface 
{ 
    @Override
    public void welcomeScreen(Session sessionInstance) 
    { 
        new welcomeScreen(sessionInstance); 
    }

    @Override
    public void lat_locInputScreen(Session sessionInstance) {
         new lat_locInputScreen(sessionInstance);
    }

    @Override
    public void locationInputScreen(Session sessionInstance) {
        new LocationInputScreen(sessionInstance);
    }

    @Override
    public void displayscreen(Session sessionInstance,String[][]weatherarr,int count) {
        new WeatherInfoDisplayScreen(sessionInstance, weatherarr,count);
    } 
} 
