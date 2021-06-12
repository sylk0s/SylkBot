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
        return null;
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
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.updateRoles("admin",args[2]);
                }
            }
        }
        if(args[1].equals("modrole")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.updateRoles("mod",args[2]);
                }
            }
        }
        if(args[1].equals("everyonerole")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.updateRoles("everyone",args[2]);
                }
            }
        }
        if(args[1].equals("restrictedrole")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.updateRoles("restricted",args[2]);
                }
            }
        }
        if(args[1].equals("bannedrole")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.updateRoles("banned",args[2]);
                }
            }
        }
        if(args[1].equals("removecommand")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    for(Command command : SylkBot.getBot().commands) {
                        if(command.getTrigger().equals(args[2])) {
                            guild.commandBlacklist.add(command.getTrigger());
                        }
                    }
                }
            }
        }
        if(args[1].equals("removecatagory")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    //todo
                }
            }
        }
        if(args[1].equals("commandblacklistadd")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.commandBlacklistAdd(args[2]);
                }
            }
        }
        if(args[1].equals("commandblacklistremove")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.commandBlacklistRemove(args[2]);
                }
            }
        }
        if(args[1].equals("catagoryblacklistadd")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.catagoryBlacklistAdd(args[2]);
                }
            }
        }
        if(args[1].equals("catagoryblacklistremove")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.catagoryBlacklistRemove(args[2]);
                }
            }
        }
        if(args[1].equals("voteresultchannel")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.voteResultChannel(args[2]); //fix
                }
            }
        }
        if(args[1].equals("joinleavechannel")) {
            for(BotGuild guild : SylkBot.getBot().guilds) {
                if(guild.guildID.equals(event.getGuild().getId())) {
                    guild.joinLeaveChannel(args[2]);
                }
            }
        }
    }
}
