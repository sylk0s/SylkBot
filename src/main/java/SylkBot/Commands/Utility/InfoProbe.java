package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class InfoProbe extends Command {
    @Override
    public String getHelpInfo() {
        return "Debug tool... dont use lol\n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + "`";
    }

    @Override
    public String getTrigger() {
        return "probe";
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

    }
}
