package main.App;

import main.Session.Session;


public class App{

	Session session = null;
	
    App(String argvString) {
        session = new Session(argvString);
    }
	Session getSession() {
        return session;
    }

    public static void main(String[] args) {

        System.out.println("\n" + args[0] + " application config");
		App OpenWeatherMap = new App(args[0]);
		OpenWeatherMap.getSession();
    }
	
}
