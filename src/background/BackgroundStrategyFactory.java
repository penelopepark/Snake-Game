package background;

import weather.Weather;

//creates appropriate background.BackgroundStrategy instances based on weather conditions.
public class BackgroundStrategyFactory {

    //creates and returns an instance of a background strategy based on the current weather object
    public static BackgroundStrategy createStrategy(Weather weather) {
        double temp = weather.getTemperature();
        String condition = weather.getMainCondition().toLowerCase();
        if (temp < -5) {
            System.out.println("SNOWING");
            return new SnowyBackgroundStrategy();

        } else if (temp >= -5 && temp < 10) {
            if (condition.equalsIgnoreCase("rain") || condition.equalsIgnoreCase("drizzle") || condition.equalsIgnoreCase("thunderstorm")) {
                System.out.println("RAINING");
                return new RainyBackgroundStrategy();
            } else {
                System.out.println("DEFAULT");
                return new DefaultBackgroundStrategy();
            }
        } else {
            if (condition.equalsIgnoreCase("rain") || condition.equalsIgnoreCase("drizzle") || condition.equalsIgnoreCase("thunderstorm")) {
                System.out.println("RAINING");
                return new RainyBackgroundStrategy();
            } else {
                System.out.println("DEFAULT");
                return new SunnyBackgroundStrategy();
            }
        }
    }
}