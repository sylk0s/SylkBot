package SylkBot.Commands.Core;

import SylkBot.Commands.Command;
import SylkBot.Main;

import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Command {

    @Override
    public String getTrigger() {
        return "help";
    }

    @Override
    public String getHelpInfo() {
        return Main.prefix + this.getTrigger() + " Use to show all commands \n" +
                Main.prefix + this.getTrigger() + " `[command] Use to get help with a specific command`\n";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.RESTRICTED;
    }

    //todo rewrite this so i can be lazy

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        if (args.length < 2) {
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("SylkBot Help");
            String description = " ";
            for (String key : Main.CommandList.keySet()) {
                description = description + Main.CommandList.get(key).getHelpInfo();
            }
            help.setDescription(description);
            help.setColor(0xff97cb);

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(help.build()).queue();
            help.clear();
        } else {
            for (String key : Main.CommandList.keySet()) {
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