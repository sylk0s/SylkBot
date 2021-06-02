package SylkBot.Commands.Moderation;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Poll extends Command {
    @Override
    public String getHelpInfo() {
        return "Use to make votes";
    }

    @Override
    public String getTrigger() {
        return "poll";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.MOD;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

    }
}
