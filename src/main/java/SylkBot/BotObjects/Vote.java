package SylkBot.BotObjects;

import SylkBot.Configs.Config;
import SylkBot.Configs.SylkConfigs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.vm.ci.meta.Local;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;

public class Vote {

    /*

    .vote create "title"
    .vote "title" setDescription
    .vote "title" setTime
    .vote "title" post

     */

    LocalDateTime startTime;
    LocalDateTime endTime;

    //i know how to do this with gson pog

    public Vote(int hours, int minutes, String name, GuildMessageReceivedEvent event) {
       startTime = LocalDateTime.now();
       endTime = startTime.plus(hours, ChronoUnit.HOURS);
       endTime = endTime.plus(minutes, ChronoUnit.MINUTES);

        try {
            File newVoteFile = new File(""); //todo
            newVoteFile.createNewFile();
            //setup gson
            // uhhhhhhhhhh
        } catch (IOException e) {
            e.printStackTrace();
        }

       while(true) {
           if(LocalDateTime.now().isAfter(endTime)) {
               this.endVote();
               break;
           }
       }
    }

    private void endVote() {

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
