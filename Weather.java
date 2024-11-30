// weather object constructed from the JSON retrieved from API call
public class Weather {
    private String cond;
    private String desc;
    private double temperature;

    public String getMainCondition() {
        return cond;
    }
    public void setMainCondition(String mainCondition) {
        this.cond = mainCondition;
        System.out.println("CONDITION: " + mainCondition);
    }
    public String getDescription() {
        return desc;
    }
    public void setDescription(String description) {
        this.desc = description;
    }
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("TEMPERATURE: " + this.temperature);
    }
}
