package SylkBot.Commands.Moderation;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GuildSetup extends Command {
    @Override
    public String getHelpInfo() {
        return "For updating guild roles -> reccomended to just modify {guildID}.json\n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [adminrole/modrole/everyonerole/restrictedrole/bannedrole/commandblacklistadd/commandblacklistremove/voterestultchannel/joinleavechannel] [id of element]`";
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
            this.eventBotGuild.updateRoles("admin",args[2]);
        }
        if(args[1].equals("modrole")) {
            this.eventBotGuild.updateRoles("mod",args[2]);
        }
        if(args[1].equals("everyonerole")) {
            this.eventBotGuild.updateRoles("everyone",args[2]);

        }
        if(args[1].equals("restrictedrole")) {
            this.eventBotGuild.updateRoles("restricted",args[2]);
        }
        if(args[1].equals("bannedrole")) {
            this.eventBotGuild.updateRoles("banned",args[2]);
        }
        if(args[1].equals("commandblacklistadd")) {
            this.eventBotGuild.commandBlacklistAdd(args[2]);
        }
        if(args[1].equals("commandblacklistremove")) {
            this.eventBotGuild.commandBlacklistRemove(args[2]);
        }
        if(args[1].equals("catagoryblacklistadd")) {
            this.eventBotGuild.categoryBlacklistAdd(args[2]);
        }
        if(args[1].equals("catagoryblacklistremove")) {
            this.eventBotGuild.categoryBlacklistRemove(args[2]);
        }
        if(args[1].equals("voteresultchannel")) {
            this.eventBotGuild.voteResultChannel(args[2]);
        }
        if(args[1].equals("joinleavechannel")) {
            this.eventBotGuild.joinLeaveChannel(args[2]);
        }
        if(args[1].equals("t4vlink")) {
            this.eventBotGuild.setT4vLinks(args[2], args[3]);
        }
    }
}
