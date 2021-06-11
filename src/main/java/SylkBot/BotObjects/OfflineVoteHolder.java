package SylkBot.BotObjects;

import SylkBot.SylkBot;

public class OfflineVoteHolder {

    public OfflineVoteHolder() {

    }

    public void add(Vote vote) {
        SylkBot.getBot().votes.put(vote.getTitle(),vote);

        //updates json
    }

    public void remove(String key) {
        SylkBot.getBot().votes.remove(key);
    }

    /*

    public Vote setup(Class c) {
        Vote vote = new Vote(); //bruh
        try {
            File newConfigsFile = new File(vote.getPath());
            if (newConfigsFile.createNewFile()) {
                config.setup();
                return config;
            } else {
                return config.setupObject(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static Vote create(Class c) {

        //add here

        if (c == SylkConfigs.class) return new SylkConfigs();
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

     */

}
