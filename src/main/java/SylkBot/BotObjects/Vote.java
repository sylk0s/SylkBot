package SylkBot.BotObjects;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Vote {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private String description;
    private GuildMessageReceivedEvent event;
    private int minutes;
    private int hours;

    public Vote(String[] args, GuildMessageReceivedEvent event) {

        //rewrite this crap

       this.name = args[1];
       this.event = event;

       System.out.println("new vote");
       System.out.println(endTime);
    }

    public void endVote() {
        //to do this i need guild configs
        System.out.println("vote ended");
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public void setTime(int minutes, int hours) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public String getTitle() {
        return this.name;
    }

    public void post() {
        this.startTime = LocalDateTime.now();
        this.endTime = this.startTime.plus(this.hours, ChronoUnit.HOURS);
        this.endTime = this.endTime.plus(this.minutes, ChronoUnit.MINUTES);

        VoteRunner runner = new VoteRunner(this.endTime, this);
    }
}
