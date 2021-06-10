package SylkBot.Commands.Moderation;

import SylkBot.Commands.Command;
import SylkBot.Error.ClearError;
import SylkBot.Error.NoArgsError;

import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Clear extends Command {

    @Override
    public String getTrigger() {
        return "clear";
    }

    @Override
    public String getHelpInfo() {
        return " [value < 100] Will delete up to 100 messages from a channel\n";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.MOD;
    }

    @Override
    public boolean hasNoArgs() { return false; }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        if (args.length < 2) {
           NoArgsError error = new NoArgsError();
           error.outputError(event);
        } else {
            try {
                List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                event.getChannel().deleteMessages(messages).queue();
            } catch (IllegalArgumentException e) {
                ClearError error = new ClearError(e);
                error.outputError(event);
            }
        }
    }
}