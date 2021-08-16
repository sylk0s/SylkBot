package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class BotGuildConfigsTest extends Command {
    @Override
    public String getHelpInfo() {
        return "Used to determine which roles are set to bot permissions. \n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [admin/mod/everyone/restricted/banned]`";
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
        if(args[1].equals("admin")) { event.getChannel().sendMessage(this.eventBotGuild.adminRoleID).queue();}
        if(args[1].equals("mod")) { event.getChannel().sendMessage(this.eventBotGuild.modRoleID).queue();}
        if(args[1].equals("everyone")) { event.getChannel().sendMessage(this.eventBotGuild.everyoneRoleID).queue();}
        if(args[1].equals("restricted")) { event.getChannel().sendMessage(this.eventBotGuild.restrictedRoleID).queue();}
        if(args[1].equals("banned")) { event.getChannel().sendMessage(this.eventBotGuild.bannedRoleID).queue();}
    }
}
