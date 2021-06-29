package SylkBot.Commands.Moderation;

import SylkBot.BotObjects.BotGuild;
import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GuildSetup extends Command {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "guild";
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
        return Category.MODERATION;
    }

    //this doesn't work

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        if(args[1].equals("adminrole")) {
            BotGuild.getBotGuild(event.getGuild().getId()).updateRoles("admin",args[2]);
        }
        if(args[1].equals("modrole")) {
            BotGuild.getBotGuild(event.getGuild().getId()).updateRoles("mod",args[2]);
        }
        if(args[1].equals("everyonerole")) {
            BotGuild.getBotGuild(event.getGuild().getId()).updateRoles("everyone",args[2]);

        }
        if(args[1].equals("restrictedrole")) {
            BotGuild.getBotGuild(event.getGuild().getId()).updateRoles("restricted",args[2]);
        }
        if(args[1].equals("bannedrole")) {
            BotGuild.getBotGuild(event.getGuild().getId()).updateRoles("banned",args[2]);
        }
        if(args[1].equals("commandblacklistadd")) {
            BotGuild.getBotGuild(event.getGuild().getId()).commandBlacklistAdd(args[2]);
        }
        if(args[1].equals("commandblacklistremove")) {
            BotGuild.getBotGuild(event.getGuild().getId()).commandBlacklistRemove(args[2]);
        }
        if(args[1].equals("catagoryblacklistadd")) {
            BotGuild.getBotGuild(event.getGuild().getId()).categoryBlacklistAdd(args[2]);
        }
        if(args[1].equals("catagoryblacklistremove")) {
            BotGuild.getBotGuild(event.getGuild().getId()).categoryBlacklistRemove(args[2]);
        }
        if(args[1].equals("voteresultchannel")) {
            BotGuild.getBotGuild(event.getGuild().getId()).voteResultChannel(args[2]);
        }
        if(args[1].equals("joinleavechannel")) {
            BotGuild.getBotGuild(event.getGuild().getId()).joinLeaveChannel(args[2]);
        }
        if(args[1].equals("t4vlink")) {
            BotGuild.getBotGuild(event.getGuild().getId()).setT4vLinks(args[2], args[3]);
        }
    }
}
