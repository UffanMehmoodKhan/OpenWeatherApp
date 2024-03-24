package main.Session; 

import main.API.API;
import main.UserInterface.UserInterface;

public interface Session {
	
	API OpenAI = new API();
	public void printSessionType(UserInterface userInterface);
}
