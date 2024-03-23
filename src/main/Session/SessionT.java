package main.Session; 

 

import java.util.Scanner;

import main.UserInterface.UserInterface; 

import main.UserInterface.terminal; 

 

public class SessionT implements Session 
{ 
    UserInterface UIInterface = new terminal(); 
    public SessionT()
    { 
        System.out.println("Terminal is Operational"); 
        this.getWelcomeScreen();
    } 

    @Override 
    public void getWelcomeScreen()
    { 
      UIInterface.welcomeScreen(this);
    } 

    @Override
    public void test(Session sessionInstance,int[][] a)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }



    @Override
    public void getWeatherLoc_LatInput(Session sessionInstance) 
    {
        UIInterface.lat_locInputScreen(sessionInstance);
    }

    @Override
    public void getWeatherLoc_LatProcess(int [][] dataarr)
    {
        //display the data in the terminal
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Latitude of location " + (i+1) + " is " + dataarr[i][0]);
            System.out.println("Longitude of location " + (i+1) + " is " + dataarr[i][1]);
        }
    } 


    } 

    