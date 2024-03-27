package main.Session; 

import main.Database.*;
import main.UserInterface.*; 
public class SessionDT implements Session { 

    UserInterface UIInterface; 
    //DB SQL_cache; DB txt_cache;
    DB DB_cache;

    public SessionDT(DB db)
    { 
        System.out.println("Desktop is Operational"); 
        UIInterface = new desktop(); 
       //SQL_cache = new SQL(); txt_cache = new txt();
        DB_cache = db;
        this.getWelcomeScreen();
        System.out.println("Desktop is Operational");
    }   

    @Override 
    public void getWelcomeScreen() { 
        UIInterface.welcomeScreen(this); 
    } 

    @Override
    public void test(Session sessionInstance,String[] a ) {
        // Method implementation
    }

    @Override
    public void getLoc_LatInpu(Session sessionInstance, int choice) {
        UIInterface.lat_locInputScreen(sessionInstance, choice);
    }

    @Override
    public void getWeatherLoc_LatProcess(double[][] dataarr) {
        if (dataarr == null || dataarr.length == 0) {
            // Handle null or empty input array
            return;
        }

        int count = dataarr.length;
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < dataarr[i].length; j++) {
                System.out.print(dataarr[i][j] + " ");
            }
            System.out.println();
        }

        String[][] arr = new String[count][];
        for (int x = 0; x < count; x++) 
        {   
            String[] weatherData = DB_cache.GetWeather(dataarr[x][0],dataarr[x][1]);
            arr[x] = weatherData;
        }
        this.displayWeatherscreen(this, arr, count);
    }

    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
       UIInterface.locationInputScreen(sessionInstance);
    }

    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        if (dataarra == null || dataarra.length == 0) {
            // Handle null or empty input array
            return;
        }
        int count = dataarra.length;
        System.out.println(count);
        for(int z = 0; z < count; z++) {
            System.out.println(dataarra[z]);
        }
        String[][] arr = new String[count][];
        // This is where the data will be processed
        // API call will be made here
        for (int i = 0; i < count ; i++) 
        {
           // String[] weatherData = APIInterface.getCurrentWeather(dataarra[i]);
            String[] weatherData = DB_cache.GetWeather(dataarra[i]);
            arr[i] = weatherData;
        }

       
        this.displayWeatherscreen(this, arr, count);
    }
      
    @Override
    public void displayWeatherscreen(Session sessionInstance,String[][] weatherarr,int count){
        UIInterface.displayscreen(sessionInstance,weatherarr,count);
    }

    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][]arr,int count) {
     UIInterface.displayAirPollutionScreen(sessionInstance, arr,count);   
    }

    @Override
    public void getAirPollutionLoc_LatProcess(double[][] dataarr) {
       
        if (dataarr == null || dataarr.length == 0) {
            // Handle null or empty input array
            return;
        }

        int count = dataarr.length; // only for the first location
        System.out.println(count);
        String[][] arr = new String[count][];
        for (int x = 0; x < count; x++) {   
            String[] airPollutionData = DB_cache.GetAirPoll(dataarr[x][0],dataarr[x][1]);
            arr[x] = airPollutionData;
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        
        this.displayAirPollutionScreen(this, arr, count);
    }
    @Override
    public void get5DayForecastDataProcess(double[][] dataarr) {
        if (dataarr == null || dataarr.length == 0) {
            // Handle null or empty input array
            return;
        }
        //String[] forecastData = APIInterface.get5DayForecast(dataarr[0][0],dataarr[0][1]);
        String[] forecastData = DB_cache.GetForecast(dataarr[0][0],dataarr[0][1]);
        String[][] Weatherarr = new String[7][]; // Initialize the 2D array with 7 rows
        int x = 0;
        // Initialize the first row with 4 elements
        Weatherarr[0] = new String[4];
        for (x = 0; x < 4; x++) {
            Weatherarr[0][x] = forecastData[x];
        }
        // Initialize the rest of the rows with 5 elements each
        for (int i = 1; i < 7; i++) {
            Weatherarr[i] = new String[5];
            for (int j = 0; j < 5; j++) {
                Weatherarr[i][j] = forecastData[x++];
            }
        }
        int count = Weatherarr.length;
    this.display5DayForecastScreen(this,Weatherarr, count); 
    }
    @Override
    public void get5DayForecastDataProcess(String cityName) {
    
            //String[] forecastData = APIInterface.get5DayForecast(cityName);
            String[] forecastData = DB_cache.GetForecast(cityName);
            String[][] Weatherarr = new String[7][]; // Initialize the 2D array with 7 rows
            int x = 0;
            // Initialize the first row with 4 elements
            Weatherarr[0] = new String[4];
            for (x = 0; x < 4; x++) {
                Weatherarr[0][x] = forecastData[x];
            }
            // Initialize the rest of the rows with 5 elements each
            for (int i = 1; i < 7; i++) {
                Weatherarr[i] = new String[5];
                for (int j = 0; j < 5; j++) {
                    Weatherarr[i][j] = forecastData[x++];
                }
            }
            int count = Weatherarr.length;
            
           

        this.display5DayForecastScreen(this,Weatherarr, count);    
    }
    @Override
    public void display5DayForecastScreen(Session sessionInstance, String[][] arr, int count) {
        UIInterface.display5DayForecastScreen(sessionInstance, arr, count);
    }
} 
