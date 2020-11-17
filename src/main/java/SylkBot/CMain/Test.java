package SylkBot.CMain;

import SylkBot.Commands;
import SylkBot.Main;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Test extends Commands {

    public static String trigger = " ";
    @Override
    public String getHelpInfo() {return " \n";}

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {

        }
    }
}