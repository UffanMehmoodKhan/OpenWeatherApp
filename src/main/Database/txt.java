package main.Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.API.API;

public class txt implements DB {

    public void insertWeatherInfo(String[] data) {
        try {
            FileWriter fw1 = new FileWriter("weather.txt", true);
            for (int i = 0; i < data.length; i++) {
                fw1.write(data[i]);
                fw1.write(" ");
            }
            fw1.append('\n');
            fw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertForecastInfo(String[] data) {
        try {
            FileWriter fw1 = new FileWriter("forecast.txt", true);
            for (int i = 0; i < data.length; i++) {
                fw1.write(data[i]);
                fw1.write(" ");
            }
            fw1.append('\n');
            fw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertAirInfo(String[] data) {
        try {
            FileWriter fw1 = new FileWriter("pollution.txt", true);
            for (int i = 0; i < data.length; i++) {
                fw1.write(data[i]);
                fw1.write(" ");
            }
            fw1.append('\n');
            fw1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] retrieveWeatherInfo(double lat, double lon) {
        try (BufferedReader br = new BufferedReader(new FileReader("weather.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                double savedLat = Double.parseDouble(parts[0]);
                double savedLon = Double.parseDouble(parts[1]);
                if (savedLat == lat && savedLon == lon) {
                    return parts;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Sorry data does not exist");
        return null;
    }

    public String[] retrieveWeatherInfo(String city) {
        try (BufferedReader br = new BufferedReader(new FileReader("weather.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(city)) {
                    return line.split(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sorry data does not exist");
        return null;
    }

    public String[] retrieveForecastInfo(double lat, double lon) {
        try (BufferedReader br = new BufferedReader(new FileReader("forecast.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                double savedLat = Double.parseDouble(parts[0]);
                double savedLon = Double.parseDouble(parts[1]);
                if (savedLat == lat && savedLon == lon) {
                    return parts;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Sorry data does not exist");
        return null;
    }

    public String[] retrieveForecastInfo(String city) {
        try (BufferedReader br = new BufferedReader(new FileReader("forecast.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(city)) {
                    return line.split(" ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sorry data does not exist");
        return null;
    }

    public String[] retrieveAirInfo(double lat, double lon) {
        try (BufferedReader br = new BufferedReader(new FileReader("pollution.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                double savedLat = Double.parseDouble(parts[0]);
                double savedLon = Double.parseDouble(parts[1]);
                if (savedLat == lat && savedLon == lon) {
                    return parts;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Sorry data does not exist");
        return null;
    }

    // Session methods
    public String[] GetWeather(double lat, double lon) {
        String[] lines = retrieveWeatherInfo(lat, lon);
        if (lines != null) {
            return lines;
        } else {
            String[] info = OpenAI.getCurrentWeather(lat, lon);
            if (info != null) {
                insertWeatherInfo(info);
                return info;
            }
        }
        return null;
    }

    public String[] GetWeather(String city) {
        String[] lines = retrieveWeatherInfo(city);
        if (lines != null) {
            return lines;
        } else {
            String[] info = OpenAI.getCurrentWeather(city);
            if (info != null) {
                insertWeatherInfo(info);
                return info;
            }
        }
        return null;
    }

    public String[] GetForecast(double lat, double lon) {
        String[] lines = retrieveForecastInfo(lat, lon);
        if (lines != null) {
            return lines;
        } else {
            String[] info = OpenAI.get5DayForecast(lat, lon);
            if (info != null) {
                insertForecastInfo(info);
                return info;
            }
        }
        return null;
    }

    public String[] GetForecast(String city) {
        String[] lines = retrieveForecastInfo(city);
        if (lines != null) {
            return lines;
        } else {
            String[] info = OpenAI.get5DayForecast(city);
            if (info != null) {
                insertForecastInfo(info);
                return info;
            }
        }
        return null;
    }

    public String[] GetAirPoll(double lat, double lon) {
        String[] lines = retrieveAirInfo(lat, lon);
        if (lines != null) {
            return lines;
        } else {
            String[] info = OpenAI.getAirQuality(lat, lon);
            if (info != null) {
                insertAirInfo(info);
                return info;
            }
        }
        return null;
    }
}
