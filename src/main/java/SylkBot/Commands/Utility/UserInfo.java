package SylkBot.Commands.Utility;

import SylkBot.Commands.APICommand;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class UserInfo extends APICommand {
    @Override
    public String getHelpInfo() {
        return "Get info about a discord user\n"+
                " `" + this.bot.configs.prefix + this.getTrigger() + " [user id]`";
    }

    @Override
    public String getTrigger() {
        return "userinfo";
    }

    @Override
    public PermType getPermLevel() {
        return PermType.EVERYONE;
    }

    @Override
    public boolean hasNoArgs() {
        return false;
    }

    @Override
    public Category getCategory() {
        return Category.UTILITY;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

        this.bot.jda.retrieveUserById(args[1]).queue(user -> {
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle(user.getName() + "#" + user.getDiscriminator());
            info.setThumbnail(user.getEffectiveAvatarUrl());
            //System.out.println("https://cdn.discordapp.com/avatars/" + json.get("id").toString() + "/" + json.get("avatar").toString() + ".png");

            info.addField("User ID: ", user.getId(),false);
            info.addField("Account created on: ", user.getTimeCreated().toString(), true);
            info.addField("Joined server on: ", this.bot.jda.getGuildById(event.getGuild().getId()).getMemberById(args[1]).getTimeJoined().toString(),true);
            if (this.bot.jda.getGuildById(event.getGuild().getId()).getMemberById(args[1]).getNickname() != null) {
                info.addField("Nickname: ", this.bot.jda.getGuildById(event.getGuild().getId()).getMemberById(args[1]).getNickname(), false);
            }
            info.setColor(0x4e5d94);

            //sometime i want to add more to this embed
            event.getChannel().sendMessage(info.build()).queue();
        });

        //retrieveUserById(userId).queue(user -> System.out.println(user.getAvatarUrl()))
    }

    private void set(User in, User out) {

    }
}
