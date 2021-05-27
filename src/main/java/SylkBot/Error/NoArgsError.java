package SylkBot.Error;

import SylkBot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class NoArgsError implements Error {

    public void outputError(String name, GuildMessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        error.setColor(errorColor);
        error.setTitle("Please Input a Value");
        error.setDescription("Usage: " + Main.prefix + name + " [insert value here]");
        event.getChannel().sendMessage(error.build()).queue();
        error.clear();
    }
}
