package SylkBot.Commands.Minecraft;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.shanerx.mojang.Mojang;

public class History extends Command {
    @Override
    public String getHelpInfo() {
        return "Shows account history";
    }

    @Override
    public String getTrigger() {
        return "history";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        Mojang api = new Mojang().connect();
        String message = " ";
        api.getNameHistoryOfPlayer(api.getUUIDOfUsername(args[1])).forEach(o -> message + o); //i dont know what goin on here but will fix in the morning
    }
}
