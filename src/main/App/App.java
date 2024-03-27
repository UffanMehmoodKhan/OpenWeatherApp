package main.App;

import main.Session.Session;
import main.Session.SessionT;
import main.Session.SessionDT;

public class App{

	Session session;
	
	//App constructor take console argument
    App(String argvString) {
		if(argvString.equals("terminal")){ 			//creates a terminal Session
			System.out.println("is this terminal even working?");
			session = new SessionT();
		}
		else if(argvString.equals("desktop")){		//creates a desktop Session
			System.out.println("is this desktop even working?");
			session = new SessionDT(); 
		}
    }
	Session getSession()
	{
        return session;
    }

    public static void main(String[] args)
	{
		  // Instantiate the txt class
        DB database = new txt();

        // Test inserting weather information
        String[] weatherData = {"New York", "20", "30"};
        database.insertWeatherInfo(weatherData);

        // Test retrieving weather information by city
        String[] retrievedWeatherByCity = database.GetWeather("New York");
        if (retrievedWeatherByCity != null) {
            System.out.println("Weather information for New York:");
            for (String data : retrievedWeatherByCity) {
                System.out.print(data + " ");
            }
            System.out.println();
        }

        // Test retrieving weather information by latitude and longitude
        double lat = 40.7128;
        double lon = -74.0060;
        String[] retrievedWeatherByLatLon = database.GetWeather(lat, lon);
        if (retrievedWeatherByLatLon != null) {
            System.out.println("Weather information for latitude " + lat + " and longitude " + lon + ":");
            for (String data : retrievedWeatherByLatLon) {
                System.out.print(data + " ");
            }
            System.out.println();
        }

        // Similarly, you can test other methods like GetForecast and GetAirPoll
		
        // System.out.println("\n" + args[0] + " application config"); 
		// App OpenWeatherMap = new App(args[0]); //Initialize OpenWeatherMap App object
		// OpenWeatherMap.getSession();
    }
	
}