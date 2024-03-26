package main.Database;

//import main.API.API;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Connection; 
public class SQL implements DB
{
	public Connection connect()
	{
		Connection conn = null;
		try
		{
			String connectionString = "jdbc:sqlserver://DESKTOP-2DS0DMR;databaseName=testing2;integratedSecurity=true;TrustServerCertificate=True";
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
	public void Insert(String query,Connection conn,String[] data)
	{
		try
		{
			//changing expeccted here	
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
		String cityName = data[0];
        String countryName = data[1];
        String localTime = data[2];
        String sunriseTime = data[3];
        String sunsetTime = data[4];
        String latitude = data[5];
        String longitude = data[6];
        String weather = data[7];
        String weatherDescription = data[8];
        String temp = data[9];
        String feelsLike = data[10];
        String minTemp = data[11];
        String maxTemp = data[12];

        try (Connection connection = connect()) 
		{
            if (connection != null) {
                // Insert into Locations table
                String locationInsertQuery = "INSERT INTO Locations (name, latitude, longitude, country) VALUES (?, ?, ?, ?)";
                try (PreparedStatement locationStatement = connection.prepareStatement(locationInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    locationStatement.setString(1, cityName);
                    locationStatement.setString(2, latitude);
                    locationStatement.setString(3, longitude);
                    locationStatement.setString(4, countryName);
                    locationStatement.executeUpdate();

                    // Get the generated location ID
                    int locationId;
                    try (var generatedKeys = locationStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            locationId = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Creating location failed, no ID obtained.");
                        }
                    }

                    // Insert into weather table
                    String weatherInsertQuery = "INSERT INTO weather (localTime, sunriseTime, sunsetTime, weather, weatherDescription, Temp, FeelsLike, MinTemp, MaxTemp, location_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement weatherStatement = connection.prepareStatement(weatherInsertQuery)) {
                        weatherStatement.setString(1, localTime);
                        weatherStatement.setString(2, sunriseTime);
                        weatherStatement.setString(3, sunsetTime);
                        weatherStatement.setString(4, weather);
                        weatherStatement.setString(5, weatherDescription);
                        weatherStatement.setString(6, temp);
                        weatherStatement.setString(7, feelsLike);
                        weatherStatement.setString(8, minTemp);
                        weatherStatement.setString(9, maxTemp);
                        weatherStatement.setInt(10, locationId);
                        weatherStatement.executeUpdate();
                    }
                }
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
		
	
    
    public void insertForecastInfo(String[] data)
	{
		Connection conn = connect();
		String query = "INSERT INTO forecast (city, country, sunrise, sunset, latitude, longitude, weather, weather_description, formatted_temp, feel_like, min_temp, max_temp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		Insert(query,conn,data);

	}
	public void insertAirInfo(String[] data)
	{
		String latitude = data[0];
        String longitude = data[1];
        String localTime = data[2];
        String aqi = data[3];
        String dco = data[4];
        String dno = data[5];
        String dno2 = data[6];
        String do3 = data[7];
        String dso2 = data[8];
        String dpm2_5 = data[9];
        String dpm10 = data[10];
        String dnh3 = data[11];

        try (Connection connection = connect()) {
            if (connection != null) {
                // Insert into Locations table
                String locationInsertQuery = "INSERT INTO Locations (latitude, longitude) VALUES (?, ?)";
                try (PreparedStatement locationStatement = connection.prepareStatement(locationInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    locationStatement.setString(1, latitude);
                    locationStatement.setString(2, longitude);
                    locationStatement.executeUpdate();

                    // Get the generated location ID
                    int locationId;
                    try (var generatedKeys = locationStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            locationId = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Creating location failed, no ID obtained.");
                        }
                    }

                    // Insert into AirQuality table
                    String airQualityInsertQuery = "INSERT INTO AirQuality (localTime, aqi, Dco, Dno, Dno2, Do3, Dso2, Dpm2_5, Dpm10, Dnh3, location_id) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement airQualityStatement = connection.prepareStatement(airQualityInsertQuery)) {
                        airQualityStatement.setString(1, localTime);
                        airQualityStatement.setString(2, aqi);
                        airQualityStatement.setString(3, dco);
                        airQualityStatement.setString(4, dno);
                        airQualityStatement.setString(5, dno2);
                        airQualityStatement.setString(6, do3);
                        airQualityStatement.setString(7, dso2);
                        airQualityStatement.setString(8, dpm2_5);
                        airQualityStatement.setString(9, dpm10);
                        airQualityStatement.setString(10, dnh3);
                        airQualityStatement.setInt(11, locationId);
                        airQualityStatement.executeUpdate();
                    }
                }
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

    //
    public String[] retrieveWeatherInfo(double lat,double lon)
	{
		String[] weatherInfo = new String[13]; // Array to store weather information

        try (Connection connection = connect()) {
            if (connection != null) {
                // Query to retrieve weather data based on latitude and longitude
                String query = "SELECT Locations.name, Locations.country, weather.localTime, weather.sunriseTime, " +
                        "weather.sunsetTime, Locations.latitude, Locations.longitude, weather.weather, " +
                        "weather.weatherDescription, weather.Temp, weather.FeelsLike, weather.MinTemp, weather.MaxTemp " +
                        "FROM Locations INNER JOIN weather ON Locations.id = weather.location_id " +
                        "WHERE Locations.latitude = ? AND Locations.longitude = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, String.valueOf(lat));
                    statement.setString(2, String.valueOf(lon));

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Extract data from the result set and store in the array
                            weatherInfo[0] = resultSet.getString("name");
                            weatherInfo[1] = resultSet.getString("country");
                            weatherInfo[2] = resultSet.getString("localTime");
                            weatherInfo[3] = resultSet.getString("sunriseTime");
                            weatherInfo[4] = resultSet.getString("sunsetTime");
                            weatherInfo[5] = resultSet.getString("latitude");
                            weatherInfo[6] = resultSet.getString("longitude");
                            weatherInfo[7] = resultSet.getString("weather");
                            weatherInfo[8] = resultSet.getString("weatherDescription");
                            weatherInfo[9] = resultSet.getString("Temp");
                            weatherInfo[10] = resultSet.getString("FeelsLike");
                            weatherInfo[11] = resultSet.getString("MinTemp");
                            weatherInfo[12] = resultSet.getString("MaxTemp");
                        }
                    }
                }
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weatherInfo;
	}
    public String[] retrieveWeatherInfo(String city)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}

    public String[] retrieveForecastInfo(double lat,double lon)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}
    public String[] retrieveForecastInfo(String city)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}

    public String[] retrieveAirInfo(double lat,double lon)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}

	//boolean functions to check data existance 

	public boolean checkIfWeatherExists(double lat, double lon) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM Locations WHERE latitude = ? AND longitude = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, lat);
            statement.setDouble(2, lon);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    exists = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    //Session methods
    public String[] GetWeather(double lat,double lon)
	{
		if (checkIfWeatherExists(lat, lon)) {
            return retrieveWeatherInfo(lat, lon);
        } else {
			String[] data = APIInterface.getCurrentWeather(lat,lon);
            //api call and insert method will be called here
			insertWeatherInfo(data);
			return data;
        }
    }

		

	
    public String[] GetWeather(String city)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}
    public String[] GetForecast(double lat,double  lon)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}
    public String[] GetForecast(String city)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
	}
    public String[] GetAirPoll(double lat,double lon)
	{
		String[] data = {"karachi","pakistan","6:00","6:00","24.8607","67.0011","sunny","clear sky","30","30","30","30"};
		return data;
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
