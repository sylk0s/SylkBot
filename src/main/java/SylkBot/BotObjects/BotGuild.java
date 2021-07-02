package SylkBot.BotObjects;

import SylkBot.Commands.Command;
import SylkBot.Configs.Config;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import com.google.gson.annotations.Expose;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BotGuild extends Config {

    //ok what stuff do we need here
    @Expose public String adminRoleID = ""; //all perms to the bot
    @Expose public String modRoleID = ""; //most perms
    @Expose public String everyoneRoleID = ""; //the norm
    @Expose public String restrictedRoleID = ""; //less perms
    @Expose public String bannedRoleID = ""; //no perms

    @Expose public ArrayList<String> commandBlacklist = new ArrayList<>();
    @Expose public ArrayList<String> categoryBlacklist = new ArrayList<>();

    @Expose public String voteResultChannelID = "";
    @Expose public String votePostChannelID = "";
    @Expose public String joinLeaveChannelID = "";
    @Expose public String loggerChannelID = "";

    @Expose public String guildID = "";
    @Expose public ArrayList<Vote> votes = new ArrayList<>();
    public ArrayList<Vote> tempVotes = new ArrayList<>();

    @Expose public ArrayList<String[]> t4vLinks = new ArrayList<>();

    @Expose public long uniqueIDCount = 0;

    //must init guild id beforehand

    @Override
    public String getPath() {
        return this.guildID + ".json";
    }

    @Override
    public Class getConfigClass() {
        return BotGuild.class;
    }

    public void updateRoles(String role, String id) {
        if(role.equals("admin")) this.adminRoleID = id;
        if(role.equals("mod")) this.modRoleID = id;
        if(role.equals("everyone")) this.everyoneRoleID = id;
        if(role.equals("restricted")) this.restrictedRoleID = id;
        if(role.equals("banned")) this.bannedRoleID = id;
        this.saveObject();
    }

    public void commandBlacklistAdd(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) {
                this.commandBlacklist.add(trigger);
            }
        }
        this.saveObject();
    }
    public void commandBlacklistRemove(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) {
                this.commandBlacklist.add(trigger);
            }
        }
        this.saveObject();
    }
    public void categoryBlacklistAdd(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) {
                this.categoryBlacklist.add(trigger);
            }
        }
        this.saveObject();
    }
    public void categoryBlacklistRemove(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) {
                this.categoryBlacklist.add(trigger);
            }
        }
        this.saveObject();
    }
    public void voteResultChannel(String id) {
        this.voteResultChannelID = id;
        this.saveObject();
    }
    public void joinLeaveChannel(String id) {
        this.joinLeaveChannelID = id;
        this.saveObject();
    }

    public void setLoggerChannelID(String id) {
        this.loggerChannelID = id;
        this.saveObject();
    }

    public void setT4vLinks(String vcID, String t4vID) {
        String[] link = {vcID, t4vID};
        this.t4vLinks.add(link);
        this.saveObject();
    }

    public Guild getGuild() {
        return SylkBot.getBot().jda.getGuildById(this.guildID);
    }

    public String getId() {return this.guildID;}

    public static BotGuild getBotGuild(String id) {
        for(BotGuild guild : SylkBot.getBot().guilds) {
            if(guild.guildID.equals(id)) {
                return guild;
            }
        }
        return null;
    }

    private PermType getPerm(Role role) {
        if(role.getId().equals(adminRoleID)) { return PermType.BOT_ADMIN; }
        if(role.getId().equals(modRoleID)) { return PermType.MOD; }
        if(role.getId().equals(everyoneRoleID)) { return PermType.EVERYONE; }
        if(role.getId().equals(restrictedRoleID)) { return PermType.RESTRICTED; }
        if(role.getId().equals(bannedRoleID)) { return PermType.BANNED; }
        return null;
    }

    private boolean permSet() {
        if(this.adminRoleID.equals("")) return false;
        if(this.modRoleID.equals("")) return false;
        if(this.everyoneRoleID.equals("")) return false;
        if(this.restrictedRoleID.equals("")) return false;
        if(this.bannedRoleID.equals("")) return false;
        return true;
    }

    public boolean roleCheck(List<Role> roles, Command command) {
        if(!permSet()) { return true; } else {
            for (Role role : roles) {
                if (getPerm(role) != null) {
                    if (getPerm(role).ordinal() >= command.getPermLevel().ordinal()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public long getUniqueID() {
        uniqueIDCount++;
        return uniqueIDCount;
    }
}