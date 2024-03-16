package main.Session;

import main.UserInterface.UserInterface;
import main.API.API;



public class Session {
	UserInterface UI = null;
	

	public Session(String UI_type){
		System.out.println("Session Started!");
		UI = new UserInterface(UI_type);
		
	}

}
