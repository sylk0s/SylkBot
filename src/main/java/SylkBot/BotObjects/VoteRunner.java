package SylkBot.BotObjects;

import java.time.LocalDateTime;

public class VoteRunner {

    public VoteRunner(LocalDateTime endTime, Vote vote) {
        while(true) {
            if(LocalDateTime.now().isAfter(endTime)) {
                vote.endVote();
                break;
            }
        }
    }
}
