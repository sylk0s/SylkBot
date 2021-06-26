package SylkBot.Commands.Utility;

import SylkBot.Commands.APICommand;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.json.JSONObject;

public class Weather extends APICommand {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "weather";
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
    public Category getCategory() {
        return null;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        JSONObject obj = this.weatherAPICall("?q=" + args[1]);

        String tmp = obj.getJSONObject("main").get("temp").toString();

        EmbedBuilder weather = new EmbedBuilder();
        weather.setTitle("Weather for " + args[1]);
        weather.setDescription(tmp);

        event.getChannel().sendMessage(weather.build()).queue();
    }
}
