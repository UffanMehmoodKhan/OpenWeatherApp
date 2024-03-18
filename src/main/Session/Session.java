package main.Session;

import main.UserInterface.UserInterface;
import main.API.API;

public interface Session {
	
	public static API APIInterface = null;
	public void printSessionType(UserInterface userInterface);
	

	//Methods for API calls ----> made to DB first ----> then to API calls ----> then back ----------------<
}
