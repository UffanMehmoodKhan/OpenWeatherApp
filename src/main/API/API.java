package main.API;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

public class API {
	private String API_Key;
private HttpResponse<String> response ;

	public API(double latitude, double longitude){
			             String apiKey = "abd8b18b14cccdad663c3f5e6c462ab0";
//        double latitude = 32.166351;
//        double longitude = 74.195900;

       String url = "https://api.openweathermap.org/data/2.5/weather"
               + "?lat=" + latitude
               + "&lon=" + longitude
               + "&appid=" + apiKey;

       HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(url))
               .GET()
               .build();

       try {
           response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
             System.out.println(response.body());
       } catch (IOException | InterruptedException e) {
           e.printStackTrace();
       }
	}
	// public void returnKey(){
	// 	System.out.println(API_Key);
	// }
}


