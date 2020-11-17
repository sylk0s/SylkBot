package SylkBot.CFun;

import SylkBot.Commands;
import SylkBot.Main;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ping extends Commands {

    public static String trigger = "ping";
    @Override
    public String getHelpInfo() {return Main.prefix + trigger + " Pings yourself\n" +
            Main.prefix + trigger + " [discord name as mention] Will ping someone else\n"; }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {
            if (args.length < 2) {
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue();
            } else {
                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(args[1]).queue();
            }
        }
    }
}