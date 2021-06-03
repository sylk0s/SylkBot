package SylkBot;

import SylkBot.Commands.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class SylkBot extends ListenerAdapter {

    public JDA jda;
    public static SylkBot instance;
    public SylkConfigs configs;

    public static HashMap<String, Command> CommandList = new HashMap<>();

    public static void main(String[] arguments) {

        SylkBot bot = instance();
        bot.configs = SylkConfigs.setup();
        bot.create();
    }

    public void create() {
        try {
            this.jda = JDABuilder.createDefault(this.configs.token).build();
            this.jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        } catch (LoginException e) {
            System.out.println(e);
        }
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {

    }

    public static SylkBot instance() {
        if (instance == null) instance = new SylkBot();
        return instance;
    }
}