
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
        sessionInstance.getWeatherLoc_LatInput(sessionInstance);
    }

    @Override
    public void lat_locInputScreen(Session sessionInstance) 
    {
         //take 3 longitudes and 3 latitudes as input
        Scanner sc = new Scanner(System.in);
        int[][] dataarr = new int[3][2];
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Enter the latitude of location " + (i+1));
            dataarr[i][0] = sc.nextInt();
            System.out.println("Enter the longitude of location " + (i+1));
            dataarr[i][1] = sc.nextInt();
        }
        sessionInstance.getWeatherLoc_LatProcess(dataarr);  
    }
    
    
} 
 