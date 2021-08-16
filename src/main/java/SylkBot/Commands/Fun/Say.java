package SylkBot.Commands.Fun;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Say extends Command {
    @Override
    public String getHelpInfo() {
        return "makes the bot say stuff (pls don't make my bot say mean things)\n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [phrase/string] `";
    }

    @Override
    public String getTrigger() {
        return "say";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
    }

    @Override
    public boolean hasNoArgs() { return false; }

    @Override
    public Category getCategory() {
        return Category.FUN;
    }

    //please dont abuse

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {
        String message = " ";
        for(int i=1; i < args.length; i++) { message = message + args[i] + " "; }
        event.getChannel().sendMessage(message).queue();
    }
}