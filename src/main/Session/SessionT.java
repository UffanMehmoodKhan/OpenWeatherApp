package main.Session;


import main.Database.DB;
import main.UserInterface.UserInterface;
import main.UserInterface.terminal;

public class SessionT implements Session{
	UserInterface UIInterface;
    //DB SQL_cache; DB txt_cache;
    DB DB_cache;
    public SessionT(DB db){

        DB_cache = db; UIInterface = new terminal(DB_cache);
        System.out.println("Terminal is Operational");
        this.getWelcomeScreen();
    }

    @Override
    public void getWelcomeScreen(){
		UIInterface.welcomeScreen(this);
    }
    // terminal input is due to this method 
    @Override
    public void getLoc_LatInpu(Session sessionInstance, int choice){
        UIInterface.lat_locInputScreen(sessionInstance, choice);
    }
	@Override
    public void getWeatherLoc_LatProcess(double [][] dataarr)
    {
        String data[][] = new String[1][];
         data [0]= DB_cache.GetWeather(dataarr[0][0],dataarr[0][1] );
        //display the data in the terminal
        this.displayWeatherscreen(this, data, 1);
        // for(int i = 0; i < 3; i++){
        //     System.out.println("Latitude of location " + (i+1) + " is " + dataarr[i][0]);
        //     System.out.println("Longitude of location " + (i+1) + " is " + dataarr[i][1]);
        // }
    }
    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        // will process the data and then call the display screen
       String data[][] = new String[1][];
       String Tdata[] = DB_cache.GetWeather(dataarra[0]);
       data[0]=Tdata;
       this.displayWeatherscreen(this,data , 1);

    }
	@Override
    public void displayWeatherscreen(Session sessionInstance, String[][] arr, int count) {

        UIInterface.displayscreen(this, arr, 1);
    }
    @Override
    public void getAirPollutionLoc_LatProcess(double[][] dataarr) {
        String data[][] = new String[1][];
        data [0]= DB_cache.GetAirPoll(dataarr[0][0],dataarr[0][1] );
        
        this.displayAirPollutionScreen(this, data, 1);
    }
    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {
        UIInterface.displayAirPollutionScreen(this, arr, 1);
    }
    @Override
    public void get5DayForecastDataProcess(double[][] dataarr) {
        String data[][] = new String[1][];
        data [0]= DB_cache.GetForecast(dataarr[0][0],dataarr[0][1] );
        this.display5DayForecastScreen(this,data,1);
    } 
    @Override
    public void get5DayForecastDataProcess(String cityName) {
        String data[][] = new String[1][];
        data[0]= DB_cache.GetForecast(cityName);
        this.display5DayForecastScreen(this,data,1);
    }
    @Override
    public void display5DayForecastScreen(Session sessionInstance, String[][] arr, int count) {
        UIInterface.display5DayForecastScreen(sessionInstance, arr, count);
    }
    // not used because on terminal we have asked the user simultaneloysly what type of data he wants t input
    @Override
    public void GetForecastLocationInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'GetForecastLocationInput'");
    }
    @Override
    public void GetForecastLocationbyNameInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'GetForecastLocationbyNameInput'");
    }
    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherLocationInput'");
    }

}