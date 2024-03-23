package main.Session; 

import main.API.API; 

public interface Session { 
    public static API APIInterface = null; 
    public void getWelcomeScreen(); 
    public void test();
    public void process(); 
    //Methods for API calls ----> made to DB first ----> then to API calls ----> then back ----------------< 

} 