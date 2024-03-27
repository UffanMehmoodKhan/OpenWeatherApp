package main.API;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.HashSet;

public class Forecast {

    @Deprecated
    // Method to get 5 day forecast data
    public String[] get5DayForecastData(String queryParameter, String apiKey) {
        List<String> forecastInfoList = new ArrayList<>();
        HashSet<String> uniqueDates = new HashSet<>();

        try {
            // Construct the URL for API request
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?" + queryParameter + "&appid=" + apiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // Check the HTTP response code
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed : HTTP error code : " + responseCode);
                forecastInfoList.add("Not Found");
                for (int i = 0; i < 40; i++) {
                    forecastInfoList.add(null);
                }
                return forecastInfoList.toArray(new String[0]);
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

                if (jsonObject.containsKey("message") || jsonObject.containsKey("error")) {
                    System.out.println("Error: " + jsonObject.get("message"));
                    forecastInfoList.add("Not Found");
                    for (int i = 0; i < 40; i++) {
                        forecastInfoList.add(null);
                    }
                    return forecastInfoList.toArray(new String[0]);
                }

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

                // Extract relevant data from JSON object
                JSONArray forecastList = (JSONArray) jsonObject.get("list");

                for (int i = 0; i < forecastList.size(); i++) {
                    JSONObject forecastObject = (JSONObject) forecastList.get(i);
                    String dateTime = (String) forecastObject.get("dt_txt");
                    String datee = dateTime.split(" ")[0];

                    // Skip if date is already added
                    if (uniqueDates.contains(datee)) {
                        continue;
                    }

                    uniqueDates.add(datee);

                    JSONObject mainObject = (JSONObject) forecastObject.get("main");
                    Number minTemp = (Number) mainObject.get("temp_min");
                    Number maxTemp = (Number) mainObject.get("temp_max");
                    JSONArray weatherArray = (JSONArray) forecastObject.get("weather");
                    JSONObject weatherObject = (JSONObject) weatherArray.get(0);
                    String weather = (String) weatherObject.get("main");
                    String weatherDescription = (String) weatherObject.get("description");

                    // Convert temperature from Kelvin to Celsius
                    Double minTempCelsius = minTemp.doubleValue() - 273.15;
                    Double maxTempCelsius = maxTemp.doubleValue() - 273.15;

                    String formattedMinTemp = String.format("%.2f", minTempCelsius);
                    String formattedMaxTemp = String.format("%.2f", maxTempCelsius);

                    // Populate the list with extracted data
                    forecastInfoList.add(datee);
                    forecastInfoList.add(formattedMinTemp);
                    forecastInfoList.add(formattedMaxTemp);
                    forecastInfoList.add(weather);
                    forecastInfoList.add(weatherDescription);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forecastInfoList.toArray(new String[0]);
    }
}
