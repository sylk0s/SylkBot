package SylkBot.CMain;

        import SylkBot.Commands;
        import SylkBot.Main;

        import net.dv8tion.jda.api.EmbedBuilder;
        import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info extends Commands {

    public static String trigger = "info";
    @Override
    public String getHelpInfo() {return Main.prefix + trigger + " Use to get info about the bot\n";}

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("SylkBot Info");
            info.setDescription("Info about SylkBot! \n" +
                    "Use .help to learn about the commands this bot can do. \n" +
                    "TBD"); //TODO Write this info command
            info.setColor(0xff97cb);
            info.setFooter("Created by Sylkos", event.getMember().getUser().getAvatarUrl());

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }
}