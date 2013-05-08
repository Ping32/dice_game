package pl.edu.agh.to1.dice.logic;

import pl.edu.agh.to1.dice.logic.Players.Player;

import java.util.Map;

public class PlayerHandler {
    public final String name;
    public final Player player;
    private final Score score;

    public PlayerHandler(String name, Player player,int numberOfRounds) {
        this.name = name;
        this.player = player;
        this.score = new Score(numberOfRounds);
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
            dice.roll();
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
