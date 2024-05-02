package main.UserInterface; 
import java.util.InputMismatchException;
import java.util.Scanner;

import main.API.API;
import main.Database.DB;
import main.Session.Session;

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
        "Minimum Temp",
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
        "Current Min Temp",
		"Current Max Temp",
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
    DB DB_cache;
    
    public terminal(DB db){ 
        DB_cache = db;
    }

    
    @Override
    public void welcomeScreen(Session sessionInstance){ 
        int choice = 0; Scanner sc = new Scanner(System.in); sc.reset();
        String[][] arr = new String[3][2];
       
		boolean status = true;
		while(status == true){
		System.out.print("\033[H\033[2J");  
		System.out.flush(); sc.reset();
		System.out.println("\t\t\tWelcome to the OpenWeatherMap API Interface!");
        System.out.println("\n\t\t\tChoose the following options:");
        System.out.println("\n\n\t\t[1] Weather Report\t\t[2]Air Pollution Report");
        System.out.println("\n\t\t[3] 5-Day Forecast\t\t[4]Exit");

        try {
            choice = sc.nextInt(); System.out.println(choice); sc.reset(); 
            
        } catch (InputMismatchException e) {
            //System.out.println("InputMismatchException: Please enter a valid integer.");
            sc.nextLine(); // Consume the invalid input to prevent an infinite loop
        }
        if(choice == 4){
			status = false;
		}
        else{ 
            sessionInstance.getLoc_LatInpu(sessionInstance, choice);
        }
        //sc.close();
    }
}

    @Override
    public void lat_locInputScreen(Session sessionInstance,int choice) 
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        System.out.println("\n\tChoose the following options for Weather Report:");
        System.out.println("\n[1] Lat/Lon\t\t[2]City, Country");
        Scanner sc = new Scanner(System.in); 
        double lat = 1; double lon = 1; String loc = "Lahore";
        String[] weather = null;
        int temp =0;
        try {
            temp = sc.nextInt(); sc.reset(); 
            
        } catch (InputMismatchException e) {
            sc.nextLine(); // Consume the invalid input to prevent an infinite loop
            lat_locInputScreen(sessionInstance, choice); 
        } 
        String locs[] = new String[1];
        if(temp == 1){
            System.out.println("Enter lat: ");
            try {
                lat = sc.nextDouble();  sc.reset(); 
                
            } catch (InputMismatchException e) {
                sc.nextLine(); // Consume the invalid input to prevent an infinite loop
                this.welcomeScreen(sessionInstance);
            } 

            System.out.println("Enter lon: ");
            try {
                lon = sc.nextDouble();  sc.reset(); 
                
            } catch (InputMismatchException e) {
                sc.nextLine(); // Consume the invalid input to prevent an infinite loop
                this.welcomeScreen(sessionInstance);            } 
            //weather = DB_cache.GetWeather(lat, lon);
            double arr[][] = new double[1][2];
            arr[0][0]= lat;
            arr[0][1] = lon;

            if (choice==1) {
            sessionInstance.getWeatherLoc_LatProcess(arr);
            }
            else if (choice ==2) {
                sessionInstance.getAirPollutionLoc_LatProcess(arr);
            }
            else if (choice == 3)
            {
                sessionInstance.get5DayForecastDataProcess(arr);
            }
        }
        else if(temp == 2){
            System.out.println("Enter location: "); 
            try {
                loc = sc.next();  sc.reset(); 
                locs[0] = loc;
            } catch (InputMismatchException e) {
                sc.nextLine(); // Consume the invalid input to prevent an infinite loop
                this.welcomeScreen(sessionInstance);
            }
            if (choice==1) {
                sessionInstance.getWeatherLocationProcess(locs);
                }
                else if (choice == 3)
                {
                sessionInstance.get5DayForecastDataProcess(locs[0]);
                }
        }
        else{
            System.out.println("Invalid choice. Please try again.");
            this.welcomeScreen(sessionInstance);
            }
           
        
    }
    // weather screen
    @Override
    public void displayscreen(Session sessionInstance, String[][]weatherData,int count) {
        
        System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		System.out.println("\n\tWeather Index Report\n\n");
        Scanner sc = new Scanner(System.in); 
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < weatherData[i].length; j++) {
                System.out.println(weather_titles[j] + ": " + weatherData[i][j]);
            }
            System.out.println();
        }
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        welcomeScreen(sessionInstance);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        sc.close();
    }
    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {
		
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		System.out.println("\n\tAir Pollution Index Report\n\n");
        Scanner sc = new Scanner(System.in); 
        // data reprezentation 
        for(int i = 0; i < arr[0].length - 1; i++){
			System.out.println(AirPoll_titles[i] +": "+arr[0][i]);
		}
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        welcomeScreen(sessionInstance);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        sc.close();
    }
    public void display5DayForecastScreen(Session sessionInstance,String[][]arr,int count)
    {
        System.out.print("\033[H\033[2J");  
		System.out.flush(); 
		System.out.println("\n\t5 day weather Forecast Report\n\n");
        Scanner sc = new Scanner(System.in); 
        for(int i = 0; i < 4; i++){
            System.out.println(forecast_prelim[i] + ": "+arr[0][i]);
        } System.out.println("\n");
        
        int day = 1;
        for(int i = 4; i < arr[0].length; i++){
            if((i + 1) % 5 == 0){System.out.println("\nDay ["+day+"] "); day++;}
            System.out.println(arr[0][i]);
        }
        System.out.println("Press Enter to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        welcomeScreen(sessionInstance);
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        sc.close();
    }
    // not implemented
	@Override
    public void ForecastLocationInputScreen(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'ForecastLocationInputScreen'");
    }
    @Override
    public void ForecastLocationbyNameInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'ForecastLocationbyNameInput'");
    }
    
    @Override
    public void locationInputScreen(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'locationInputScreen'");
    }

    
} 