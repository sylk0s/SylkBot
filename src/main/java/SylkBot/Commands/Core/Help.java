package SylkBot.Commands.Core;

import SylkBot.BotObjects.BotGuild;
import SylkBot.Commands.Command;

import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Help extends Command {

    @Override
    public String getTrigger() {
        return "help";
    }

    @Override
    public String getHelpInfo() {
        return " Use to show all commands \n" +
                " `[command] Use to get help with a specific command`\n";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.RESTRICTED;
    }

    @Override
    public boolean hasNoArgs() { return true; }

    @Override
    public Category getCategory() {
        return Category.CORE;
    }

    //todo rewrite this so i can be lazy
    //also note i need to go fix the ones that work properly now before i fix the ones later

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        EmbedBuilder help = new EmbedBuilder();
        if(args.length < 2) {
            help.setTitle("SylkBot Help: ");
            help.setDescription("For help with a specific command, use .help [command]");
            List<Role> roleList = event.getGuild().getMember(event.getAuthor()).getRoles();
            for(Command command : SylkBot.getBot().commands) {
                if(BotGuild.getBotGuild(event.getGuild().getId()).roleCheck(roleList, command)) {
                    help.addField(command.getTrigger(), command.getHelpInfo(), false);
                }
            }
        } else {
            Command command = getCommandFromTrigger(args[1]);
            if(BotGuild.getBotGuild(event.getGuild().getId()).roleCheck(event.getGuild().getMember(event.getAuthor()).getRoles(), command)){
                help.setTitle(command.getTrigger());
                help.setDescription(command.getHelpInfo());
            }
        }

        event.getChannel().sendMessage(help.build()).queue();
    }

    private static Command getCommandFromTrigger(String trigger) {
        for(Command command : SylkBot.getBot().commands) {
            if(command.getTrigger().equals(trigger)) return command;
        }
        return null;
    }
    /*

        if (args.length < 2) {
            EmbedBuilder help = new EmbedBuilder();
            help.setTitle("SylkBot Help");
            String description = "";
            for (Command command : SylkBot.getBot().commands) {
                help.addField(command.getTrigger(),command.getHelpInfo() ,false);
            }
            help.setDescription(description);
            help.setColor(0xff97cb);

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(help.build()).queue();
            help.clear();
        } else {
            for (Command command : SylkBot.getBot().commands) {
                if (args[1].equalsIgnoreCase(command.getTrigger())) {
                    EmbedBuilder help = new EmbedBuilder();
                    help.setTitle("How to use the " + command.getTrigger() + " command.");
                    help.setDescription(command.getHelpInfo());
                    help.setColor(0xff97cb);

                    event.getChannel().sendTyping().queue();
                    event.getChannel().sendMessage(help.build()).queue();
                    help.clear();
                }
            }
        }
     */

}