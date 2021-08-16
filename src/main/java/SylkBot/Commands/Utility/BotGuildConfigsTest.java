package SylkBot.Commands.Utility;

import SylkBot.BotObjects.BotGuild;
import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class BotGuildConfigsTest extends Command {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "guildtest";
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
        return Category.UTILITY;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        if(args[1].equals("admin")) { event.getChannel().sendMessage(BotGuild.getBotGuild(event.getGuild().getId()).adminRoleID).queue();}
        if(args[1].equals("mod")) { event.getChannel().sendMessage(BotGuild.getBotGuild(event.getGuild().getId()).modRoleID).queue();}
        if(args[1].equals("everyone")) { event.getChannel().sendMessage(BotGuild.getBotGuild(event.getGuild().getId()).everyoneRoleID).queue();}
        if(args[1].equals("restricted")) { event.getChannel().sendMessage(BotGuild.getBotGuild(event.getGuild().getId()).restrictedRoleID).queue();}
        if(args[1].equals("banned")) { event.getChannel().sendMessage(BotGuild.getBotGuild(event.getGuild().getId()).bannedRoleID).queue();}
    }
}
