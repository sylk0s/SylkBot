package SylkBot.Commands.Frc;

import SylkBot.Commands.APICommand;
import SylkBot.Commands.Permissons.PermType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import javax.imageio.ImageIO;

public class TeamInfo extends APICommand {

    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "teaminfo";
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

        //make this not break if I put in an incorrect value

        String call1 = "/team/frc" + args[1] + "/media/" + args[2];
        String call2 = "/team/frc" + args[1];
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(Base64.decodeBase64(tbaApiCall(call1).getJSONObject(0).getJSONObject("details").get("base64Image").toString()));
            BufferedImage image = ImageIO.read(stream);
            stream.close();

            File out = new File("image.png");
            ImageIO.write(image, "png", out);

            JSONObject teamInfo = tbaApiCall(call2).getJSONObject(0);

            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("FRC Team " + args[1]);
            info.setDescription(teamInfo.get("nickname").toString());
            info.addField("Rookie Year: ", teamInfo.get("rookie_year").toString() ,true);
            info.addField("Website: ", teamInfo.get("website").toString(), true);
            info.addField("Location: ", teamInfo.get("city").toString() + ", " + teamInfo.get("state_prov").toString() + ", " + teamInfo.get("country").toString(), false);
            info.setThumbnail("attachment://image.png");
            info.setColor(0x0000FF);

            event.getChannel().sendMessage(info.build()).addFile(out, "image.png").queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
