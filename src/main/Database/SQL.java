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
    public SQL(){

    }
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
		
	
    //implemented
    public void insertForecastInfo(String[] data)
	{
        Connection connection = null;
        PreparedStatement forecastStatement = null;

        try {
            // Establish connection
            connection = connect();

            if (connection != null) {
                // Insert data into Locations table
                String insertLocationQuery = "INSERT INTO Locations (name, latitude, longitude, country) VALUES (?, ?, ?, ?)";
                PreparedStatement locationStatement = connection.prepareStatement(insertLocationQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                locationStatement.setString(1, data[5]); // City name
                locationStatement.setString(2, data[7]); // Latitude
                locationStatement.setString(3, data[8]); // Longitude
                locationStatement.setString(4, data[6]); // Country name
                locationStatement.executeUpdate();

                // Retrieve the generated location ID
                int locationId;
                try (var generatedKeys = locationStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        locationId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve generated keys.");
                    }
                }

                // Insert data into Forecast table
                String insertForecastQuery = "INSERT INTO Forecast (datee, formattedMinTemp, formattedMaxTemp, weather, weatherDescription, cityName, countryName, latitude, longitude, location_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                forecastStatement = connection.prepareStatement(insertForecastQuery);
                forecastStatement.setString(1, data[0]); // Date
                forecastStatement.setString(2, data[1]); // Min temp
                forecastStatement.setString(3, data[2]); // Max temp
                forecastStatement.setString(4, data[3]); // Weather
                forecastStatement.setString(5, data[4]); // Weather description
                forecastStatement.setString(6, data[5]); // City name
                forecastStatement.setString(7, data[6]); // Country name
                forecastStatement.setString(8, data[7]); // Latitude
                forecastStatement.setString(9, data[8]); // Longitude
                forecastStatement.setInt(10, locationId); // Location ID
                forecastStatement.executeUpdate();
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (forecastStatement != null) forecastStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
        String[] weatherInfo = new String[13]; // Array to store weather information

        try (Connection connection = connect()) {
            if (connection != null) {
                // Query to retrieve weather data based on city name
                String query = "SELECT Locations.name, Locations.country, weather.localTime, weather.sunriseTime, " +
                        "weather.sunsetTime, Locations.latitude, Locations.longitude, weather.weather, " +
                        "weather.weatherDescription, weather.Temp, weather.FeelsLike, weather.MinTemp, weather.MaxTemp " +
                        "FROM Locations INNER JOIN weather ON Locations.id = weather.location_id " +
                        "WHERE Locations.name = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, city);

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

    //    //timplemented
    public String[] retrieveForecastInfo(double lat,double lon)
	{
		String[] forecastInfo = new String[9]; // Array to store forecast information

        try (Connection connection = connect()) 
        {
            if (connection != null) {
                // Query to retrieve forecast data based on latitude and longitude
                String query = "SELECT Locations.name, Locations.country, Forecast.datee, Forecast.formattedMinTemp, " +
                        "Forecast.formattedMaxTemp, Forecast.weather, Forecast.weatherDescription, Locations.latitude, " +
                        "Locations.longitude " +
                        "FROM Locations INNER JOIN Forecast ON Locations.id = Forecast.location_id " +
                        "WHERE Locations.latitude = ? AND Locations.longitude = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, String.valueOf(lat));
                    statement.setString(2, String.valueOf(lon));

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Extract data from the result set and store in the array
                            forecastInfo[0] = resultSet.getString("name");
                            forecastInfo[1] = resultSet.getString("country");
                            forecastInfo[2] = resultSet.getString("datee");
                            forecastInfo[3] = resultSet.getString("formattedMinTemp");
                            forecastInfo[4] = resultSet.getString("formattedMaxTemp");
                            forecastInfo[5] = resultSet.getString("weather");
                            forecastInfo[6] = resultSet.getString("weatherDescription");
                            forecastInfo[7] = resultSet.getString("latitude");
                            forecastInfo[8] = resultSet.getString("longitude");
                        }
                    }
                }
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forecastInfo;
	}
    public String[] retrieveForecastInfo(String city)
	{
        String[] forecastInfo = new String[9]; // Array to store forecast information

        try (Connection connection = connect()) {
            if (connection != null) {
                // Query to retrieve forecast data based on city name
                String query = "SELECT Locations.name, Locations.country, Forecast.datee, Forecast.formattedMinTemp, " +
                        "Forecast.formattedMaxTemp, Forecast.weather, Forecast.weatherDescription, Locations.latitude, " +
                        "Locations.longitude " +
                        "FROM Locations INNER JOIN Forecast ON Locations.id = Forecast.location_id " +
                        "WHERE Locations.name = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, city);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Extract data from the result set and store in the array
                            forecastInfo[0] = resultSet.getString("name");
                            forecastInfo[1] = resultSet.getString("country");
                            forecastInfo[2] = resultSet.getString("datee");
                            forecastInfo[3] = resultSet.getString("formattedMinTemp");
                            forecastInfo[4] = resultSet.getString("formattedMaxTemp");
                            forecastInfo[5] = resultSet.getString("weather");
                            forecastInfo[6] = resultSet.getString("weatherDescription");
                            forecastInfo[7] = resultSet.getString("latitude");
                            forecastInfo[8] = resultSet.getString("longitude");
                        }
                    }
                }
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forecastInfo;
	}


    public String[] retrieveAirInfo(double lat,double lon)
	{
        String[] airInfo = new String[10]; // Array to store air quality information
        try (Connection connection = connect()) {
            if (connection != null) {
                // Query to retrieve air quality data based on latitude and longitude
                String query = "SELECT localTime, aqi, Dco, Dno, Dno2, Do3, Dso2, Dpm2_5, Dpm10, Dnh3 " +
                               "FROM AirQuality INNER JOIN Locations ON AirQuality.location_id = Locations.id " +
                               "WHERE Locations.latitude = ? AND Locations.longitude = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setDouble(1, lat);
                    statement.setDouble(2, lon);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Extract data from the result set and store in the array
                            airInfo[0] = resultSet.getString("localTime");
                            airInfo[1] = resultSet.getString("aqi");
                            airInfo[2] = resultSet.getString("Dco");
                            airInfo[3] = resultSet.getString("Dno");
                            airInfo[4] = resultSet.getString("Dno2");
                            airInfo[5] = resultSet.getString("Do3");
                            airInfo[6] = resultSet.getString("Dso2");
                            airInfo[7] = resultSet.getString("Dpm2_5");
                            airInfo[8] = resultSet.getString("Dpm10");
                            airInfo[9] = resultSet.getString("Dnh3");
                        }
                    }
                }
            } else {
                System.out.println("Failed to make connection to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airInfo;
	}

	//boolean functions to check data existance 

	public boolean checkIfDataExists(double lat, double lon) {
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
    public boolean checkIfDataExists(String city) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM Locations WHERE name = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, city);
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
    public boolean checkIfAirpollExists(double lat, double lon) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM AirQuality INNER JOIN Locations ON AirQuality.location_id = Locations.id " +
                       "WHERE Locations.latitude = ? AND Locations.longitude = ?";
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
    
    
    //Session methods for weather
    public String[] GetWeather(double lat,double lon)
	{
		if (checkIfDataExists(lat, lon)) {
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
		if (checkIfDataExists(city)) {
            return retrieveWeatherInfo(city);
        } else {
            String[] data = APIInterface.getCurrentWeather(city);
            //api call and insert method will be called here
            insertWeatherInfo(data);
            return data;
        }
	}
   
    //session methods for forecast,     // implemented
    public String[] GetForecast(double lat,double  lon)
	{
		if (checkIfDataExists(lat, lon)) {
            return retrieveForecastInfo(lat, lon);
        } else
        {
			String[] data = APIInterface.get5DayForecast(lat,lon);
            //api call and insert method will be called here
			insertForecastInfo(data);
			return data;
        }
	}
    public String[] GetForecast(String city)
	{
		if (checkIfDataExists(city)) {
            return retrieveForecastInfo(city);
        } else
        {
			String[] data = APIInterface.get5DayForecast(city);
            //api call and insert method will be called here
			insertForecastInfo(data);
			return data;
        }
	}
    
    //session methods for air quality
    public String[] GetAirPoll(double lat,double lon)
	{
		if (checkIfAirpollExists(lat, lon)) {
            return retrieveAirInfo(lat, lon);
        } else {
			String[] data = APIInterface.getAirQuality(lat,lon);
            //api call and insert method will be called here
			insertAirInfo(data);
			return data;
        }
	}

}