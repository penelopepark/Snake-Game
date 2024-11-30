public interface WeatherService {
    void fetchWeather();
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
}