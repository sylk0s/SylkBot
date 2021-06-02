package SylkBot.Commands.Core.Setup;

import SylkBot.Commands.Command;
import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Setup extends Command {

    @Override
    public String getHelpInfo() {
        return " ";
    }

    @Override
    public String getTrigger() {
        return "setup";
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

    }

    @Override
    public boolean hasNoArgs() { return false; }

    @Override
    public PermType getPermLevel() { return PermType.BOT_ADMIN; }
}
