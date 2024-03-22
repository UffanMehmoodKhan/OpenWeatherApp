package main.UserInterface;

public class terminal implements UserInterface{

	String interfaceType;
	
	public terminal(String ui) {
		interfaceType = ui;
	}

	@Override
	public String getUI(){
		return UIInterface.getUI();
	}

}