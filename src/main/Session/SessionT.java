package main.Session;

import main.UserInterface.UserInterface;
import main.UserInterface.terminal;

public class SessionT implements Session {
	
	public SessionT(){
		System.out.println("Terminal is Operational");
		UserInterface UIInterface = new terminal("terminal");
		printSessionType(UIInterface);
	}

	@Override
    public void printSessionType(UserInterface userInterface) {
		System.out.println(userInterface.getUI()); 
    }
 
}
