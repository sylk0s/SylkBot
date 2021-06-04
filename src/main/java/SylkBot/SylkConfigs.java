package SylkBot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.*;

public class SylkConfigs {

    private static final String path = "sylkConfigs.json";

    @Expose public String botToken;
    @Expose public String prefix;
    @Expose public String tbaToken;
    @Expose public String firstUsername;
    @Expose public String firstToken;

    public static SylkConfigs setup() {
        try {
            File newConfigsFile = new File(path);
            if (newConfigsFile.createNewFile()) {
                SylkConfigs newConfigs = new SylkConfigs();
                newConfigs.botToken = "token";
                newConfigs.prefix = ".";
                newConfigs.tbaToken = "token";
                newConfigs.firstUsername = "";
                newConfigs.firstToken = "token";
                newConfigs.saveObject();
                return newConfigs;
            } else {
                System.out.println("Found existing sylkConfigs.json");
                return setupObject();
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    private static SylkConfigs setupObject() {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new FileReader(path), SylkConfigs.class);
        } catch (FileNotFoundException e) {
            System.out.println("Somehow didnt find the file???");
            return null;
        }
    }

    private void saveObject() {
        try {
            FileWriter writer = new FileWriter(path);
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
