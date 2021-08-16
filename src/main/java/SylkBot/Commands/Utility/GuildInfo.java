package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GuildInfo extends Command {
    @Override
    public String getHelpInfo() {
        return "Gives info about current server";
    }

    @Override
    public String getTrigger() {
        return "guildinfo";
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
        return Category.UTILITY;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Guild Info: " + event.getGuild().getName());
        //add more here
        info.addField("Owner: ", event.getGuild().getOwner().getEffectiveName(), false);
        info.setThumbnail(event.getGuild().getIconUrl());

        event.getChannel().sendMessage(info.build()).queue();
    }
}
