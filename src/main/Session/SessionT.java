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
	
    @Override
    public void getLoc_LatInpu(Session sessionInstance, int choice){
        
        UIInterface.lat_locInputScreen(sessionInstance, choice);
    }
    
	@Override
    public void getWeatherLocationInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherLocationInput'");
    }


	@Override
    public void getWeatherLoc_LatProcess(double [][] dataarr)
    {
        //display the data in the terminal
        for(int i = 0; i < 3; i++){
            System.out.println("Latitude of location " + (i+1) + " is " + dataarr[i][0]);
            System.out.println("Longitude of location " + (i+1) + " is " + dataarr[i][1]);
        }
    }

    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherLocationProcess'");
       // will process the data and then call the display screen
    }

	@Override
    public void displayWeatherscreen(Session sessionInstance, String[][] arr, int count) {
        throw new UnsupportedOperationException("Unimplemented method 'displayscreen'");
    }

    @Override
    public void getAirPollutionLoc_LatProcess(double[][] dataarr) {
        throw new UnsupportedOperationException("Unimplemented method 'getAirPollutionLoc_LatProcess'");
    }

    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {
        throw new UnsupportedOperationException("Unimplemented method 'displayAirPollutionScreen'");
    }

    
    @Override
    public void GetForecastLocationInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'GetForecastLocationInput'");
    }
    @Override
    public void GetForecastLocationbyNameInput(Session sessionInstance) {
        throw new UnsupportedOperationException("Unimplemented method 'GetForecastLocationbyNameInput'");
    }


    @Override
    public void get5DayForecastDataProcess(double[][] dataarr) {
        throw new UnsupportedOperationException("Unimplemented method 'get5DayForecastDataProcess'");
    } 
    @Override
    public void get5DayForecastDataProcess(String cityName) {
        throw new UnsupportedOperationException("Unimplemented method 'get5DayForecastDataProcess'");
    }
    @Override
    public void display5DayForecastScreen(Session sessionInstance, String[][] arr, int count) {
        throw new UnsupportedOperationException("Unimplemented method 'display5DayForecastScreen'");
    }

}