package main.API;

import java.net.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Date;

public class API {

    private String API_Key = "bc146deacea7f9cc88cdb8b6bbb5fef9";

    public API(){

    }

    @SuppressWarnings("deprecation")
    public void testAPI(){
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Lahore&appid=" + returnKey());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // If connection is made;
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(con.getInputStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                // Close the scanner
                scanner.close();

                System.out.println(informationString);

                // JSON Parsing
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(informationString.toString());

				// Extracting data
				String cityName = (String) jsonObject.get("name");
				long timeZoneOffset = (long) jsonObject.get("timezone");
				JSONObject sysObject = (JSONObject) jsonObject.get("sys");
				String countryName = (String) sysObject.get("country");
				long sunriseTime = (long) sysObject.get("sunrise");
				long sunsetTime = (long) sysObject.get("sunset");
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


				System.out.println("City: " + cityName);
				System.out.println("Country: " + countryName);
				System.out.println("Sunrise Time: " + sunriseTime);
				System.out.println("Sunset Time: " + sunsetTime);
				System.out.println("Latitude: " + latitude);
				System.out.println("Longitude: " + longitude);
				System.out.println("Weather: " + weather);
				System.out.println("Weather Description: " + weatherDescription);
				System.out.println("Temperature: " + temp);
				System.out.println("Feels Like: " + feelsLike);
				System.out.println("Minimum Temperature: " + minTemp);
				System.out.println("Maximum Temperature: " + maxTemp);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String returnKey(){
        return (API_Key);
    }
}
