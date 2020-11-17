package SylkBot.CFun;

import SylkBot.Commands;
import SylkBot.Main;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Hello extends Commands {

    public static String trigger = "hello";
    @Override
    public String getHelpInfo() {return Main.prefix + trigger + " Greets you!\n";}

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {
            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage("Hello " + event.getAuthor().getAsMention()).queue();
        }
    }
}