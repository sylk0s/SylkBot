package SylkBot.Commands.Frc;

import SylkBot.Commands.APICommand;
import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TeamName extends APICommand {
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
        event.getChannel().sendMessage(tbaApiCall(call).getJSONObject(0).get("nickname").toString()).queue();
    }
}
