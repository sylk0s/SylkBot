package SylkBot.Commands.Core;

import SylkBot.Commands.Command;


import SylkBot.Commands.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.security.auth.login.LoginException;

public class Info extends Command {

    @Override
    public String getTrigger() {
        return "info";
    }

    @Override
    public String getHelpInfo() {
        return " Use to get info about the bot\n";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.BANNED;
    }

    @Override
    public boolean hasNoArgs() { return true; }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("SylkBot Info");
        info.setDescription("Info about SylkBot! \n" +
                "Use .help to learn about the commands this bot can do. \n" +
                "TBD"); //TODO Write this info command
        info.setColor(0xff97cb);
        info.setFooter("Created by Sylkos", "https://cdn.discordapp.com/avatars/403610859370446851/5582e1ec5346e420f50a006c35bd2e3b.webp?size=128");

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(info.build()).queue();
        info.clear();
    }
}