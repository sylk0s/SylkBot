package SylkBot.Configs;

import com.google.gson.annotations.Expose;

public class SylkConfigs extends Config {

    private final String path = "sylkConfigs.json";

    @Expose public String botToken;
    @Expose public String prefix;
    @Expose public String tbaToken;
    @Expose public String firstUsername;
    @Expose public String firstToken;
    @Expose public String googleToken;

    public void setup() {
        this.botToken = "token";
        this.prefix = ".";
        this.tbaToken = "token";
        this.firstUsername = "";
        this.firstToken = "token";
        this.googleToken = "token";
        this.saveObject();
    }

    public String getPath() {
        return this.path;
    }
    public void generate(String id) {}
}
