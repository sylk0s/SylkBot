package SylkBot.Error;

import SylkBot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class NoArgsError implements Error {

    public void outputError(String name, GuildMessageReceivedEvent event) {
        EmbedBuilder success = new EmbedBuilder();
        success.setColor(errorColor);
        success.setTitle("Please Input a Value");
        success.setDescription("Usage: " + Main.prefix + name + " [insert value here]");
        event.getChannel().sendMessage(success.build()).queue();
    }
}
