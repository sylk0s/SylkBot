package SylkBot.BotObjects;

import SylkBot.SylkBot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ChannelLink extends ListenerAdapter {

    //there is DEFINITLY some extranious code in here that's a result of me trying to get this working. I'm just gonna leave ti for now because its not really hurting anything.
    //oh also! this can definitly be abused over multiple server (ie uses the bot to spam or access a secret channel)... i should fix this but probably wont

    TextChannel c1;
    TextChannel c2;

    public ChannelLink(TextChannel c1, TextChannel c2) {
        this.c1 = c1;
        this.c2 = c2;

        c1.sendMessage("link established 1").queue();
        c2.sendMessage("link established 2").queue();
    }

    public static void createLink(String l1, String l2) {
        TextChannel c1 = SylkBot.getBot().jda.getTextChannelById(l1);
        TextChannel c2 = SylkBot.getBot().jda.getTextChannelById(l2);

        SylkBot.getBot().jda.addEventListener(new ChannelLink(c1,c2));
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && (event.getChannel().equals(c1)||event.getChannel().equals(c2))) { //do i need this lol
            TextChannel toSend = null;
            if (event.getChannel().equals(c1)) toSend = this.c2;
            if (event.getChannel().equals(c2)) toSend = this.c1;

            if (!(toSend == null))
                toSend.sendMessage(event.getAuthor().getName() + ": " + event.getMessage().getContentRaw()).queue();
        }
    }
}
