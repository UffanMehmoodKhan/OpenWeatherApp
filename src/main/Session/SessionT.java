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
    public void getWeatherLoc_LatInput(Session sessionInstance) 
    {
        UIInterface.lat_locInputScreen(sessionInstance);
    }

    @Override
    public void getWeatherLoc_LatProcess(int [][] dataarr)
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
    public void displayscreen(Session sessionInstance, String[] arr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayscreen'");
    } 


    } 

    