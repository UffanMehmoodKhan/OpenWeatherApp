package main.Session;

import main.UserInterface.UserInterface;
import main.UserInterface.terminal;

public class SessionT implements Session {
	
	public SessionT(){

		System.out.println("Terminal is Operational");
		UserInterface UIInterface = new terminal("terminal");
		printSessionType(UIInterface);
		main();
	}

	@Override
    public void printSessionType(UserInterface userInterface) {
		System.out.println(userInterface.getUI()); 
    }

	public static void main() {
		System.out.println("This will be the control mechanism of the terminal, where all of the tasks will occur");
		System.out.println("This is the current API Key: " + OpenAI.returnKey());
		OpenAI.getCurrentWeather(31.5497, 74.3436);
		OpenAI.get5DayForecast("Lahore");
		OpenAI.getAirQuality(31.5467, 74.3401);
		
		// all methods should be executed here as per user specs, with exception handling
		// boolean status = true;
		// while (status == true) {
		//
		// 	// TODO
		//
		// }
	}
 
}
