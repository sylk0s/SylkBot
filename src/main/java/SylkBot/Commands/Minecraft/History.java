package SylkBot.Commands.Minecraft;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.shanerx.mojang.Mojang;

import java.util.Map;

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
    public boolean hasNoArgs() { return false; }

    //this looks like crap but whatever, ill godo make it pwetty later

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        Mojang api = new Mojang().connect();
        Map<String, Long> names = api.getNameHistoryOfPlayer(api.getUUIDOfUsername(args[1]));
        EmbedBuilder history = new EmbedBuilder();
        history.setTitle("Name history of " + args[1]);
        for (String name : names.keySet()) {
            history.addField(name, "Changed on " + names.get(name), false);
        }
        history.setColor(0xff97cb);
        event.getChannel().sendMessage(history.build()).queue();
        history.clear();
    }
}