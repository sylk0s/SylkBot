package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hex extends Command {
    @Override
    public String getHelpInfo() {
        return null;
    }

    @Override
    public String getTrigger() {
        return "hex";
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
        // Create the image
        BufferedImage bi = new BufferedImage(60, 60, ColorSpace.TYPE_RGB
        );
        Graphics2D graphics = bi.createGraphics();

// Fill the background with gray color
        Color hex = Color.decode("#" + args[1]);
        graphics.setColor (hex);
        graphics.fillRect ( 0, 0, bi.getWidth(), bi.getHeight());

// Save the file in PNG format
        try {
            File outFile = new File("output.png");
            ImageIO.write(bi, "png", outFile);
            event.getChannel().sendMessage("0x"+args[1]).addFile(outFile).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
