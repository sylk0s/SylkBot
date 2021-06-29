package SylkBot.Configs;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class SylkConfigs extends Config {

    private final String path = "sylkConfigs.json";

    @Expose public String botToken = "token";
    @Expose public String prefix = ".";
    @Expose public String tbaToken = "token";
    @Expose public String firstUsername = "";
    @Expose public String firstToken = "token";
    @Expose public String googleToken = "token";
    @Expose public String weatherToken = "token";
    @Expose public ArrayList<String[]> channelLinkIDs = new ArrayList<>();

    public String getPath() {
        return this.path;
    }

    @Override
    public Class getConfigClass() {
        return SylkConfigs.class;
    }
}
