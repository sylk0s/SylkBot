package SylkBot.Commands.Fun;

import SylkBot.Commands.Command;
import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Hello extends Command {
    @Override
    public String getHelpInfo() {
        return "greets you";
    }

    @Override
    public String getTrigger() {
        return "hello";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
    }

    @Override
    public boolean hasNoArgs() { return true; }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("hello " + event.getAuthor().getAsMention()).queue();
    }
}
