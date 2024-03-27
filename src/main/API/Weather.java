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

public class Weather {

    @Deprecated
    public String[] getCurrentWeatherData(String queryParameter, String apiKey) {
        List<String> weatherInfoList = new ArrayList<>();

        try {
            // Construct the URL for API request
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?" + queryParameter + "&appid=" + apiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // Check the HTTP response code
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed : HTTP error code : " + responseCode);
                weatherInfoList.add("Not Found");
                for (int i = 0; i < 12; i++) {
                    weatherInfoList.add(null);
                }
                return weatherInfoList.toArray(new String[0]);
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

                if (jsonObject.containsKey("error")) {
                    System.out.println("Error: " + jsonObject.get("message"));
                    weatherInfoList.add("Not Found");
                    for (int i = 0; i < 12; i++) {
                        weatherInfoList.add(null);
                    }
                    return weatherInfoList.toArray(new String[0]);
                }

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
                Number latitudeN = (Number) coordObject.get("lat");
                Number longitudeN = (Number) coordObject.get("lon");
                JSONObject mainObject = (JSONObject) jsonObject.get("main");
                Double temp = (Double) mainObject.get("temp");
                Double feelsLike = (Double) mainObject.get("feels_like");
                Double minTemp = (Double) mainObject.get("temp_min");
                Double maxTemp = (Double) mainObject.get("temp_max");

                Double latitude = latitudeN.doubleValue();
                Double longitude = longitudeN.doubleValue();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherInfoList.toArray(new String[0]);
    }
}