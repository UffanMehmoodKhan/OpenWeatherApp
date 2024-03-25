package main.Session; 
import main.UserInterface.UserInterface; 
import main.UserInterface.terminal; 
public class SessionT implements Session 
{ 
    UserInterface UIInterface = new terminal(); 
    public SessionT()
    { 
        System.out.println("Terminal is Operational"); 
        this.getWelcomeScreen();
    } 

    @Override 
    public void getWelcomeScreen()
    { 
      UIInterface.welcomeScreen(this);
    } 

    @Override
    public void test(Session sessionInstance,String[] a)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }
    @Override
    public void getLoc_LatInpu(Session sessionInstance, int choice) 
    {
        UIInterface.lat_locInputScreen(sessionInstance, choice);
    }
    @Override
    public void getWeatherLoc_LatProcess(double [][] dataarr)
    {
        //display the data in the terminal
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Latitude of location " + (i+1) + " is " + dataarr[i][0]);
            System.out.println("Longitude of location " + (i+1) + " is " + dataarr[i][1]);
        }
    }

    @Override
    public void getWeatherLocationInput(Session sessionInstance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherLocationInput'");
    }

    @Override
    public void getWeatherLocationProcess(String[] dataarra) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherLocationProcess'");
       // will process the data and then call the display screen
    }

    @Override
    public void displayWeatherscreen(Session sessionInstance, String[][] arr,int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayscreen'");
    }

   

    @Override
    public void getAirPollutionLoc_LatProcess(double[][] dataarr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAirPollutionLoc_LatProcess'");
    }

    @Override
    public void displayAirPollutionScreen(Session sessionInstance, String[][] arr, int count) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayAirPollutionScreen'");
    }

   


    } 

    