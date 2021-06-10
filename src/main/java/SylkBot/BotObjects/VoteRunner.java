package SylkBot.BotObjects;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class VoteRunner {

    private Vote vote;
    public VoteRunner self;

    public VoteRunner(LocalDateTime endTime, Vote vote) {

        System.out.println("in runner"); //prints

        this.vote = vote;
        this.self = this; //well... it doesnt work without this so...

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("in task");
                self.vote.endVote();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, Timestamp.valueOf(endTime));
    }
}
