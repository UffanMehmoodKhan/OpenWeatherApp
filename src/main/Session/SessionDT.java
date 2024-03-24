package main.Session;

import main.UserInterface.UserInterface;
import main.UserInterface.desktop;

public class SessionDT implements Session {

	public SessionDT(){

		System.out.println("Desktop is Operational");
		UserInterface UIInterface = new desktop("desktop");
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
		OpenAI.getCurrentWeather("Lahore");

		
		// all methods should be executed here as per user specs, with exception handling
		// boolean status = true;
		// while (status == true) {
		//
		// 	// TODO
		//
		// }
	}
 
	
}
