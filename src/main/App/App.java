package main.App;

import main.Session.*;
import main.Database.*;


public class App{

	Session session;
	DB db;
	
	//App constructor take console argument
    App(String argvString, String ui) {
		if(!ui.equals(null)){
			if(ui.equals("SQL")){ 			//creates a terminal Session
				System.out.println("is this sql even working?");
				db = new SQL();
			}
			else if(ui.equals("txt")){		//creates a desktop Session
				System.out.println("is this txt even working?");
				db = new txt();
			}
		}
		if(!argvString.equals(null)){
			if(argvString.equals("terminal")){ 			//creates a terminal Session
			System.out.println("is this terminal even working?");
			session = new SessionT(db);
			}
			else if(argvString.equals("desktop")){		//creates a desktop Session
				System.out.println("is this desktop even working?");
				session = new SessionDT(db); 
			}
		}
		
    }
	Session getSession()
	{
        return session;
    }

    public static void main(String[] args)
	{
		
        System.out.println("\n" + args[0] + " application config"); 
		App OpenWeatherMap = new App(args[0], args[1]); //Initialize OpenWeatherMap App object
		OpenWeatherMap.getSession();
    }
}