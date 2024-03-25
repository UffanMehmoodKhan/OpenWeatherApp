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

public class AirPollution {
    // Private method to fetch Air Quality data from OpenWeatherMap API
	@SuppressWarnings("deprecation")
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
				Long no = (Long) componentsObject.get("no");
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return airQualityInfoList.toArray(new String[0]);
	}
}