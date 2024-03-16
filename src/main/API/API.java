package main.API;

public class API {
	private String API_Key;

	public API(){
		API_Key = "open_weather_api_key_fill";
		System.out.println(API_Key);
	}

	public void returnKey(){
		System.out.println(API_Key);
	}
}


