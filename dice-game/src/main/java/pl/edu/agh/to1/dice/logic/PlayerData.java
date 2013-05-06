package pl.edu.agh.to1.dice.logic;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import pl.edu.agh.to1.dice.logic.Players.ComputerPlayer;
import pl.edu.agh.to1.dice.logic.Players.Player;

import java.util.Map;

public class PlayerData {
    public final String name;
    public final Player player;
    private final Score score;

    public PlayerData(String name, Player player) {
        this.name = name;
        this.player = player;
        this.score = new Score();
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public void handleRound(Dice dice) {
        dice.reset();
        dice.roll();

        this.player.doScoring(score);

        for(int i = 1; i < 3; i++) {
            int[] stop = this.player.doReshuffle(dice.getDice(), i);
            if(stop.length == 0) {
                break;
            } else {
                dice.reset();
                for(int die : stop) {
                    dice.stop(die);
                }
            }
        }

        Hand hand = this.player.doHandScoring(dice.getDice());
        String handName = null;
        for(Map.Entry<String, Hand> entry : DiceGame.hands.entrySet()) {
            if(entry.getValue().equals(hand)) {
                handName = entry.getKey();
            }
        }

        score.updateScoreboard(handName, hand.calculate(dice.getDice()));

        this.player.doScoring(score);
    }
}
