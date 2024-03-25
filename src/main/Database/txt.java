package main.Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class txt implements DB
{
	public void insertWeatherInfo(String[] data)
	{
      File f1=new File("weather.txt");
	  try{
		FileWriter fw1=new FileWriter("weather.txt");
		for(int i=0;i<data.length;i++){
		fw1.write(data[i]);
		fw1.write(" ");
	}
	//fw1.flush();
	//fw1.close();
	  }
	  catch(IOException e){
		e.getMessage();
	  }
	}

	
	public void insertForecastInfo(String[] data)
	{

	}
    public void insertAirInfo(String[] data)
	{

	}
    public void retrieveWeatherInfo(String[] data)
	{

	}
    public void retrieveForecastInfo(String[] data)
	{

	}
    public void retrieveAirInfo(String[] data)
	{
		
	}
	

}
