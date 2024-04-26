package main.API;



public class API { 

    // OpenWeatherMap API key
    private String API_Key = "a262e48a1166d96447ed16fe0708d092";
    //"bc146deacea7f9cc88cdb8b6bbb5fef9";

    
    Weather weather = new Weather();//WeatherMap API key
    Forecast forecast = new Forecast();//ForecastMap API key
    AirPollution airPoll = new AirPollution();//AirPollution API key

    // Default constructor
    public API() {}

    //Method to get weather by city name
    @SuppressWarnings("deprecation")
    public String[] getCurrentWeather(String cityName) {
        return weather.getCurrentWeatherData("q=" + cityName, API_Key);
    }

    // Method to get weather by coordinates
    @SuppressWarnings("deprecation")
    public String[] getCurrentWeather(double lat, double lon) {
        return weather.getCurrentWeatherData("lat=" + lat + "&lon=" + lon, API_Key);
    }

    

	// Method to get 5-day weather by city name
	@SuppressWarnings("deprecation")
    public String[] get5DayForecast(String cityName){
		return forecast.get5DayForecastData("q=" + cityName, API_Key);
	}

	// Method to get 5-day weather by coordinates
	@SuppressWarnings("deprecation")
    public String[] get5DayForecast(double lat, double lon){
		return forecast.get5DayForecastData("lat=" + lat + "&lon=" + lon, API_Key);
	}


	// Method to get Air Quality by coordinates
	@SuppressWarnings("deprecation")
    public String[] getAirQuality(double lat, double lon) {
		return airPoll.getAirQualityData("lat=" + lat + "&lon=" + lon, API_Key);
	}
    // Method to return the API key
    public String returnKey() {
        return (API_Key);
    }
}
