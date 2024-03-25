package main.Database;

public interface DB
{
    public void insertWeatherInfo(String[] data);
    public void insertForecastInfo(String[] data);
    public void insertAirInfo(String[] data);

    //
    public String[] retrieveWeatherInfo(double lat,double lon);
    public String[] retrieveWeatherInfo(String city);

    public String[] retrieveForecastInfo(double lat,double lon);
    public String[] retrieveForecastInfo(String city);

    public String[] retrieveAirInfo(double lat,double lon);


    //Session methods
    public String[] GetWeather(double lat,double lon);
    public String[] GetWeather(String city);
    public String[] GetForecast(double lat,double  lon);
    public String[] GetForecast(String city);
    public String[] GetAirPoll(double lat,double lon);
}

