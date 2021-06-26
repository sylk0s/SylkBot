package SylkBot.Commands.Frc;

import SylkBot.Commands.APICommand;
import SylkBot.Permissons.PermType;
import SylkBot.Error.TeamInfoError;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;

public class TeamInfo extends APICommand {

    @Override
    public String getHelpInfo() {
        return "gives various bits of info on FRC teams";
    }

    @Override
    public String getTrigger() {
        return "teaminfo";
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
        return Category.FRC;
    }

    @Override
    public void run(String[] args, GuildMessageReceivedEvent event) {

        String call1 = "/team/frc" + args[1] + "/media/2019";
        String call2 = "/team/frc" + args[1];
        try {

            EmbedBuilder info = new EmbedBuilder();
            JSONObject teamInfo = tbaApiCall(call2).getJSONObject(0);

            try {

                info.setTitle("FRC Team " + args[1]);
                info.setDescription(teamInfo.get("nickname").toString());
                info.addField("Rookie Year: ", teamInfo.get("rookie_year").toString(), true);
                info.addField("Website: ", teamInfo.get("website").toString(), true);
                info.addField("Location: ", teamInfo.get("city").toString() + ", " + teamInfo.get("state_prov").toString() + ", " + teamInfo.get("country").toString(), false);
                info.setColor(0x0000FF);

                try {
                    byte[] bytes = Base64.decodeBase64(tbaApiCall(call1).getJSONObject(0).getJSONObject("details").get("base64Image").toString());

                    ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
                    BufferedImage image = ImageIO.read(stream);
                    stream.close();

                    File out = new File("image.png");
                    ImageIO.write(image, "png", out);

                    info.setThumbnail("attachment://image.png");

                    event.getChannel().sendMessage(info.build()).addFile(out, "image.png").queue();
                } catch (JSONException e) {
                    System.out.println("json not found/read properly");
                    event.getChannel().sendMessage(info.build()).queue();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("failed");
            }
        } catch (JSONException e) {
            TeamInfoError error = new TeamInfoError();
            error.outputError(event);
        }
    }
}
