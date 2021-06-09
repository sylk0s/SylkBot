package SylkBot.BotObjects;

import com.google.gson.annotations.Expose;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Vote {

    /*

    .vote create "title"
    .vote "title" setDescription
    .vote "title" setTime
    .vote "title" post

     */

    @Expose private LocalDateTime startTime;
    @Expose private LocalDateTime endTime;
    @Expose private String name;
    //@Expose private String description;

    //i know how to do this with gson pog

    public Vote(String[] args, GuildMessageReceivedEvent event) {
       this.startTime = LocalDateTime.now();
       this.endTime = this.startTime.plus(Integer.parseInt(args[2]), ChronoUnit.HOURS);
       this.endTime = this.endTime.plus(Integer.parseInt(args[3]), ChronoUnit.MINUTES);
       this.name = args[1];

       System.out.println("new vote");
       System.out.println(endTime);
       //grrrrrrrr do i want this to be multiple commands
       //this.description =

        /*

        try {
            File newVoteFile = new File(args[1] + ".json"); //todo
            if(newVoteFile.createNewFile()) {

            } else {

            }
            //setup gson
            // uhhhhhhhhhh
        } catch (IOException e) {
            e.printStackTrace();
        }

         */

       while(true) {
           if(LocalDateTime.now().isAfter(endTime)) {
               this.endVote();
               break;
           }
       }
    }

    private void endVote() {
        //to do this i need guild configs
        System.out.println("vote ended");
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
