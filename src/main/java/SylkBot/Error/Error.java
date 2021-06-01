package SylkBot.Error;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class Error {
    protected int errorColor = 0xff3923;
    protected String errorTitle;
    protected String errorDescription;

    public void outputError(GuildMessageReceivedEvent event) {
        EmbedBuilder error = new EmbedBuilder();
        error.setColor(errorColor);
        error.setTitle(errorTitle);
        error.setDescription(errorDescription);
        event.getChannel().sendMessage(error.build()).queue();
        error.clear();
    }
}
