package main.UserInterface;

public class UserInterface {
	private String UI;

	public UserInterface (String ui){
		this.UI = ui;
		System.out.println("UI " + this.UI);
	}
	
	public String getUI() {
        return UI;
    }
}

