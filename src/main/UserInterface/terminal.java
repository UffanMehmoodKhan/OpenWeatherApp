package main.UserInterface;

public class terminal implements UserInterface{

	private String interfaceType;
	
	public terminal(String ui) {
		interfaceType = ui;	
	}


	@Override
	public String getUI(){
		return interfaceType;
	}

}