package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Configs.Config;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UpdateConfigs extends Command {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "updatec";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.BOT_ADMIN;
    }

    @Override
    public boolean hasNoArgs() {
        return true;
    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        SylkBot.getBot().guilds.forEach(Config::saveObject);
        SylkBot.getBot().configs.saveObject();
    }
}
