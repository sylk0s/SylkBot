package SylkBot.CMain;

import SylkBot.Commands;
import SylkBot.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Clear extends Commands {

    public static String trigger = "clear";
    @Override
    public String getHelpInfo() {return Main.prefix + trigger + " [value < 100] Will delete up to 100 messages from a channel\n";}

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {
                if (args.length < 2) {
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(0xff3923);
                    success.setTitle("Please Input a Value");
                    success.setDescription("Usage: " + Main.prefix + trigger + " [insert value here]");
                    event.getChannel().sendMessage(success.build()).queue();
                } else {
                    try {
                        List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                        event.getChannel().deleteMessages(messages).queue();
                    }
                    catch (IllegalArgumentException e) {
                        if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval limit is between 1 and 100 messages. No more, no less.")) {
                            EmbedBuilder error = new EmbedBuilder();
                            error.setColor(0xff3923);
                            error.setTitle("Too Many Messages Selected");
                            error.setDescription("Message retrieval limit is between 1 and 100 messages. No more, no less.");
                            event.getChannel().sendMessage(error.build()).queue();
                        } else {
                            if (e.toString().startsWith("java.lang.IllegalArgumentException: Must provide at least 2 or at most 100 messages to be deleted.")) {
                                EmbedBuilder error = new EmbedBuilder();
                                error.setColor(0xff3923);
                                error.setTitle("Can't delete a singular message");
                                error.setDescription("TBH I don't know why this is a thing but I'm too lazy to fix it right now.");
                                event.getChannel().sendMessage(error.build()).queue();
                            } else {
                                    EmbedBuilder error = new EmbedBuilder();
                                    error.setColor(0xff3923);
                                    error.setTitle("Message Is Too Old");
                                    error.setDescription("Messages from more than 2 weeks ago cannot be removed.");
                                    event.getChannel().sendMessage(error.build()).queue();
                            }
                        }
                    }
                }
            }
        }
    }