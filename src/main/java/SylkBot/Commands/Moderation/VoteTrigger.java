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
        if (args[1].equals("create")) {
            Vote newVote = new Vote(BotGuild.getBotGuild(
                    event.getGuild().getId()).getUniqueID(),
                    event.getMessage().getContentRaw().substring(13),
                    event.getGuild().getId()
                    );
            newVote.deleteList.add(event.getMessage().getId());
            event.getChannel().sendMessage(new EmbedBuilder().setTitle("Vote created: ").setDescription(newVote.getTitle() + "\nVote ID: " + newVote.id + "\nUse this to reference votes in future commands.").build()).queue(m -> newVote.deleteList.add(m.getId()));
        } else {
            BotGuild g = BotGuild.getBotGuild(event.getGuild().getId());
            for(Vote v : g.tempVotes) {
                if (v.getId() == Long.parseLong(args[1])) {
                    v.deleteList.add(event.getMessage().getId());
                    switch (args[2]) {
                            case "setDescription":
                                v.setDescription(event.getMessage().getContentRaw().replace(".vote " + args[1] + " " + args[2] + " ", ""));
                                event.getChannel().sendMessage(new EmbedBuilder().setTitle("Description for " + v.getTitle() + " (ID: " + v.getId() + ") set to: ").setDescription(v.getDescription()).build()).queue(m -> v.deleteList.add(m.getId()));
                                break;

                            case "delete":
                                for (String id : v.deleteList) {
                                    event.getChannel().deleteMessageById(id).queue();
                                }
                                g.tempVotes.remove(v);
                                break;

                            case "time":
                                v.setTime(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                                event.getChannel().sendMessage(new EmbedBuilder().setTitle("Vote timer for " + v.getTitle() + " (ID: " + v.getId() + ") set to: ").setDescription("Hours: " + args[3] + "\n" + "Minutes: " + args[4]).build()).queue(m -> v.deleteList.add(m.getId()));
                                break;

                            case "post":
                                for (String id : v.deleteList) {
                                    event.getChannel().deleteMessageById(id).queue();
                                }
                                v.post(event.getAuthor().getId(), event.getChannel().getId());
                                break;

                            case "testmode":
                                v.setTime(0, 1);
                                break;

                            default:
                                event.getChannel().sendMessage("not a command").queue(); //todo as an error
                        }
                    }
                }
            //if (!g.votes.contains(args[1])) event.getChannel().sendMessage("it FAILED").queue(); //HELP
        }
    }
}
