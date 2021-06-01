package SylkBot.Error;

import java.util.Objects;

public class ClearError extends Error {

    public ClearError(IllegalArgumentException e) {
        switch(Objects.requireNonNull(getType(e))) {
            case TOO_MANY_MESSAGES:
                this.errorTitle = "Too Many Messages Selected";
                this.errorDescription = "Message retrieval limit is between 1 and 100 messages. No more, no less.";
                break;
            case TOO_FEW_MESSAGES:
                this.errorTitle = "Can't delete a singular message";
                this.errorDescription = "TBH I don't know why this is a thing but I'm too lazy to fix it right now.";
                break;
            case OLD_MESSAGE:
                this.errorTitle = "Message Is Too Old";
                this.errorDescription = "Messages from more than 2 weeks ago cannot be removed.";
                break;
        }
    }

    private ErrorType getType(IllegalArgumentException e) {
        if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval limit is between 1 and 100 messages. No more, no less.")) return ErrorType.TOO_MANY_MESSAGES;
        if (e.toString().startsWith("java.lang.IllegalArgumentException: Must provide at least 2 or at most 100 messages to be deleted.")) return ErrorType.TOO_FEW_MESSAGES;
        if (e.toString().startsWith("java.lang.IllegalArgumentException: Message Id provided was older than 2 weeks.")) return ErrorType.OLD_MESSAGE;
        return null;
    }
}
