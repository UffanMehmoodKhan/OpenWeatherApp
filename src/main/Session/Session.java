package main.Session; 

import main.API.API;
import main.Database.*;
import main.UserInterface.UserInterface;

public interface Session {
	
	API OpenAI = new API();
	DB OW_DB[] = null;
	public void printSessionType(UserInterface userInterface);

}
