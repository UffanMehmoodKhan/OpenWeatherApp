package main.UserInterface;

public class desktop implements UserInterface{

	private String interfaceType;
	
	public desktop(String ui) {
		interfaceType = ui;	
	}


	@Override
	public String getUI(){
		return interfaceType;
	}
 
}
