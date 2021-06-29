package SylkBot.Commands.Moderation;

import SylkBot.BotObjects.BotGuild;
import SylkBot.BotObjects.Vote;
import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.w3c.dom.Text;

public class VoteTrigger extends Command {
    @Override
    public String getHelpInfo() {
        return "Use to make votes";
    }

    @Override
    public String getTrigger() {
        return "vote";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.MOD;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public Category getCategory() {
        return Category.MODERATION;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        if(args[1].equals("create")) {
            Vote newVote = new Vote(args[2]);
            newVote.deleteList.add(event.getMessage().getId());
            SylkBot.getBot().votes.put(newVote.getTitle(), newVote);
            event.getChannel().sendMessage(new EmbedBuilder().setTitle("Vote created: ").setDescription(newVote.getTitle()).build()).queue(m -> newVote.deleteList.add(m.getId()));
        } else {
            boolean found = false;
            for(String key : SylkBot.getBot().votes.keySet()) {
                if(key.equals(args[1])) {
                    found = true;
                    Vote vote = SylkBot.getBot().votes.get(key);
                    vote.deleteList.add(event.getMessage().getId());

                    if(args[2].equals("setDescription")) {
                        vote.setDescription(event.getMessage().getContentRaw().replace(".vote " + args[1] + " " + args[2] + " ",""));
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("Description set to:").setDescription(vote.getDescription()).build()).queue(m -> vote.deleteList.add(m.getId()));
                    }

                    if(args[2].equals("delete")) {
                        for(String id : vote.deleteList) {
                            event.getChannel().deleteMessageById(id).queue();
                        }
                        SylkBot.getBot().votes.remove(key);
                    }

                    if(args[2].equals("time")) {
                        vote.setTime(Integer.parseInt(args[3]),Integer.parseInt(args[4]));
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("Vote timer set to:").setDescription("Hours: " + args[3] + "\n" + "Minutes: " + args[4]).build()).queue(m -> vote.deleteList.add(m.getId()));
                    }

                    if(args[2].equals("post")) {
                        for(String id : vote.deleteList) {
                            event.getChannel().deleteMessageById(id).queue();
                        }

                        SylkBot.getBot().getBotG(event.getGuild().getId()).votes.add(vote); //this doesnt work... we also need to figure out how to do the other transfer and split into guilds
                        SylkBot.getBot().getBotG(event.getGuild().getId()).saveObject();
                        vote.authorID = event.getAuthor().getId();
                        vote.post();
                        EmbedBuilder voteDisplay = new EmbedBuilder();

                        voteDisplay.setTitle(vote.getTitle());
                        voteDisplay.setDescription(vote.getDescription());
                        voteDisplay.addField("Vote ends at: ",vote.endTime.toString() ,false);
                        voteDisplay.setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), null, event.getAuthor().getEffectiveAvatarUrl());

                        BotGuild guild = BotGuild.getBotGuild(event.getGuild());
                        TextChannel channel;
                        if(guild.votePostChannelID.equals("")) {
                            channel = event.getChannel();
                        } else {
                            channel = SylkBot.getBot().jda.getTextChannelById(guild.votePostChannelID);
                        }

                        channel.sendMessage(voteDisplay.build()).queue(m -> {
                            m.addReaction("U+1F44D").queue();
                            m.addReaction("U+1F44E").queue();
                            m.addReaction("U+270B").queue();

                            vote.messageID = m.getId();
                            vote.channelID = m.getChannel().getId();
                        });
                    }

                    if(args[2].equals("testmode")) {
                        vote.setTime(0,1);
                    }
                }
            }
            if(!found) {
                event.getChannel().sendMessage("vote not found").queue();
                //todo redo this as an error
            }
        }
    }
}
