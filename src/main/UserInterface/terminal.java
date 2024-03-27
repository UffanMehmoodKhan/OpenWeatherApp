
package main.UserInterface; 
import java.util.Scanner;
import main.Session.*;
import main.API.*;

public class terminal implements UserInterface
 {  String[] weather_titles = {
        "City Name",
        "Country Name",
        "Local Time",
        "Sunrise Time",
        "Sunset Time",
        "Latitude",
        "Longitude",
        "Weather",
        "Weather Description",
        "Temperature",
        "Feels Like",
        "Maximum Temp"
    };

    String[] AirPoll_titles = {
        "Latitude",
        "Longitude",
        "Local Time",
        "Air Quality Index",
        "Concentration of CO",
        "Concentration of NO",
        "Concentration of NO2",
        "Concentration of O3",
        "Concentration of SO2",
        "Concentration of PM2.5",
        "Concentration of PM10",
        "Concentration of NH3"
    };

    String[] forecast_prelim = {
        "City Name",
        "Country Name",
        "Latitude",
        "Longitude",
        "Time Stamp",
        "Current Max Temp",
        "Current Min Temp",
        "Weather",
        "Weather Description"
    };

    String[] forecast_titles = {
        "Timestamp",
        "Maximum Temperature",
        "Minimum Temperature",
        "Weather",
        "Weather Description"
    };
    
    API OpenAI = new API();
    public terminal(){ }
    @Override
    public void welcomeScreen(Session sessionInstance)
    { 
        int choice; Scanner sc = new Scanner(System.in); sc.reset();
        String[][] arr = new String[3][2];
        
        // if(choice == (1)){
        //     this.lat_locInputScreen(sessionInstance, 1);
        //        try{
        //         Thread.sleep(1000);
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        //     this.welcomeScreen(sessionInstance);
        // }
        // else if(choice == 2){
        //     this.displayAirPollutionScreen(sessionInstance, arr, choice);
        // }
        // else if(choice == 3){
        //     this.display_forecast(sessionInstance, arr, choice);
        // }
        // else if(choice == 4){
     
        // }
       
		boolean status = true;
		while(status == true){
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		System.out.println("\tWelcome to the OpenWeatherMap API Interface!");
        System.out.println("\n\tChoose the following options:");
        System.out.println("\n[1] Weather Report\t\t[2]Air Pollution Report");
        System.out.println("\n[3] 5-Day Forecast\t\t[4]Exit");

        choice = sc.nextInt(); System.out.println(choice); sc.reset(); 
       
        if (choice == 1){
            System.out.print("\033[H\033[2J");  
		    System.out.flush(); 
            System.out.println("\n\tChoose the following options for Weather Report:");
            System.out.println("\n[1] Lat/Lon\t\t[2]City, Country");
            sc.reset(); choice = sc.nextInt();
            this.lat_locInputScreen(sessionInstance, choice);
        }
        else if (choice == 2){
            System.out.print("\033[H\033[2J");  
		    System.out.flush(); 
            System.out.println("\n\tAir Pollution Index Report\n");
            this.displayAirPollutionScreen(sessionInstance,  arr, choice);
        }
        else if (choice == 3)
        {
            System.out.print("\033[H\033[2J");  
		    System.out.flush(); 
            this.display_forecast(sessionInstance, arr, choice);
        }
        else {
			status = false;
		}
        
        // complete if else will be implemented 
        // sessionInstance.getLoc_LatInpu(sessionInstance,0);
        //sc.close();
    }
}

    @Override
    public void lat_locInputScreen(Session sessionInstance,int choice) 
    {
        Scanner sc = new Scanner(System.in); 
        double lat; double lon; String loc;
        
        String[] weather = null;
        if(choice == 1){
            System.out.println("Enter lat: "); lat = sc.nextDouble(); 
            System.out.println("Enter lon: ");lon = sc.nextDouble();
            weather = OpenAI.getCurrentWeather(lat, lon);
        }
        else if(choice == 2){
            System.out.println("Enter location: "); loc = sc.next(); 
            weather = OpenAI.getCurrentWeather("London");
        }
		
        for(int i = 0; i < weather.length - 1; i++){
			System.out.println(weather_titles[i] +": "+weather[i]);
		}
        
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
           // e.printStackTrace();
        }
        //this.welcomeScreen(sessionInstance);
         //take 3 longitudes and 3 latitudes as input
        // Scanner sc = new Scanner(System.in);
        // double[][] dataArr = new double[3][2];
        // for(int i = 0; i < 3; i++)
        // {
        //     System.out.println("Enter the latitude of location " + (i+1));
        //     dataArr[i][0] = sc.nextInt();
        //     System.out.println("Enter the longitude of location " + (i+1));
        //     dataArr[i][1] = sc.nextInt();
        // }
        // sc.close();
        
        // sessionInstance.getWeatherLoc_LatProcess(dataArr);  
    }

    @Override
    public void locationInputScreen(Session sessionInstance) {
        
        throw new UnsupportedOperationException("Unimplemented method 'locationInputScreen'");
    }

    @Override
    public void displayscreen(Session sessionInstance, String[][]weatherData,int count) {
        
        throw new UnsupportedOperationException("Unimplemented method 'displayscreen'");
    }
    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {

        Scanner sc = new Scanner(System.in); 
        double lat; double lon; String loc;
        System.out.println("\nEnter latitude: "); lat = sc.nextDouble(); 
        System.out.println("Enter longitude: "); lon = sc.nextDouble();
        
        String[] air_poll = OpenAI.getAirQuality(lat, lon);
        
        for(int i = 0; i < air_poll.length - 1; i++){
			System.out.println(AirPoll_titles[i] +": "+air_poll[i]);
		}
        
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //throw new UnsupportedOperationException("Unimplemented method 'displayAirPollutionScreen'");
    }

    public void display_forecast(Session sessionInstance, String[][] arr, int count){
        Scanner sc = new Scanner(System.in); 
        double lat; double lon; String loc;
        
        String[] forecast = null;
        
        System.out.println("Enter lat: "); lat = sc.nextDouble(); 
        System.out.println("Enter lon: ");lon = sc.nextDouble();
        forecast = OpenAI.get5DayForecast((double)lat, (double)lon);
        System.out.println("\n");
    
        for(int i = 0; i < 9; i++){
            System.out.println(forecast_prelim[i] + ": "+forecast[i]);
        } System.out.println("\n");
        
        int day = 1;
        for(int i = 9; i < forecast.length; i++){
            if((i + 1) % 5 == 0){System.out.println("\nDay ["+day+"] "); day++;}
            System.out.println(forecast[i]);
        }

        

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
        //throw new UnsupportedOperationException("Unimplemented method 'forecast screen'");
    }
    @Override
    public void ForecastLocationInputScreen(Session sessionInstance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ForecastLocationInputScreen'");
    }
    @Override
    public void ForecastLocationbyNameInput(Session sessionInstance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ForecastLocationbyNameInput'");
    }
    public void display5DayForecastScreen(Session sessionInstance,String[][]arr,int count)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display5DayForecastScreen'");
    }
    
} 
 