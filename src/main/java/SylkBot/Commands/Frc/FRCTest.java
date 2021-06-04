package SylkBot.Commands.Frc;

import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class FRCTest extends TBACommand {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "teamname";
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
    public void run(String[] args, GuildMessageReceivedEvent event) {
        String call = "/team/" + "frc" + args[1];
        event.getChannel().sendMessage(apiCall(call).get("nickname").toString()).queue();
    }
}
