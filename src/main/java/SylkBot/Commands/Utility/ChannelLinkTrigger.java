package SylkBot.Commands.Utility;

import SylkBot.BotObjects.BotGuild;
import SylkBot.BotObjects.ChannelLink;
import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ChannelLinkTrigger extends Command {

    //I havent wrote this yet but I KNOW its gonna be very sketchy

    @Override
    public String getHelpInfo() {
        return "for linking two channels together";
    }

    @Override
    public String getTrigger() {
        return "channellink";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.BOT_ADMIN;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public Category getCategory() {
        return Category.UTILITY;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        String[] link = {args[1],args[2]};
        BotGuild.getBotGuild(event.getGuild().getId()).addLink(link);
        ChannelLink.createLink(args[1], args[2]);
    }
}
