package main.App;

import main.Database.DB;
import main.Database.SQL;
import main.Database.txt;
import main.Session.Session;
import main.Session.SessionDT;
import main.Session.SessionT;

public class App{

	Session session;
	static DB db;
	
	//App constructor take console argument
    App(String argvString, String ui) {
		if(!ui.equals(null)){
			if(ui.equals("SQL")){ 			//creates a terminal Session
				db = new SQL();
			}
			else if(ui.equals("txt")){		//creates a desktop Session
				db = new txt();
			}
		}
		if(!argvString.equals(null)){

			if(argvString.equals("terminal")){ 			//creates a terminal Session
				session = new SessionT(db);

			}
			else if(argvString.equals("desktop")){		//creates a desktop Sessi
				session = new SessionDT(db); 
			}
		}
		
    }
	Session getSession(){
        return session;
    }
	
	public static void clearDatabase(){
		db.clearCache();
	}
    public static void main(String[] args){
		
		System.out.println("\n" + args[0] + " application config"); 
		App OpenWeatherMap = new App(args[0], args[1]); //Initialize OpenWeatherMap App object
		OpenWeatherMap.getSession();
    }
}