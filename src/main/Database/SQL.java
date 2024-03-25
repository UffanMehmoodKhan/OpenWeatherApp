package main.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection; 

public class SQL implements DB
{
	public Connection connect()
	{
		Connection conn = null;
		try
		{
			String connectionString = "jdbc:sqlserver://DESKTOP-2DS0DMR;databaseName=testing;integratedSecurity=true;TrustServerCertificate=True";
			conn = DriverManager.getConnection(connectionString);
			System.out.println("Connection has been established.");
		}
		catch (SQLException e) 
		{
			System.out.println("connection failed");
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public void insertWeather(String[] data) 
	{
		Connection conn = connect();
		try
		{
			//changing expeccted here	
			String query = "INSERT INTO weather (city, country, sunrise, sunset, latitude, longitude, weather, weather_description, formatted_temp, feel_like, min_temp, max_temp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			for(int i=0; i<data.length; i++)
			{
				statement.setString(i+1, data[i]);
			}
			statement.executeUpdate();
			System.err.println("Data has been inserted successfully");
		}
		catch (SQLException e) 
		{
			System.out.println("connection failed");
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch (SQLException e) 
			{
				System.out.println("connection failed");
				System.out.println(e.getMessage());
			}
		}
		
	}	
	
	public void insertWeatherInfo(String[] data)
	{

	}
    public void insertForecastInfo(String[] data)
	{

	}
    public void insertAirInfo(String[] data)
	{

	}

    //
    public String retrieveWeatherInfo(double lat,double lon)
	{

	}
    public String retrieveWeatherInfo(String city,String country)
	{

	}

    public String retrieveForecastInfo(double lat,double lon)
	{

	}
    public String retrieveForecastInfo(String city,String country)
	{

	}

    public String retrieveAirInfo(double lat,double lon)
	{

	}


    //Session methods
    public String GetWeather(double lat,double lon)
	{

	}
    public String GetWeather(String city,String country)
	{

	}
    public String GetForecast(double lat,double  lon)
	{

	}
    public String GetForecast(String city,String country)
	{

	}
    public String GetAirPoll(double lat,double lon)
	{
		
	}
	public static void main(String[] args)
	{
		SQL sql = new SQL();
		//String[] data = {"Karachi", "Pakistan", "6:00 AM", "6:00 PM", "31.5497", "74.3436", "Clear", "Clear Sky", "30", "30", "30", "30"};
		//give another city data like toronto
		String[] data = {"Toronto", "Canada", "6:00 AM", "6:00 PM", "43.65107", "-79.347015", "Clear", "Clear Sky", "30", "30", "30", "30"};
		sql.insertWeatherInfo(data);
	}

}
