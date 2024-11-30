import org.json.*;

// responsible to construct a weather obj from JSON
public class WeatherBuilder {

    // returns a weather object from our json string retrieved from weather API
    public static Weather buildFromJson(String jsonString) {
        Weather weather = new Weather();
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray weatherArray = json.getJSONArray("weather");
            JSONObject weatherObj = weatherArray.getJSONObject(0);
            weather.setMainCondition(weatherObj.getString("main"));
            weather.setDescription(weatherObj.getString("description"));
            JSONObject main = json.getJSONObject("main");
            weather.setTemperature(main.getDouble("temp"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}