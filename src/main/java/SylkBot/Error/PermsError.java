package SylkBot.Error;

public class PermsError extends Error {

    public PermsError() {
        this.errorTitle = "You don't have permissions to use this command!";
        this.errorDescription = "Ask for the server owner to check server configs you think this is wrong.";
    }

}
