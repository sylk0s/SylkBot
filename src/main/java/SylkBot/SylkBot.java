package SylkBot;

import SylkBot.BotObjects.BotGuild;
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
            this.jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        this.guilds = new ArrayList<>();
        for(Guild guild : getBot().jda.getGuilds()) {
            BotGuild g = new BotGuild();
            g.guildID = guild.getId();
            guilds.add((BotGuild) BotGuild.setup(g));

            System.out.println(guild.getName() + ": " + guild.getId());
        }
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        for(BotGuild guild : guilds) {
            if(guild.guildID.equals(event.getGuild().getId())) {
                TextChannel channel = SylkBot.getBot().jda.getTextChannelById(guild.joinLeaveChannelID);
                if(channel != null) {
                    channel.sendMessage(event.getMember().getAsMention() + " joined the server").queue();
                }
            }
        }
    }

    @Override
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        for(BotGuild guild : guilds) {
            if(guild.guildID.equals(event.getGuild().getId())) {
                TextChannel channel = SylkBot.getBot().jda.getTextChannelById(guild.joinLeaveChannelID);
                if(channel != null) {
                    channel.sendMessage(event.getMember().getAsMention() + " left the server").queue();
                }
            }
        }
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
    }

    private void register(Command command) {
        this.commands.add(command);
        this.jda.addEventListener(command);
    }
}