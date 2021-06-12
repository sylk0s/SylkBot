package SylkBot.Commands.Frc;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Mallet extends Command {
    @Override
    public String getHelpInfo() {
        return "bonks people";
    }

    @Override
    public String getTrigger() {
        return "bonk";
    }

    @Override
    public PermType getPermLevel() {
        return null;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public Category getCategory() {
        return Category.FRC;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(args[1] + " was bonked by the Mallet of Loving Correction!").queue();
    }
}
