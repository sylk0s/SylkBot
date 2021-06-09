package SylkBot.Commands.Moderation;

import SylkBot.BotObjects.Vote;
import SylkBot.Commands.Command;
import SylkBot.Commands.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

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

    //todo here i want to make a list every time this is called in the vote object so that when vote.post() is called it deletes all the messages

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        if(args[1].equals("create")) {
            Vote newVote = new Vote(args, event);
            SylkBot.getBot().voteHolder.update(newVote);
            newVote.deleteList.add(event.getMessage());
        } else {
            boolean found = false;
            for(String key : SylkBot.getBot().votes.keySet()) {
                if(key.equals(args[1])) {
                    found = true;
                    Vote vote = SylkBot.getBot().votes.get(key);
                    vote.deleteList.add(event.getMessage());
                    //here is where we can check for args[2] and add other functionality

                    if(args[2].equals("setDescription")) {
                        vote.setDescription(event.getMessage().getContentRaw().replace(".vote " + args[1] + " " + args[2] + " ",""));
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("Description set to:").setDescription(vote.getDescription()).build()).queue(m -> vote.deleteList.add(m));
                    }

                    if(args[2].equals("delete")) {

                    }

                    if(args[2].equals("setTime")) {
                        vote.setTime(Integer.parseInt(args[3]),Integer.parseInt(args[4]));
                        event.getChannel().sendMessage(new EmbedBuilder().setTitle("Vote timer set to:").setDescription("Hours: " + args[3] + "\n" + "Minutes: " + args[4]).build()).queue(m -> vote.deleteList.add(m));
                    }

                    if(args[2].equals("post")) {
                        EmbedBuilder voteDisplay = new EmbedBuilder();

                        voteDisplay.setTitle(vote.getTitle());
                        voteDisplay.setDescription(vote.getDescription());

                        TextChannel channel = event.getChannel(); // this is so i can feed it a channel from configs later
                        channel.sendMessage(voteDisplay.build()).queue(m -> {
                            m.addReaction("U+1F44D").queue();
                            m.addReaction("U+1F44E").queue();
                            m.addReaction("U+270B").queue();
                            //need some way to get these out of here
                            vote.message = m;
                        });

                        vote.post();
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
