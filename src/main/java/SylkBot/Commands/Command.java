package SylkBot.Commands;

import SylkBot.Error.NoArgsError;
import SylkBot.Commands.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public abstract class Command extends ListenerAdapter {

    public abstract String getHelpInfo();
    public abstract String getTrigger();
    public abstract PermType getPermLevel();
    public abstract boolean hasNoArgs();

    public abstract void run(String[] args, GuildMessageReceivedEvent event);


    //todo add in perms?

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(SylkBot.instance().configs.prefix + this.getTrigger())) {
            if (args.length < 2 && !hasNoArgs()) {
                NoArgsError error = new NoArgsError();
                error.outputError(event);
            } else {
                run(args, event);
            }
        }
    }
}