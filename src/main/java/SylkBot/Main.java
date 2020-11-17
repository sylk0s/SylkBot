package SylkBot;

import SylkBot.CAlpha.Ask;
import SylkBot.CFun.Hello;

import SylkBot.CFun.Ping;
import SylkBot.CMain.Clear;
import SylkBot.CMain.Help;
import SylkBot.CMain.Info;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class Main {

    public static JDA jda;
    private final static String botToken = "TOKEN";
    public static String prefix = ".";

    public static HashMap<String, Commands> CommandList = new HashMap<String, Commands>();

    public static void main(String[] arguments) throws LoginException {
        jda = (JDA) JDABuilder.createDefault(botToken).build();
        jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

        CommandList.put(Hello.trigger, new Hello());
        CommandList.put(Ping.trigger, new Ping());
        CommandList.put(Clear.trigger, new Clear());
        CommandList.put(Help.trigger, new Help());
        CommandList.put(Info.trigger, new Info());
        CommandList.put(Ask.trigger, new Ask());

        for(String key: CommandList.keySet()) {
            jda.addEventListener(CommandList.get(key));
        }
    }
}