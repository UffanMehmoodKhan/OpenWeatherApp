package main.Session;

import main.UserInterface.UserInterface;
import main.UserInterface.desktop;

public class SessionDT implements Session {

	public SessionDT(){
		System.out.println("Desktop is Operational");
		UserInterface UIInterface = new desktop("desktop");
		printSessionType(UIInterface);
	}

	@Override
    public void printSessionType(UserInterface userInterface) {
		System.out.println(userInterface.getUI());
        
    }
	
}
