package main.API;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.*;
import java.text.SimpleDateFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.TimeZone;
import java.util.Date;
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import java.util.Scanner;

public class API {

    // OpenWeatherMap API key
    private String API_Key = "bc146deacea7f9cc88cdb8b6bbb5fef9";

    // Default constructor
    public API() {
    }

    // Method to get weather by city name
    @SuppressWarnings("deprecation")
    public String[] getCurrentWeather(String cityName) {
        return getCurrentWeatherData("q=" + cityName);
    }

    // Method to get weather by coordinates
    @SuppressWarnings("deprecation")
    public String[] getCurrentWeather(double lat, double lon) {
        return getCurrentWeatherData("lat=" + lat + "&lon=" + lon);
    }

    // Private method to fetch weather data from OpenWeatherMap API
    @SuppressWarnings("deprecation")
    private String[] getCurrentWeatherData(String queryParameter) {
        List<String> weatherInfoList = new ArrayList<>();
        try {
            // Construct the URL for API request
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?" + queryParameter + "&appid=" + returnKey());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
			
            // Check the HTTP response code
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            } else {
                // Read the API response
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(con.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                // Parse JSON response
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(informationString.toString());

                // Extract relevant data from JSON object
                String cityName = (String) jsonObject.get("name");
                long timeZoneOffset = (long) jsonObject.get("timezone");
				long timestamp = (long) jsonObject.get("dt");
                JSONObject sysObject = (JSONObject) jsonObject.get("sys");
                String countryName = (String) sysObject.get("country");
                long sunriseTimeUTC = (long) sysObject.get("sunrise");
                long sunsetTimeUTC = (long) sysObject.get("sunset");
                JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
                JSONObject weatherObject = (JSONObject) weatherArray.get(0);
                String weather = (String) weatherObject.get("main");
                String weatherDescription = (String) weatherObject.get("description");
                JSONObject coordObject = (JSONObject) jsonObject.get("coord");
                Double latitude = (Double) coordObject.get("lat");
                Double longitude = (Double) coordObject.get("lon");
                JSONObject mainObject = (JSONObject) jsonObject.get("main");
                Double temp = (Double) mainObject.get("temp");
                Double feelsLike = (Double) mainObject.get("feels_like");
                Double minTemp = (Double) mainObject.get("temp_min");
                Double maxTemp = (Double) mainObject.get("temp_max");

				// Convert timestamp to local time
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setTimeZone(TimeZone.getDefault()); // Set local timezone
				String localTime = sdf.format(new Date(timestamp * 1000L));

                // Convert time from UTC to local time
                long sunriseTimeLocal = sunriseTimeUTC + timeZoneOffset;
                long sunsetTimeLocal = sunsetTimeUTC + timeZoneOffset;

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date sunriseDateLocal = new Date(sunriseTimeLocal * 1000);
                Date sunsetDateLocal = new Date(sunsetTimeLocal * 1000);
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                String sunriseTime = dateFormat.format(sunriseDateLocal);
                String sunsetTime = dateFormat.format(sunsetDateLocal);



                // Convert temperature from Kelvin to Celsius
                temp = temp - 273.15;
                feelsLike = feelsLike - 273.15;
                minTemp = minTemp - 273.15;
                maxTemp = maxTemp - 273.15;

                String formattedTemp = String.format("%.2f", temp);
                String formattedFeelsLike = String.format("%.2f", feelsLike);
                String formattedMinTemp = String.format("%.2f", minTemp);
                String formattedMaxTemp = String.format("%.2f", maxTemp);

                // Populate the list with extracted data
                weatherInfoList.add(cityName);
                weatherInfoList.add(countryName);
				weatherInfoList.add(localTime);
                weatherInfoList.add(sunriseTime);
                weatherInfoList.add(sunsetTime);
                weatherInfoList.add(latitude.toString());
                weatherInfoList.add(longitude.toString());
                weatherInfoList.add(weather);
                weatherInfoList.add(weatherDescription);
                weatherInfoList.add(formattedTemp);
                weatherInfoList.add(formattedFeelsLike);
                weatherInfoList.add(formattedMinTemp);
                weatherInfoList.add(formattedMaxTemp);
            }
			// for (String info : weatherInfoList) {
            //     System.out.println(info);
            // }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherInfoList.toArray(new String[0]);
    }

	// Method to get 5-day weather by city name
	public String[] get5DayForecast(String cityName){
		return get5DayForecastData("q=" + cityName);
	}

	// Method to get 5-day weather by coordinates
	public String[] get5DayForecast(double lat, double lon){
		return get5DayForecastData("lat=" + lat + "&lon=" + lon);
	}

	// Method to get 5 day forcast data
	@SuppressWarnings("deprecation")
    private String[] get5DayForecastData(String queryParameter) {
        List<String> forecastInfoList = new ArrayList<>();
        try {
            // Construct the URL for API request
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?" + queryParameter + "&appid=" + returnKey());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // Check the HTTP response code
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            } else {
                // Read the API response
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(con.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                // Parse JSON response
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(informationString.toString());

				
				String countryName = (String) jsonObject.get("country");
				JSONObject cityObject = (JSONObject) jsonObject.get("city");
				String cityName = (String) cityObject.get("name");
				JSONObject coordObject = (JSONObject) cityObject.get("coord");
				Double latitude = (Double) coordObject.get("lat");
				Double longitude = (Double) coordObject.get("lon");
				forecastInfoList.add(cityName);
				forecastInfoList.add(countryName);
				forecastInfoList.add(latitude.toString());
				forecastInfoList.add(longitude.toString());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();  
				forecastInfoList.add(formatter.format(date).toString());

                // Extract relevant data from JSON object
                JSONArray forecastList = (JSONArray) jsonObject.get("list");
                
                for (int i = 0; i < forecastList.size(); i++) {
					
					JSONObject forecastObject = (JSONObject) forecastList.get(i);
                    String dateTime = (String) forecastObject.get("dt_txt");
                    JSONObject mainObject = (JSONObject) forecastObject.get("main");
                    Number temp = (Number) mainObject.get("temp");
                    Number feelsLike = (Number) mainObject.get("feels_like");
                    Number minTemp = (Number) mainObject.get("temp_min");
                    Number maxTemp = (Number) mainObject.get("temp_max");
                    JSONArray weatherArray = (JSONArray) forecastObject.get("weather");
                    JSONObject weatherObject = (JSONObject) weatherArray.get(0);
                    String weather = (String) weatherObject.get("main");
                    String weatherDescription = (String) weatherObject.get("description");

					
                    // Convert temperature from Kelvin to Celsius
                    Double tempCelsius = temp.doubleValue() - 273.15;
                	Double feelsLikeCelsius = feelsLike.doubleValue() - 273.15;
                	Double minTempCelsius = minTemp.doubleValue() - 273.15;
                	Double maxTempCelsius = maxTemp.doubleValue() - 273.15;

                    String formattedTemp = String.format("%.2f", tempCelsius);
                    String formattedFeelsLike = String.format("%.2f", feelsLikeCelsius);
                    String formattedMinTemp = String.format("%.2f", minTempCelsius);
                    String formattedMaxTemp = String.format("%.2f", maxTempCelsius);

                    // Populate the list with extracted data
                    forecastInfoList.add(dateTime);
                    forecastInfoList.add(formattedTemp);
                    forecastInfoList.add(formattedFeelsLike);
                    forecastInfoList.add(formattedMinTemp);
                    forecastInfoList.add(formattedMaxTemp);
                    forecastInfoList.add(weather);
                    forecastInfoList.add(weatherDescription);
                }

            }

            for (String info : forecastInfoList) {
                System.out.println(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecastInfoList.toArray(new String[0]);
    }

	// Method to get Air Quality by city name
	@SuppressWarnings("deprecation")
	public String[] getAirQuality(String cityName) {
		return getAirQualityData("q=" + cityName);
	}

	// Method to get Air Quality by coordinates
	@SuppressWarnings("deprecation")
	public String[] getAirQuality(double lat, double lon) {
		return getAirQualityData("lat=" + lat + "&lon=" + lon);
	}

	// Private method to fetch Air Quality data from OpenWeatherMap API
	@SuppressWarnings("deprecation")
	private String[] getAirQualityData(String queryParameter) {
		List<String> airQualityInfoList = new ArrayList<>();
		try {
			// Construct the URL for API request
			URL url = new URL("https://api.openweathermap.org/data/2.5/air_pollution?" + queryParameter + "&appid=" + returnKey());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();

			// Check the HTTP response code
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + responseCode);
			} else {
				// Read the API response
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(con.getInputStream());

				while (scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				scanner.close();

				// Parse JSON response
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(informationString.toString());

				// Extract relevant data from JSON object
				JSONObject coordObject = (JSONObject) jsonObject.get("coord");
				Double latitude = (Double) coordObject.get("lat");
				Double longitude = (Double) coordObject.get("lon");
				JSONArray listArray = (JSONArray) jsonObject.get("list");
				JSONObject mainObject = (JSONObject) listArray.get(0);
				long timestamp = (long) mainObject.get("dt");
				JSONObject aqiObject = (JSONObject) mainObject.get("main");
				Number aqi = (Number) aqiObject.get("aqi");
				JSONObject componentsObject = (JSONObject) mainObject.get("components");

				// Convert timestamp to local time
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sdf.setTimeZone(TimeZone.getDefault()); // Set local timezone
				String localTime = sdf.format(new Date(timestamp * 1000L));

				// Extract air quality data
				Double co = (Double) componentsObject.get("co");
				Double no = (Double) componentsObject.get("no");
				Double no2 = (Double) componentsObject.get("no2");
				Double o3 = (Double) componentsObject.get("o3");
				Double so2 = (Double) componentsObject.get("so2");
				Double pm2_5 = (Double) componentsObject.get("pm2_5");
				Double pm10 = (Double) componentsObject.get("pm10");
				Double nh3 = (Double) componentsObject.get("nh3");


				// Populate the list with extracted data
				airQualityInfoList.add(latitude.toString());
				airQualityInfoList.add(longitude.toString());
				
				airQualityInfoList.add(localTime);
				Double aqiDouble = aqi.doubleValue();
				airQualityInfoList.add(aqiDouble.toString());
				airQualityInfoList.add(co + " µg/m³");
				airQualityInfoList.add(no + " µg/m³");
				airQualityInfoList.add(no2 + " µg/m³");
				airQualityInfoList.add(o3 + " µg/m³");
				airQualityInfoList.add(so2 + " µg/m³");
				airQualityInfoList.add(pm2_5 + " µg/m³");
				airQualityInfoList.add(pm10 + " µg/m³");
				airQualityInfoList.add(nh3 + " µg/m³");
			}

			for (String info : airQualityInfoList) {
				System.out.println(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return airQualityInfoList.toArray(new String[0]);
	}

	

    // Method to return the API key
    public String returnKey() {
        return (API_Key);
    }
}
