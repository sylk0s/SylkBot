package SylkBot.BotObjects;

import SylkBot.SylkBot;
import com.google.gson.annotations.Expose;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Vote {

    public LocalDateTime endTime;
    @Expose public long id;
    @Expose public String stringEndTime;
    @Expose public String name;
    @Expose private String description;
    private int minutes;
    private int hours;
    @Expose public String messageID;
    @Expose public String channelID;
    private int yesVote;
    private int noVote;
    private int abstains;
    @Expose public String authorID;
    private Timer timer;
    @Expose public String guildID;

    public ArrayList<String> deleteList;

    public Vote(Long id, String name, String guildID) {
        this.id = id;
        this.name = name;
        this.guildID = guildID;
        this.description = "";
        deleteList = new ArrayList<>();
        BotGuild.getBotGuild(this.guildID).tempVotes.add(this);
    }

    public void endVote() {
        //to do this i need guild configs
        TextChannel channel = SylkBot.getBot().jda.getTextChannelById(this.channelID);

        channel.retrieveMessageById(this.messageID).queue(m -> {
            m.getReactions().forEach(r -> {
                if(r.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+1F44D")) { this.setYes(r.getCount() - 1); }
                if(r.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+1F44E")) { this.setNo(r.getCount() - 1); }
                if(r.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+270B"))  { this.setAbs(r.getCount() - 1); }
            });

            SylkBot.getBot().jda.retrieveUserById(this.authorID).queue(user -> {
                int voters = this.yesVote + this.noVote;

                String status;
                int fractionRequired = 2;

                EmbedBuilder result = new EmbedBuilder();
                if((voters - this.yesVote) >= 0) { //this logic is super easy but i need to rewrite... i think this causes an error
                    status = "Vote Passed!";
                    result.setColor(0x00FF00);
                } else {
                    status = "Vote Failed :(";
                    result.setColor(0xFF0000);
                }

                result.setTitle(status);
                result.setAuthor(user.getName() + "#" + user.getDiscriminator(), null, user.getEffectiveAvatarUrl());
                result.addField(this.name, this.description, false);
                result.addField("Yes:", String.valueOf(yesVote),true);
                result.addField("No:", String.valueOf(noVote),true);
                result.addField("Abstains:",String.valueOf(abstains),true);

                BotGuild guild = BotGuild.getBotGuild(m.getGuild().getId());
                MessageChannel channel1;
                if(guild.voteResultChannelID.equals("")) {
                    channel1 = m.getChannel();
                } else {
                    channel1 = SylkBot.getBot().jda.getTextChannelById(guild.voteResultChannelID);
                }
                channel1.sendMessage(result.build()).queue();
                m.delete().queue();
            });
        });
        BotGuild.getBotGuild(guildID).votes.remove(this);
        BotGuild.getBotGuild(guildID).saveObject();
    }

    public String getDescription() { return this.description; }

    public String getTitle() { return this.name; }

    public void setDescription(String des) { this.description = des; }

    public void setTime(int hours, int minutes) {
        this.minutes = minutes;
        this.hours = hours;
    }

    public long getId() {
        return this.id;
    }

    public void post(String authorID, String sentInID) {
        this.authorID = authorID;

        LocalDateTime startTime = LocalDateTime.now();

        this.endTime = startTime.plus(this.hours, ChronoUnit.HOURS);
        this.endTime = this.endTime.plus(this.minutes, ChronoUnit.MINUTES);
        this.stringEndTime = endTime.toString();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                endVote();
            }
        };
        timer = new Timer();
        timer.schedule(task, Timestamp.valueOf(endTime));

        EmbedBuilder voteDisplay = new EmbedBuilder();

        User author = SylkBot.getBot().jda.getUserById(authorID);

        voteDisplay.setTitle(this.getTitle());
        voteDisplay.setDescription(this.getDescription());
        voteDisplay.addField("Vote ends at: ", this.endTime.toString(), false);
        voteDisplay.setAuthor(author.getName() + "#" + author.getDiscriminator(), null, author.getEffectiveAvatarUrl());

        BotGuild guild = BotGuild.getBotGuild(guildID);
        TextChannel channel;
        if (guild.votePostChannelID.equals("")) {
            channel = SylkBot.getBot().jda.getTextChannelById(sentInID);
        } else {
            channel = SylkBot.getBot().jda.getTextChannelById(guild.votePostChannelID);
        }

        channel.sendMessage(voteDisplay.build()).queue(m -> {
            m.addReaction("U+1F44D").queue();
            m.addReaction("U+1F44E").queue();
            m.addReaction("U+270B").queue();

            this.messageID = m.getId();
            this.channelID = m.getChannel().getId(); //why not doing this help

            BotGuild.getBotGuild(this.guildID).votes.add(this);
            BotGuild.getBotGuild(this.guildID).saveObject();
        });
    }

    public void post(LocalDateTime finishTime) {
        if(finishTime.isBefore(LocalDateTime.now()))
                this.endVote();
        else {
            LocalDateTime startTime = LocalDateTime.now();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    endVote();
                }
            };
            timer = new Timer();
            timer.schedule(task, Timestamp.valueOf(finishTime));
        }
    }

    public void cancel() {
        if(timer != null) {
            this.timer.cancel();
            BotGuild.getBotGuild(guildID).votes.remove(this.getTitle());
        } else {
            //do something?
        }
    }

    public void setYes(int yes) { this.yesVote = yes; }
    public void setNo(int no) { this.noVote = no; }
    public void setAbs(int abs) { this.abstains = abs; }

}
