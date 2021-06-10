package SylkBot.Commands.Utility;

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
        return null;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        TextChannel c1 = SylkBot.getBot().jda.getTextChannelById(args[1]);
        TextChannel c2 = SylkBot.getBot().jda.getTextChannelById(args[2]);

        ChannelLink.createLink(c1,c2);
    }
}
