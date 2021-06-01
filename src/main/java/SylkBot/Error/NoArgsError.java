package SylkBot.Error;

public class NoArgsError extends Error {

    public NoArgsError() {
        this.errorTitle = "No Arguments";
        this.errorDescription = "Please Input a Value after the command.";
    }
}
