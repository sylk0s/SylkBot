package SylkBot.Commands.Minecraft;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.shanerx.mojang.Mojang;

import java.util.Map;

public class PlayerInfo extends Command {
    @Override
    public String getHelpInfo() {
        return " get basic info about a minecraft account";
    }

    @Override
    public String getTrigger() {
        return "player";
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
        return Category.MINECRAFT;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        Mojang api = new Mojang().connect();
        String uuid = api.getUUIDOfUsername(args[1]);

        EmbedBuilder info = new EmbedBuilder();
        info.setTitle(args[1]);
        info.setThumbnail("https://crafatar.com/avatars/" + uuid);
        info.addField("UUID:", uuid, true);
        Map<String, Long> names = api.getNameHistoryOfPlayer(uuid);
        String accountNames = "";
        for (String name : names.keySet()) accountNames = accountNames + name + "\n";
        info.addField("Name History: ", accountNames, false);
        info.setImage("https://crafatar.com/renders/body/" + uuid);
        info.setColor(0x55ff55);

        event.getChannel().sendMessage(info.build()).queue();
        info.clear();
    }
}
