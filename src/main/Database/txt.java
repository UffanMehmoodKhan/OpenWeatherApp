package main.Database;

import java.io.*;
import main.API.API;

public class txt implements DB {
    private static final String WEATHER_FILE = "weather.txt";
    private static final String FORECAST_FILE = "forecast.txt";
    private static final String AIR_FILE = "pollution.txt";

    @Override
    public void insertWeatherInfo(String[] data) {
        try (FileWriter fw = new FileWriter(WEATHER_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String datum : data) {
                out.print(datum + " ");
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertForecastInfo(String[] data) {
        try (FileWriter fw = new FileWriter(FORECAST_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String datum : data) {
                out.print(datum + " ");
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAirInfo(String[] data) {
        try (FileWriter fw = new FileWriter(AIR_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String datum : data) {
                out.print(datum + " ");
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] retrieveInfoFromFile(String fileName, String searchCriteria) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                if (line.contains(searchCriteria)) {
                    return line.split(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sorry, data does not exist");
        }
        
        return new String[]{"0"};
    }

    @Override
    public String[] retrieveWeatherInfo(double lat, double lon) {
<<<<<<< HEAD
        String line = "0";
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            while ((line = br.readLine()) != null) {
                double num = Double.parseDouble(line);
                if (num == lat)
                {
                    flag = true;
                }
                if (num == lon)
                {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        if (flag) {
            return new String[]{line};
        } else {
            System.out.println("Sorry data does not exist");
            return new String[]{"0"};
        }
    }
	public String[] retrieveWeatherInfo(String city) {
        String line = "0";
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            while ((line = br.readLine()) != null) {
                if (line.contains(city)) {
                    return new String[]{line};
                }
            }
        } catch (IOException e) {
            System.out.println("sorry data does not exist");
            e.getMessage();
        }
        return new String[]{"0"};
=======
        String searchCriteria = lat + " " + lon;
        return retrieveInfoFromFile(WEATHER_FILE, searchCriteria);
    }

    @Override
    public String[] retrieveWeatherInfo(String city) {
        return retrieveInfoFromFile(WEATHER_FILE, city);
>>>>>>> danish1
    }

    @Override
    public String[] retrieveForecastInfo(double lat, double lon) {
        String searchCriteria = lat + " " + lon;
        return retrieveInfoFromFile(FORECAST_FILE, searchCriteria);
    }

    @Override
    public String[] retrieveForecastInfo(String city) {
        return retrieveInfoFromFile(FORECAST_FILE, city);
    }

    @Override
    public String[] retrieveAirInfo(double lat, double lon) {
        String searchCriteria = lat + " " + lon;
        return retrieveInfoFromFile(AIR_FILE, searchCriteria);
    }

    // Session methods
    @Override
    public String[] GetWeather(double lat, double lon) {
        String[] lines = retrieveWeatherInfo(lat, lon);
        if (lines[0].equals("0")) {
            String[] info = OpenAI.getCurrentWeather(lat, lon);
            insertWeatherInfo(info);
            return info;
        } else {
            return lines;
        }
    }

    @Override
    public String[] GetWeather(String city) {
        String[] lines = retrieveWeatherInfo(city);
        if (lines[0].equals("0")) {
            String[] info = OpenAI.getCurrentWeather(city);
            insertWeatherInfo(info);
            return info;
        } else {
            return lines;
        }
    }

    @Override
    public String[] GetForecast(double lat, double lon) {
        String[] lines = retrieveForecastInfo(lat, lon);
        if (lines[0].equals("0")) {
            String[] info = OpenAI.get5DayForecast(lat, lon);
            insertForecastInfo(info);
            return info;
        } else {
            return lines;
        }
    }

    @Override
    public String[] GetForecast(String city) {
        String[] lines = retrieveForecastInfo(city);
        if (lines[0].equals("0")) {
            String[] info = OpenAI.get5DayForecast(city);
            insertForecastInfo(info);
            return info;
        } else {
            return lines;
        }
    }

    @Override
    public String[] GetAirPoll(double lat, double lon) {
        String[] lines = retrieveAirInfo(lat, lon);
        if (lines[0].equals("0")) {
            String[] info = OpenAI.getAirQuality(lat, lon);
            insertAirInfo(info);
            return info;
        } else {
            return lines;
        }
    }
}
