package SylkBot;

import SylkBot.BotObjects.BotGuild;
import SylkBot.BotObjects.JoinLeave;
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
import SylkBot.Commands.Moderation.GuildSetup;
import SylkBot.Commands.Moderation.VoteList;
import SylkBot.Commands.Moderation.VoteTrigger;
import SylkBot.Commands.Utility.*;
import SylkBot.Configs.SylkConfigs;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.HashMap;

public class SylkBot extends ListenerAdapter {

    public JDA jda;
    public static SylkBot instance;
    public SylkConfigs configs;
    public OfflineVoteHolder voteHolder;

    public ArrayList<BotGuild> guilds;
    public ArrayList<Command> commands;
    public HashMap<String, Vote> votes;
    public static ArrayList<String> catagory = new ArrayList<>();

    //test push

    public static void main(String[] arguments) {

        SylkBot bot = getBot();
        bot.configs = (SylkConfigs) SylkConfigs.setup(new SylkConfigs());
        bot.create();
        bot.registerCommands();
        bot.voteHolder = new OfflineVoteHolder();
        bot.votes = new HashMap<>();
    }

    public void create() {
        try {
            this.jda = JDABuilder.createDefault(this.configs.botToken).build();
            jda.addEventListener(this);
            jda.addEventListener(new JoinLeave());
            this.jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        this.guilds = new ArrayList<>();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for(Guild guild : getBot().jda.getGuilds()) {
            BotGuild g = new BotGuild();
            g.guildID = guild.getId();
            guilds.add((BotGuild) BotGuild.setup(g));

            System.out.println(guild.getName() + ": " + guild.getId());
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }

    public static SylkBot getBot() {
        if (instance == null) instance = new SylkBot();
        return instance;
    }

    private void registerCommands() {
        this.commands = new ArrayList<>();

        //core
        register(new Help());
        register(new Info());

        //FRC
        register(new Mallet());
        register(new TeamName());
        register(new TeamInfo());

        //Fun
        register(new Hello());
        register(new Say());

        //Minecraft
        register(new PlayerInfo());
        register(new Skin());
        register(new UUID());

        //Moderation
        register(new Clear());
        register(new GuildSetup());
        register(new VoteList());
        register(new VoteTrigger());

        //Utility
        register(new BotGuildConfigsTest());
        register(new ChannelLinkTrigger());
        register(new CTranslate());
        register(new Hex());
        register(new TranslateTo());
        register(new UserInfo());
        register(new Weather());
    }

    private void register(Command command) {
        this.commands.add(command);
        this.jda.addEventListener(command);
    }
}