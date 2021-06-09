package SylkBot.Commands.Moderation;

import SylkBot.BotObjects.Vote;
import SylkBot.Commands.Command;
import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class VoteTrigger extends Command {
    @Override
    public String getHelpInfo() {
        return "Use to make votes";
    }

    @Override
    public String getTrigger() {
        return "vote";
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
        new Vote(Integer.valueOf(args[2]), Integer.valueOf(args[3]), args[1],event);
    }
}
