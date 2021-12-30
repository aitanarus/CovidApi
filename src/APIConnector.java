import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;


public class APIConnector {

    private static APIConnector instance;

    String rapidAPIHost = "covid-193.p.rapidapi.com";
    String rapidAPIKey = "35259ae04fmsh67dd00c8c8381f4p18ec07jsn91ff33ef1de3";

    public static synchronized APIConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new APIConnector();
        }
        return instance;
    }

    public JSONArray getCountries(){

    JSONArray result = new JSONArray();

    try {
        HttpRequest countriesRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/countries"))
                .header("x-rapidapi-host", rapidAPIHost)
                .header("x-rapidapi-key",rapidAPIKey )
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> countriesResponse = HttpClient.newHttpClient().send(countriesRequest, HttpResponse.BodyHandlers.ofString());
        JSONObject myObject = new JSONObject(countriesResponse.body());
        JSONArray countries = myObject.getJSONArray("response");
        result = countries;

    } catch (InterruptedException | IOException e) {
        e.printStackTrace();
    }
    return result;
    }


    public JSONObject getCountryStats(String country){

        JSONObject result = new JSONObject();

        try {
            HttpRequest statsRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-193.p.rapidapi.com/statistics?country="+country))
                    .header("x-rapidapi-host", "covid-193.p.rapidapi.com")
                    .header("x-rapidapi-key", "35259ae04fmsh67dd00c8c8381f4p18ec07jsn91ff33ef1de3")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> statsResponse = HttpClient.newHttpClient().send(statsRequest, HttpResponse.BodyHandlers.ofString());
            JSONObject myObject = new JSONObject(statsResponse.body());
            JSONArray response = myObject.getJSONArray("response");
            JSONObject stats = response.getJSONObject(0);

            result = stats;

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject getCases(String country) {
        JSONObject stats = getCountryStats(country);
        JSONObject cases = stats.getJSONObject("cases");
        return cases;
    }


}


/*



 */