package SylkBot;

import SylkBot.BotObjects.OfflineVoteHolder;
import SylkBot.BotObjects.Vote;
import SylkBot.Commands.Command;
import SylkBot.Commands.Core.Help;
import SylkBot.Commands.Core.Info;
import SylkBot.Commands.Frc.Mallet;
import SylkBot.Commands.Frc.TeamInfo;
import SylkBot.Commands.Frc.TeamName;
import SylkBot.Commands.Fun.Hello;
import SylkBot.Commands.Fun.Say;
import SylkBot.Commands.Minecraft.PlayerInfo;
import SylkBot.Commands.Minecraft.Skin;
import SylkBot.Commands.Minecraft.UUID;
import SylkBot.Commands.Moderation.Clear;
import SylkBot.Commands.Moderation.DebugGetVote;
import SylkBot.Commands.Moderation.VoteList;
import SylkBot.Commands.Moderation.VoteTrigger;
import SylkBot.Commands.Utility.*;
import SylkBot.Configs.SylkConfigs;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SylkBot extends ListenerAdapter {

    public JDA jda;
    public static SylkBot instance;
    public SylkConfigs configs;
    public OfflineVoteHolder voteHolder;

    public List<Guild> servers; //fix this
    public ArrayList<Command> commands;
    public HashMap<String, Vote> votes;

    public static void main(String[] arguments) {

        SylkBot bot = getBot();
        bot.configs = (SylkConfigs) SylkConfigs.setup(SylkConfigs.class);
        bot.create();
        bot.registerCommands();
        bot.voteHolder = new OfflineVoteHolder();
        bot.votes = new HashMap<>();
    }

    public void create() {
        try {
            this.jda = JDABuilder.createDefault(this.configs.botToken).build();
            this.jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        } catch (LoginException e) {
            System.out.println(e);
        }
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        this.servers = new ArrayList<>();
        this.servers = getBot().jda.getGuilds();
        System.out.println("IS ready"); //this doesnt work WHY DOESNT THIS WORK! maybe just make it unsafe?
    }

    public static SylkBot getBot() {
        if (instance == null) instance = new SylkBot();
        return instance;
    }

    private void registerCommands() {
        this.commands = new ArrayList<>();
        register(new Help());
        register(new Info());

        register(new Mallet());
        register(new TeamName());
        register(new TeamInfo());

        register(new Hello());
        register(new Say());

        register(new PlayerInfo());
        register(new Skin());
        register(new UUID());

        register(new Clear());
        register(new VoteList());
        register(new VoteTrigger());

        register(new ChannelLinkTrigger());
        register(new CTranslate());
        register(new Hex());
        register(new TranslateTo());
        register(new UserInfo());

        register(new DebugGetVote());
    }

    private void register(Command command) {
        this.commands.add(command);
        this.jda.addEventListener(command);
    }

    public void updateVotes() {
        //votes.forEach(v -> new v);
    }
}