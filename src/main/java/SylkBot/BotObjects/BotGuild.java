package SylkBot.BotObjects;

import SylkBot.Commands.Command;
import SylkBot.Configs.Config;
import SylkBot.SylkBot;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class BotGuild extends Config {

    //ok what stuff do we need here
    @Expose public String adminRoleID; //all perms to the bot
    @Expose public String modRoleID; //most perms
    @Expose public String everyoneRoleID; //the norm
    @Expose public String restrictedRoleID; //less perms
    @Expose public String bannedRoleID; //no perms

    @Expose public ArrayList<String> commandBlacklist;
    @Expose public ArrayList<String> catagoryBlacklist;

    @Expose public String voteResultChannelID;
    @Expose public String joinLeaveChannelID;

    @Expose public String guildID;
    @Expose public ArrayList<Vote> votes;

    //must init guild id beforehand

    @Override
    public void setupConfig() {
        this.adminRoleID = "";
        this.modRoleID = "";
        this.everyoneRoleID = "";
        this.restrictedRoleID = "";
        this.bannedRoleID = "";

        this.commandBlacklist = new ArrayList<>();
        this.catagoryBlacklist = new ArrayList<>();

        this.joinLeaveChannelID = "";
        this.voteResultChannelID = "";

        this.votes = new ArrayList<>();
        this.saveObject();
    }

    @Override
    public String getPath() {
        return this.guildID + ".json";
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
    public void catagoryBlacklistAdd(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) {
                this.commandBlacklist.add(trigger);
            }
        }
        this.saveObject();
    }
    public void catagoryBlacklistRemove(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) {
                this.commandBlacklist.add(trigger);
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
}
