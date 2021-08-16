package SylkBot.Commands.Minecraft;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.shanerx.mojang.Mojang;

public class UUID extends Command {

    @Override
    public String getHelpInfo() {
        return "Input minecraft username and this command will give the UUID. \n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [username] `";
    }

    @Override
    public String getTrigger() {
        return "uuid";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
    }

    @Override
    public boolean hasNoArgs() { return false; }

    @Override
    public Category getCategory() {
        return Category.MINECRAFT;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        Mojang api = new Mojang().connect();
        event.getChannel().sendMessage(api.getUUIDOfUsername(args[1])).queue();
    }
}
