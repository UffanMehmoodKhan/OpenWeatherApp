package main.UserInterface;

public class desktop implements UserInterface{

	String interfaceType;
	
	public desktop(String ui) {
		interfaceType = ui;	
	}


	@Override
	public String getUI(){
		return interfaceType;
	}
 
}
