package main.API;
import java.net.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Scanner;

public class API {

	float LatCord; float LongCord;
	String city, country, location;

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

			//If connection is made;
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
                        + responseCode);
			} else{

				StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);


                //JSON simple library Setup with Maven is used to convert strings to JSON
                // JSONParser parse = new JSONParser();
                // JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                // //Get the first JSON object in the JSON array
                // System.out.println(dataObject.get(0));

                // JSONObject countryData = (JSONObject) dataObject.get(0);

                // System.out.println(countryData.get("woeid"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String returnKey(){
		return (API_Key);
	}
}


