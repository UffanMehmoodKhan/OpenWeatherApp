package main.Database;

public interface DB
{
    public void insertWeatherInfo(String[] data);
    public void insertForecastInfo(String[] data);
    public void insertAirInfo(String[] data);
    public void retrieveWeatherInfo(String[] data);
    public void retrieveForecastInfo(String[] data);
    public void retrieveAirInfo(String[] data);
}

