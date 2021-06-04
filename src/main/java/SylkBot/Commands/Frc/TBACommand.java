package SylkBot.Commands.Frc;

import SylkBot.Commands.Command;
import SylkBot.SylkBot;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public abstract class TBACommand extends Command {
    protected final String tbaAPILink = "https://www.thebluealliance.com/api/v3";

    public JSONObject apiCall(String call) {
        try {
            String end = tbaAPILink + call;
            HttpResponse<JsonNode> response = Unirest.get(end)
                    .header("X-TBA-Auth-Key", SylkBot.getBot().configs.tbaToken)
                    .asJson();
            return response.getBody().getObject();
        } catch (UnirestException e) {
            System.out.println("Unirest issue");
            return null;
        }
    }
}
