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
        return " Will delete **n** latest messages from the channel. \n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [2 < value < 100]`";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.MOD;
    }

    @Override
    public boolean hasNoArgs() { return false; }

    @Override
    public Category getCategory() {
        return Category.MODERATION;
    }

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