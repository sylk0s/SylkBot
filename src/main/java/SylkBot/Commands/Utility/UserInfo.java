package SylkBot.Commands.Utility;

import SylkBot.Commands.APICommand;
import SylkBot.Commands.Permissons.PermType;
import SylkBot.SylkBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UserInfo extends APICommand {
    @Override
    public String getHelpInfo() {
        return "get info about a discord account";
    }

    @Override
    public String getTrigger() {
        return "userinfo";
    }

    @Override
    public PermType getPermLevel() {
        return null;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

        //JSONObject json = discordAPICall("/users/" + args[1]);

        SylkBot.getBot().jda.retrieveUserById(args[1]).queue(user -> {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle(user.getName() + "#" + user.getDiscriminator());
            info.setThumbnail(user.getEffectiveAvatarUrl());
            //System.out.println("https://cdn.discordapp.com/avatars/" + json.get("id").toString() + "/" + json.get("avatar").toString() + ".png");
            info.addField("User ID: ", user.getId(),false);
            info.addField("Account created on: ", user.getTimeCreated().toString(), true);
            //info.addField("Joined server on: ", )
            info.setColor(0x4e5d94);

            //sometime i want to add more to this embed

            event.getChannel().sendMessage(info.build()).queue();
        });

        //retrieveUserById(userId).queue(user -> System.out.println(user.getAvatarUrl()))
    }

    private void set(User in, User out) {

    }
}
