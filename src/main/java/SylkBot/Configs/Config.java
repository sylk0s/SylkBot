package SylkBot.Configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public abstract class Config {

    public abstract void setup();
    public abstract String getPath();

    public static Config setup(Class c) {
        Config config = create(c);
        try {
            File newConfigsFile = new File(config.getPath());
            if (newConfigsFile.createNewFile()) {
                config.setup(); //WTF is this i didnt notice it before i know how it works but WOW thats crazy that it does... not intended but should be fine?
            } else {
                return config.setupObject(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static Config create(Class c) {

        //add here

        if (c == SylkConfigs.class) return new SylkConfigs();
        return null;
    }

    protected void saveObject() {
        try {
            FileWriter writer = new FileWriter(this.getPath());
            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected Config setupObject(Class c) {
        try {
            Gson gson = new Gson();

            //add here

            if (c == SylkConfigs.class) { return gson.fromJson(new FileReader(getPath()), SylkConfigs.class); }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
