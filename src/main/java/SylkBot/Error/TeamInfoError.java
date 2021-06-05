package SylkBot.Error;

public class TeamInfoError extends Error {

    public TeamInfoError() {
        this.errorTitle = "No Info";
        this.errorDescription = "The TBA Api was not able to find one or more field of info about this team :(";
    }
}
