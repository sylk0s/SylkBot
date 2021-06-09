package SylkBot.Commands.Moderation;

import SylkBot.Commands.Command;
import SylkBot.Commands.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class VoteList extends Command {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "votes";
    }

    @Override
    public PermType getPermLevel() {
        return null;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        EmbedBuilder votes = new EmbedBuilder();
        votes.setTitle("Vote List");
        SylkBot.getBot().votes.forEach((k,v) -> {
            votes.addField(k,v.getDescription(),false);
        });
        //color?
        event.getChannel().sendMessage(votes.build()).queue();
    }
}
