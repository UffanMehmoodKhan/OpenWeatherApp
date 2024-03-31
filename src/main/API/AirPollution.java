package main.API;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AirPollution API{

	@Deprecated
    public String[] getAirQualityData(String queryParameter, String apiKey) {
        List<String> airQualityInfoList = new ArrayList<>();

        try {
            // Construct the URL for API request
            URL url = new URL("https://api.openweathermap.org/data/2.5/air_pollution?" + queryParameter + "&appid=" + apiKey);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            // Check the HTTP response code
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Failed : HTTP error code : " + responseCode);
                airQualityInfoList.add("Not Found");
                for (int i = 0; i < 10; i++) {
                    airQualityInfoList.add(null);
                }
                return airQualityInfoList.toArray(new String[0]);
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
                    airQualityInfoList.add("Not Found");
                    for (int i = 0; i < 10; i++) {
                        airQualityInfoList.add(null);
                    }
                    return airQualityInfoList.toArray(new String[0]);
                }

                // Extract relevant data from JSON object
                JSONObject coordObject = (JSONObject) jsonObject.get("coord");
                Number latitude = (Number) coordObject.get("lat");
                Number longitude = (Number) coordObject.get("lon");
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
                localTime = localTime.replace(" ", "_");

                // Extract air quality data
                Number co = (Number) componentsObject.get("co");
                Number no = (Number) componentsObject.get("no");
                Number no2 = (Number) componentsObject.get("no2");
                Number o3 = (Number) componentsObject.get("o3");
                Number so2 = (Number) componentsObject.get("so2");
                Number pm2_5 = (Number) componentsObject.get("pm2_5");
                Number pm10 = (Number) componentsObject.get("pm10");
                Number nh3 = (Number) componentsObject.get("nh3");

                // Convert values to Double
                Double latitudeD = latitude.doubleValue();
                Double longitudeD = longitude.doubleValue();
                Double Dco = co.doubleValue();
                Double Dno = no.doubleValue();
                Double Dno2 = no2.doubleValue();
                Double Do3 = o3.doubleValue();
                Double Dso2 = so2.doubleValue();
                Double Dpm2_5 = pm2_5.doubleValue();
                Double Dpm10 = pm10.doubleValue();
                Double Dnh3 = nh3.doubleValue();

                // Populate the list with extracted data
                airQualityInfoList.add(latitudeD.toString());
                airQualityInfoList.add(longitudeD.toString());
                airQualityInfoList.add(localTime);
                Double aqiDouble = aqi.doubleValue();
                airQualityInfoList.add(aqiDouble.toString());
                airQualityInfoList.add(Dco + " µg/m³");
                airQualityInfoList.add(Dno + " µg/m³");
                airQualityInfoList.add(Dno2 + " µg/m³");
                airQualityInfoList.add(Do3 + " µg/m³");
                airQualityInfoList.add(Dso2 + " µg/m³");
                airQualityInfoList.add(Dpm2_5 + " µg/m³");
                airQualityInfoList.add(Dpm10 + " µg/m³");
                airQualityInfoList.add(Dnh3 + " µg/m³");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return airQualityInfoList.toArray(new String[0]);
    }
}