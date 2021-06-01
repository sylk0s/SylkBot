package SylkBot;

import SylkBot.Commands.Command;
import SylkBot.Commands.Core.Help;
import SylkBot.Commands.Core.Info;
import SylkBot.Commands.Fun.Hello;
import SylkBot.Commands.Fun.Say;
import SylkBot.Commands.Minecraft.History;
import SylkBot.Commands.Minecraft.Skin;
import SylkBot.Commands.Minecraft.UUID;
import SylkBot.Commands.Moderation.Clear;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class Main {

    public static JDA jda;
    private final static String botToken = ""; // REMOVE THIS WHENEVER PUSHING discord yeets it automatically so if its still here it doesnt work lol
    public static String prefix = ".";

    public static HashMap<String, Command> CommandList = new HashMap<>();

    public static void main(String[] arguments) throws LoginException {
        jda = (JDA) JDABuilder.createDefault(botToken).build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

        //todo make this cleaner

        register(new Clear());
        register(new Info());
        register(new Help());
        register(new Hello());
        register(new Say());
        register(new UUID());
        register(new History());
        register(new Skin());

        for(String key: CommandList.keySet()) {
            jda.addEventListener(CommandList.get(key));
        }
    }

    public static void register(Command command) {
        CommandList.put(command.getTrigger(), command);
    }
}