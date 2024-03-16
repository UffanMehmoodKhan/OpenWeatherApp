package main.UserInterface;


public class UserInterface {
	private String UI;

	public UserInterface (String ui){
		this.UI = ui;
		System.out.println("UI " + this.UI);
	}
}

class terminal extends UserInterface{
	
	public terminal(String ui) {
		super(ui);
		//TODOs
	}
	public void readAPIkey(){
		//TODO
	}
	
}

class desktop extends UserInterface{

	public desktop(String ui) {
		super(ui);
		//TODO Auto-generated constructor stub
	}
 
}
