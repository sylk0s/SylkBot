package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TranslateTo extends Command {

    @Override
    public String getHelpInfo() {
        return "translate any text to any language";
    }

    @Override
    public String getTrigger() {
        return "translateto";
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
        String text = event.getMessage().getContentRaw().replace(".translateto " + args[1] + " ","");
        Translate translate = TranslateOptions.newBuilder().setApiKey(SylkBot.getBot().configs.googleToken).build().getService();
        Detection detection = translate.detect(text);
        String lang = detection.getLanguage();
        Translation translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(lang), Translate.TranslateOption.targetLanguage(args[1]));
        event.getChannel().sendMessage(translation.getTranslatedText()).queue();
    }

    //i dont like how this is in a different class and repeats most of the code from the other translate command
}
