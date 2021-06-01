package SylkBot.Commands.Core;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
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
    public PermType getPermLevel() { return PermType.BOT_ADMIN; }

    @Override
    public boolean hasNoArgs() { return true; } //check

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

    }
}
