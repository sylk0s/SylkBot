package SylkBot.Commands.Utility;

import SylkBot.Commands.Command;
import SylkBot.Permissons.PermType;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TranslateTo extends Command {

    @Override
    public String getHelpInfo() {
        return "Translate any text to any language\n"+"Takes language code from here -> https://developers.google.com/admin-sdk/directory/v1/languages \n" +
                " `" + this.bot.configs.prefix + this.getTrigger() + " [language code] [text to translate]`";
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
        Translate translate = TranslateOptions.newBuilder().setApiKey(this.bot.configs.googleToken).build().getService();
        Detection detection = translate.detect(text);
        String lang = detection.getLanguage();
        Translation translation = translate.translate(text, Translate.TranslateOption.sourceLanguage(lang), Translate.TranslateOption.targetLanguage(args[1]));
        event.getChannel().sendMessage(translation.getTranslatedText()).queue();
    }
    //i dont like how this is in a different class and repeats most of the code from the other translate command
}
