package main.Session; 

import main.Database.SQL;
import main.Database.txt;
import main.UserInterface.UserInterface; 
import main.UserInterface.desktop; 

public class SessionDT implements Session { 

    UserInterface UIInterface; 

    public SessionDT()
    { 
        System.out.println("Desktop is Operational"); 
        UIInterface = new desktop();
        this.getWelcomeScreen();
        System.out.println("Desktop is Operational");
		OW_DB[0] = new SQL(); OW_DB[1] = new txt();
    }    

    @Override 
    public void getWelcomeScreen() { 
        UIInterface.welcomeScreen(this); 
    } 

    @Override
    public void test(Session sessionInstance,String[] a ) {
        // Method implementation
    }

    @Override
    public void getWeatherLoc_LatInput(Session sessionInstance) {
        UIInterface.lat_locInputScreen(sessionInstance);
    }

    @Override
    public void getWeatherLoc_LatProcess(double[][] dataarr) {
        if (dataarr == null || dataarr.length == 0) {
            // Handle null or empty input array
            return;
        }

        int count = dataarr.length;
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < dataarr[i].length; j++) {
                System.out.print(dataarr[i][j] + " ");
            }
            System.out.println();
        }

        String[][] arr = new String[count][];
        for (int x = 0; x < count; x++) {   
            String[] weatherData = APIInterface.getWeather(dataarr[x][0],dataarr[x][1]);
            arr[x] = weatherData;
        }
        this.displayscreen(this, arr, count);
    }

    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
       UIInterface.locationInputScreen(sessionInstance);
    }

    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        if (dataarra == null || dataarra.length == 0) {
            // Handle null or empty input array
            return;
        }

        int count = dataarra.length;
        System.out.println(count);
        for(int z = 0; z < count; z++) {
            System.out.println(dataarra[z]);
        }
        String[][] arr = new String[count][];
        // This is where the data will be processed
        // API call will be made here
        for (int i = 0; i < count ; i++) {
            String[] weatherData = APIInterface.getWeather(dataarra[i]);

            arr[i] = weatherData;
        }

       
        this.displayscreen(this, arr, count);
    }
      
    @Override
    public void displayscreen(Session sessionInstance,String[][] weatherarr,int count){
        UIInterface.displayscreen(sessionInstance,weatherarr,count);
    }
}
