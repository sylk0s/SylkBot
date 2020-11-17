package SylkBot.CMain;

import SylkBot.Commands;
import SylkBot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Commands {

    public static String trigger = "help";
    @Override
    public String getHelpInfo() {return Main.prefix + trigger + " Use to show all commands \n" +
            Main.prefix + trigger + " [command] Use to get help with a specific command\n";}

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {
            if (args.length < 2) {
                EmbedBuilder help = new EmbedBuilder();
                help.setTitle("SylkBot Help");
                String description = " ";
                for (String key: Main.CommandList.keySet()) {
                    description = description + Main.CommandList.get(key).getHelpInfo();
                }
                help.setDescription(description);
                help.setColor(0xff97cb);

                event.getChannel().sendTyping().queue();
                event.getChannel().sendMessage(help.build()).queue();
                help.clear();
            } else {
                for(String key: Main.CommandList.keySet()) {
                    if (args[1].equalsIgnoreCase(key)) {
                        EmbedBuilder help = new EmbedBuilder();
                        help.setTitle("How to use the " + key + " command.");
                        help.setDescription(Main.CommandList.get(key).getHelpInfo());
                        help.setColor(0xff97cb);

                        event.getChannel().sendTyping().queue();
                        event.getChannel().sendMessage(help.build()).queue();
                        help.clear();
                    }
                }
            }
        }
    }
}