package SylkBot.Commands.Utility;

import SylkBot.BotObjects.ChannelLink;
import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ChannelLinkTrigger extends Command {

    //I havent wrote this yet but I KNOW its gonna be very sketchy

    @Override
    public String getHelpInfo() {
        return "For linking two channels together.\n " +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [channel id] [channel id]`";
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
        this.eventBotGuild.addLink(link);
        ChannelLink.createLink(args[1], args[2]);
    }
}
