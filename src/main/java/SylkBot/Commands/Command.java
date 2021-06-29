package SylkBot.Commands;

import SylkBot.BotObjects.BotGuild;
import SylkBot.Error.NoArgsError;
import SylkBot.Error.PermsError;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public abstract class Command extends ListenerAdapter {

    public abstract String getHelpInfo();
    public abstract String getTrigger();
    public abstract PermType getPermLevel();
    public abstract boolean hasNoArgs();
    public abstract Category getCategory();

    public abstract void run(String[] args, GuildMessageReceivedEvent event);

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(SylkBot.getBot().configs.prefix + this.getTrigger())) {
            if(BotGuild.getBotGuild(event.getGuild().getId()).roleCheck(event.getGuild().getMember(event.getAuthor()).getRoles(),this)) {
                if (args.length < 2 && !hasNoArgs()) {
                    NoArgsError error = new NoArgsError();
                    error.outputError(event);
                } else {
                    run(args, event);
                }
            } else {
                new PermsError().outputError(event);
            }
        }
    }

    public enum Category {
        CORE,
        FRC,
        FUN,
        MATH,
        MINECRAFT,
        MODERATION,
        MUSIC,
        UTILITY
    }
}