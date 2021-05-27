package SylkBot;

import SylkBot.Commands.Command;
import SylkBot.Commands.Moderation.Clear;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class Main {

    public static JDA jda;
    private final static String botToken = "";
    public static String prefix = ".";

    public static HashMap<String, Command> CommandList = new HashMap<>();

    public static void main(String[] arguments) throws LoginException {
        jda = (JDA) JDABuilder.createDefault(botToken).build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

        Clear clear = new Clear();

        CommandList.put(clear.getTrigger(), clear);

        for(String key: CommandList.keySet()) {
            jda.addEventListener(CommandList.get(key));
        }
    }
}