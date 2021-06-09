package SylkBot.BotObjects;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Vote {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private String description;
    private GuildMessageReceivedEvent event;
    private int minutes;
    private int hours;
    public Message message;

    public ArrayList<Message> deleteList;

    public Vote(String[] args, GuildMessageReceivedEvent event) {
       this.name = args[1];
       this.event = event;
    }

    public void endVote() {
        //to do this i need guild configs
        System.out.println("vote ended");

        int voters = message.getReactions().size();
        int fractionRequired = 2;

        /*

        this is actually wrong in logic
        use Message interface to see how to get total number of one type of reaction.

         */

        if ((voters - 0/*abstains*/) > voters/fractionRequired) {
            //won
        } else if ((voters - 0/*abstains*/) < voters/fractionRequired) {
            //loss
        } else {
            //tie
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getTitle() {
        return this.name;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public void setTime(int minutes, int hours) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public void post() {
        this.startTime = LocalDateTime.now();
        this.endTime = this.startTime.plus(this.hours, ChronoUnit.HOURS);
        this.endTime = this.endTime.plus(this.minutes, ChronoUnit.MINUTES);

        VoteRunner runner = new VoteRunner(this.endTime, this);
    }
}
