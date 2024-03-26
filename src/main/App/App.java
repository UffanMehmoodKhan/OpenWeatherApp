package main.App;

import main.Database.txt;
import main.Session.Session;
import main.Session.SessionT;
import main.Session.SessionDT;

public class App{

	Session session;
	
	//App constructor take console argument
    App(String argvString) {
		if(argvString.equals("terminal")){ 			//creates a terminal Session
			System.out.println("is this terminal even working?");
			session = new SessionT();
		}
		else if(argvString.equals("desktop")){		//creates a desktop Session
			System.out.println("is this desktop even working?");
			session = new SessionDT(); 
		}
    }
	Session getSession()
	{
        return session;
    }

    public static void main(String[] args) {
        txt t1=new txt();
		 String [] arr={"my","hello"};
		// t1.insertWeatherInfo(arr);
		t1.insertForecastInfo(arr);
		t1.insertAirInfo(arr);
		t1.retrieveWeatherInfo(0, 0);
    }
	
}