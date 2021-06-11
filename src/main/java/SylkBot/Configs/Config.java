package SylkBot.Configs;

import SylkBot.BotObjects.BotGuild;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public abstract class Config {

    public abstract void setupConfig();
    public abstract String getPath();

    public static Config setup(Config c) {
        try {
            File newConfigsFile = new File(c.getPath());
            if (newConfigsFile.createNewFile()) {
                c.setupConfig(); //WTF is this i didnt notice it before i know how it works but WOW thats crazy that it does... not intended but should be fine?
                //I fixed the sketchy recursion thing this ^ comment is refering to but I'm gonna leave it because I think its funny
            } else {
                return c.setupObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    protected void saveObject() {
        try {
            FileWriter writer = new FileWriter(this.getPath());
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Config setupObject() {
        try {
            Gson gson = new Gson();

            //add here

            if (this instanceof BotGuild) { return gson.fromJson(new FileReader(this.getPath()), BotGuild.class); }
            if (this instanceof SylkConfigs) { return gson.fromJson(new FileReader(this.getPath()), SylkConfigs.class); }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
