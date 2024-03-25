
package main.UserInterface; 
import java.util.Scanner;
import main.Session.Session;

public class terminal implements UserInterface
 { 
    @Override
    public void welcomeScreen(Session sessionInstance)
    { 
        System.out.println("Welcome to the Weather App");
        System.out.println("Please enter the location and latitude of the place you want to know the weather of");
        // complete if else will be implemtned 
        // sessionInstance.getLoc_LatInpu(sessionInstance,0);
    }

    @Override
    public void lat_locInputScreen(Session sessionInstance,int choice) 
    {
         //take 3 longitudes and 3 latitudes as input
        Scanner sc = new Scanner(System.in);
        double[][] dataarr = new double[3][2];
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Enter the latitude of location " + (i+1));
            dataarr[i][0] = sc.nextInt();
            System.out.println("Enter the longitude of location " + (i+1));
            dataarr[i][1] = sc.nextInt();
        }
        sc.close();
        
        sessionInstance.getWeatherLoc_LatProcess(dataarr);  
    }

    @Override
    public void locationInputScreen(Session sessionInstance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'locationInputScreen'");
    }

    @Override
    public void displayscreen(Session sessionInstance, String[][]weatherData,int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayscreen'");
    }
    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayAirPollutionScreen'");
    }
    
    
} 
 