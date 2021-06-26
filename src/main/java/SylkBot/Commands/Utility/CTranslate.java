package SylkBot.Commands.Utility;

import SylkBot.Commands.APICommand;
import SylkBot.Permissons.PermType;
import SylkBot.SylkBot;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CTranslate extends APICommand {

    @Override
    public String getHelpInfo() {
        return "translate any text to english";
    }

    @Override
    public String getTrigger() {
        return "translate";
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
        String text = event.getMessage().getContentRaw().replace(".translate ","");
        Translate translate = TranslateOptions.newBuilder().setApiKey(SylkBot.getBot().configs.googleToken).build().getService();
        Detection detection = translate.detect(text);
        String lang = detection.getLanguage();
        Translation translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(lang), Translate.TranslateOption.targetLanguage("en"));
        event.getChannel().sendMessage(translation.getTranslatedText()).queue();
    }

    //figure out how to integrate this
    //https://cloud.google.com/translate/docs/languages
}
