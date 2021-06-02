package SylkBot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.*;

public class SylkConfigs {

    private static final String path = "sylkConfigs.json";

    @Expose public String token;
    @Expose public String prefix;


    public SylkConfigs() throws FileNotFoundException{
        try {
            File newConfigsFile = new File(path);
            if(newConfigsFile.createNewFile()) {
                this.token = "";
                this.prefix = ".";
            }
        } catch (IOException e) {
            System.out.println("Tried to recreate the configs file (this is normal)");
        }
        initialize(path);
    }

    public SylkConfigs initialize(String path) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(path), SylkConfigs.class);
    }

    public void save(String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this, writer);
        writer.close();
    }
}
