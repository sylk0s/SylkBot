package SylkBot.CAlpha;

import SylkBot.Commands;
import SylkBot.Main;
import com.wolfram.alpha.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Ask extends Commands {
    public static String trigger = "ask";

    @Override
    public String getHelpInfo() {
        return Main.prefix + trigger + " Tells you stuff \n";
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args[0].equalsIgnoreCase(Main.prefix + trigger)) {

            String input = " ";

            for (int i = 1; i < args.length; i++) {
                input += (" " + args[i]);
            }

            WAEngine engine = new WAEngine();

            engine.setAppID("XR4E53-256VWEKQXT");
            engine.addFormat("plaintext");

            WAQuery query = engine.createQuery();
            query.setInput(input);

            try {
                WAQueryResult queryResult = engine.performQuery(query);

                if (queryResult.isError()) {
                    //System.out.println("Query error");
                    //System.out.println("  error code: " + queryResult.getErrorCode());
                    //System.out.println("  error message: " + queryResult.getErrorMessage());
                } else if (!queryResult.isSuccess()) {
                    //System.out.println("Query was not understood; no results available.");
                } else {
                    // Got a result.
                    //System.out.println("Successful query. Pods follow:\n");

                    EmbedBuilder output = new EmbedBuilder();
                    output.setColor(0xff97cb);

                    for (WAPod pod : queryResult.getPods()) {
                        if (!pod.isError()) {
                            output.setTitle(pod.getTitle());
                            //System.out.println(pod.getTitle());
                            //System.out.println("------------");

                            String podInfo = " ";

                            for (WASubpod subpod : pod.getSubpods()) {
                                for (Object element : subpod.getContents()) {
                                    if (element instanceof WAPlainText) {
                                        podInfo += (((WAPlainText) element).getText());
                                        //System.out.println(((WAPlainText) element).getText());
                                    }
                                }
                            }
                            if (podInfo != " ") {
                                output.setDescription(podInfo);
                                event.getChannel().sendMessage(output.build()).queue();
                            }
                        }
                    }
                }
            } catch (WAException e) {
                e.printStackTrace();
            }
        }
    }
}