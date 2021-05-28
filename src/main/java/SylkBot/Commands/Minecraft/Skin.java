package SylkBot.Commands.Minecraft;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.shanerx.mojang.Mojang;

public class Skin extends Command {

    @Override
    public String getHelpInfo() {
        return "Get the skin for a player";
    }

    @Override
    public String getTrigger() {
        return "skin";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
    }

    //this also looks REALLY bad
    //plan is to implement some 3d render api for the skin
    //https://crafatar.com/ <--- here

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        Mojang api = new Mojang();
        event.getChannel().sendMessage(api.getPlayerProfile(api.getUUIDOfUsername(args[1])).getTextures().get().getSkin().get().toString()).queue();
    }
}
