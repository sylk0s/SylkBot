package SylkBot.BotObjects;

import SylkBot.SylkBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class JoinLeave extends ListenerAdapter {
    //none of this works and I dont really know why :)

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        System.out.println("joined");
        for(BotGuild guild : SylkBot.getBot().guilds) {
            if(guild.guildID.equals(event.getGuild().getId())) {
                TextChannel channel = SylkBot.getBot().jda.getTextChannelById(guild.joinLeaveChannelID);
                if(channel != null) {
                    channel.sendMessage(event.getMember().getAsMention() + " joined the server").queue();
                }
            }
        }
    }

    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        System.out.println("left");
        for(BotGuild guild : SylkBot.getBot().guilds) {
            if(guild.guildID.equals(event.getGuild().getId())) {
                TextChannel channel = SylkBot.getBot().jda.getTextChannelById(guild.joinLeaveChannelID);
                if(channel != null) {
                    channel.sendMessage(event.getMember().getAsMention() + " left the server").queue();
                }
            }
        }
    }
}
