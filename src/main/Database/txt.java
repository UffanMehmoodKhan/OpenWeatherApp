package main.Database;

public class txt implements DB
{
	public void insertWeatherInfo(String[] data)
	{

	}
	public void insertForecastInfo(String[] data)
	{

	}
	public void insertAirInfo(String[] data)
	{

	}

	public String[] retrieveWeatherInfo(double lat, double lon)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}
	public String[] retrieveWeatherInfo(String city)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}

	public String[] retrieveForecastInfo(double lat, double lon)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}
	public String[] retrieveForecastInfo(String city)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}

	public String[] retrieveAirInfo(double lat, double lon)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}


	//Session methods
	public String[] GetWeather(double lat, double lon)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}
	public String[] GetWeather(String city)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}
	public String[] GetForecast(double lat, double lon)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}
	public String[] GetForecast(String city)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}
	public String[] GetAirPoll(double lat, double lon)
	{
		String[] data = {"karachi", "pakistan", "6:00", "6:00", "24.8607", "67.0011", "sunny", "clear sky", "30", "30", "30", "30"};
		return data;
	}

}
