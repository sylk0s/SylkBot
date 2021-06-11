package SylkBot.BotObjects;

import SylkBot.Configs.Config;
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
}
