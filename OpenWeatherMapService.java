import java.util.*;
import java.io.*;
import java.net.*;

// fetches weather data from the OpenWeatherMap API and notifies observers
public class OpenWeatherMapService implements WeatherService {

    private final String apiKey;
    private final String location;
    private final List<WeatherObserver> observers;

    //constructs the object with given API key and location
    public OpenWeatherMapService(String apiKey, String location) {
        this.apiKey = apiKey;
        this.location = location;
        this.observers = new ArrayList<>();
    }

    //fetches the current weather data from the OpenWeatherMap API, and executes network call in a separate thread to avoid blocking
    @Override
    public void fetchWeather() {
        new Thread(() -> {
            try {
                String urlString = String.format(
                        "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                        URLEncoder.encode(location, "UTF-8"), apiKey
                );
                URL url = new URL(urlString);
                System.out.println("URL: " + url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    System.err.println("Failed to fetch weather data. Response Code: " + responseCode);
                    return;
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String jsonString = response.toString();
                System.out.println("JSON Response: " + jsonString);
                Weather weather = WeatherBuilder.buildFromJson(jsonString);
                notifyObservers(weather);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // notifies all observers with updated weather data
    private void notifyObservers(Weather weather) {
        for (WeatherObserver observer : observers) {
            observer.updateWeather(weather);
        }
    }

    // registers observers to receive weather updates
    @Override
    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    // removes observer from receiving updates
    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }
}