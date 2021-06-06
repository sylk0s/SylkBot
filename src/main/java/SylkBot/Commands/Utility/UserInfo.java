package SylkBot.Commands.Utility;

import SylkBot.Commands.APICommand;
import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONObject;

public class UserInfo extends APICommand {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "userinfo";
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
        EmbedBuilder info = new EmbedBuilder();

        JSONObject json = discordAPICall("/users/" + args[1]);

        System.out.println(json.toString());

        info.setTitle(json.get("username").toString() + "#" + json.get("discriminator").toString());
        info.setThumbnail("https://cdn.discordapp.com/" + json.get("avatar").toString());
        System.out.println("https://cdn.discordapp.com/" + json.get("id").toString() + "/" + json.get("avatar").toString() + ".png");
        info.addField("User ID: ", json.get("id").toString(),false);

        // grr doesnt work godo fix later

        event.getChannel().sendMessage(info.build()).queue();
    }
}
