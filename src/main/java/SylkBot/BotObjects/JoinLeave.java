package SylkBot.BotObjects;

import SylkBot.SylkBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Objects;

public class JoinLeave extends ListenerAdapter {
    //none of this works and I dont really know why :)

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
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
        for(BotGuild guild : SylkBot.getBot().guilds) {
            if(guild.guildID.equals(event.getGuild().getId())) {
                TextChannel channel = SylkBot.getBot().jda.getTextChannelById(guild.joinLeaveChannelID);
                if(channel != null) {
                    channel.sendMessage("<@" + event.getMember().getId() + "> left the server").queue();
                }
            }
        }
    }
}
