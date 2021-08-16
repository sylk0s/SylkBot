package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Configs.Config;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UpdateConfigs extends Command {
    @Override
    public String getHelpInfo() {
        return "Updates all config files manually. \n"+
                " `" + this.bot.configs.prefix + this.getTrigger() + "`";
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
        return Category.UTILITY;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        this.bot.guilds.forEach(Config::saveObject);
        this.bot.configs.saveObject();
    }
}
