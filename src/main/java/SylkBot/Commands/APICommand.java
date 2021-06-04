package SylkBot.Commands;

import SylkBot.SylkBot;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class APICommand extends Command {
    protected final String tbaAPILink = "https://www.thebluealliance.com/api/v3";
    protected final String firstAPILink = "https://frc-api.firstinspires.org/v2.0/";

    //make this prettier? can i consolidate?

    public JSONArray tbaApiCall(String call) {
        try {
            String end = tbaAPILink + call;
            System.out.println(end);
            HttpResponse<JsonNode> response = Unirest.get(end)
                    .header("X-TBA-Auth-Key", SylkBot.getBot().configs.tbaToken)
                    .asJson();
            return response.getBody().getArray();
        } catch (UnirestException e) {
            System.out.println("Unirest issue");
            return null;
        }
    }

    public JSONObject firstAPICall(String call) {
        try {
            String end = firstAPILink + call;
            HttpResponse<JsonNode> response = Unirest.get(end)
                    .header("Authorization", "Basic " + firstAPILink)
                    .header("If-Modified-Since", "")
                    .asJson();
            return response.getBody().getObject();
        } catch (UnirestException e) {
            System.out.println("Unirest issue");
            return null;
        }
    }
}
