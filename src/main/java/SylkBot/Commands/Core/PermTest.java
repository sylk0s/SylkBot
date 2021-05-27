package SylkBot.Commands.Core;

import SylkBot.Commands.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PermTest extends Command {

    @Override
    public String getTrigger() {
        return "perm";
    }

    @Override
    public String getHelpInfo() {
        return " ";
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

    }
}
