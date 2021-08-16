package SylkBot.Commands.Frc;

import SylkBot.Commands.APICommand;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TeamName extends APICommand {
    @Override
    public String getHelpInfo() {
        return "gives the name of an FRC team when given it's number\n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [team number] `";
    }

    @Override
    public String getTrigger() {
        return "teamname";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
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
        String call = "/team/" + "frc" + args[1];
        event.getChannel().sendMessage(tbaApiCall(call).getJSONObject(0).get("nickname").toString()).queue();
    }
}
