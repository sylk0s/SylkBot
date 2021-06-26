package SylkBot.Commands.Moderation;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
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
        return PermType.EVERYONE;
    }

    @Override
    public boolean hasNoArgs() {
        return true;
    }

    @Override
    public Category getCategory() {
        return Category.MODERATION;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        EmbedBuilder votes = new EmbedBuilder();
        votes.setTitle("Vote List");
        for(String key : SylkBot.getBot().votes.keySet()) {
            votes.addField(key,SylkBot.getBot().votes.get(key).getDescription(),false);
        }
        //color?
        event.getChannel().sendMessage(votes.build()).queue();
    }
}
