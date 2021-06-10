package SylkBot.BotObjects;

import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
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
    private int yesVote;
    private int noVote;
    private int abstains;


    public ArrayList<Message> deleteList;

    public Vote(String[] args, GuildMessageReceivedEvent event) {
       this.name = args[2];
       this.event = event;
       this.description = "";
       deleteList = new ArrayList<>();
    }

    public void endVote() {
        //to do this i need guild configs
        System.out.println("vote ended");

        int fractionRequired = 2;

        message.getReactions().forEach(m -> {
            if(m.getReactionEmote().equals(MessageReaction.ReactionEmote.fromUnicode("U+1F44D", SylkBot.getBot().jda))) setYes(m.getCount()); //yes count
            if(m.getReactionEmote().equals(MessageReaction.ReactionEmote.fromUnicode("U+1F44E", SylkBot.getBot().jda))) setNo(m.getCount()); //no count
            if(m.getReactionEmote().equals(MessageReaction.ReactionEmote.fromUnicode("U+270B", SylkBot.getBot().jda))) setAbs(m.getCount()); //abstain count
        });

        int voters = this.yesVote + this.noVote;

        String status;

        EmbedBuilder result = new EmbedBuilder();

        if((voters - this.yesVote) > voters/fractionRequired) {
            status = "Vote Passed!";
            result.setColor(0x00FF00);
        } else {
            status = "Vote Failed :(";
            result.setColor(0xFF0000);
        }

        result.setTitle(status);
        result.setDescription(this.name);
        result.addField("Yes:", String.valueOf(yesVote),true);
        result.addField("No:", String.valueOf(noVote),true);
        result.addField("Abstains:",String.valueOf(abstains),true);

        this.event.getChannel().sendMessage(result.build()).queue();
    }

    public String getDescription() { return this.description; }

    public String getTitle() { return this.name; }

    public void setDescription(String des) { this.description = des; }

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

    public void setYes(int yes) { this.yesVote = yes; }
    public void setNo(int no) { this.noVote = no; }
    public void setAbs(int abs) { this.abstains = abs; }
}
