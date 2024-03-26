package main.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.API.API;

public class txt implements DB {
    public void insertWeatherInfo(String[] data) {
        File f1 = new File("weather.txt");
        try {
            FileWriter fw1 = new FileWriter("weather.txt", true);
            for (int i = 0; i < data.length; i++) {
                fw1.write(data[i]);
                fw1.write(" ");
            }
            fw1.flush();
            fw1.append('\n');
            fw1.close();
        } catch (IOException e) {
            e.getMessage();
        }

    }

    public void insertForecastInfo(String[] data) {
        File f1 = new File("forecast.txt");
        try {
            FileWriter fw1 = new FileWriter("forecast.txt", true);
            for (int i = 0; i < data.length; i++) {
                fw1.write(data[i]);
                fw1.write(" ");
            }
            fw1.flush();
            fw1.append('\n');
            fw1.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void insertAirInfo(String[] data) {
        File f1 = new File("pollution.txt");
        try {
            FileWriter fw1 = new FileWriter("pollution.txt", true);
            for (int i = 0; i < data.length; i++) {
                fw1.write(data[i]);
                fw1.write(" ");
            }
            fw1.flush();
            fw1.append('\n');
            fw1.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String[] retrieveWeatherInfo(double lat, double lon) {
        String line = "0";
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            while ((line = br.readLine()) != null) {
                double num = Double.parseDouble(line);
                if (num == lat) {
                    flag = true;
                }
                if (num == lon) {
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
            e.getMessage();
        }
        System.out.println("sorry data does not exist");
        return new String[]{"0"};
    }
    public String[] retrieveForecastInfo(double lat, double lon) {
        String line = "0";
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("forecast.txt"));
            while ((line = br.readLine()) != null) {
                double num = Double.parseDouble(line);
                if (num == lat) {
                    flag = true;
                }
                if (num == lon) {
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

    public String[] retrieveForecastInfo(String city) {
        String line = "0";
        try {
            BufferedReader br = new BufferedReader(new FileReader("forecast.txt"));
            while ((line = br.readLine()) != null) {
                if (line.contains(city)) {
                    return new String[]{line};
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("Sorry data does not exist");
        return new String[]{"0"};
    }

    public String[] retrieveAirInfo(double lat, double lon) {
        String line = "0";
        boolean flag = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader("pollution.txt"));
            while ((line = br.readLine()) != null) {
                double num = Double.parseDouble(line);
                if (num == lat) {
                    flag = true;
                }
                if (num == lon) {
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

    //Session methods
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