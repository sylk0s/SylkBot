package SylkBot.Configs;

import SylkBot.BotObjects.BotGuild;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public abstract class Config {

    public abstract String getPath();
    public abstract Class getConfigClass();

    public static Config setup(Config c) {
        try {
            File newConfigsFile = new File(c.getPath());
            if (newConfigsFile.createNewFile()) {
                c.saveObject(); //WTF is this i didnt notice it before i know how it works but WOW thats crazy that it does... not intended but should be fine?
                //I fixed the sketchy recursion thing this ^ comment is refering to but I'm gonna leave it because I think its funny
            } else {
                Config g = c.setupObject();
                g.saveObject();
                return g;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void saveObject() {
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
            return (Config) new Gson().fromJson(new FileReader(this.getPath()), getConfigClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
