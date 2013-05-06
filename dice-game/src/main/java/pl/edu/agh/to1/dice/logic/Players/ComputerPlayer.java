package pl.edu.agh.to1.dice.logic.Players;

import pl.edu.agh.to1.dice.logic.DiceGame;
import pl.edu.agh.to1.dice.logic.Hand;
import pl.edu.agh.to1.dice.logic.Score;

import java.util.Map;

public class ComputerPlayer implements pl.edu.agh.to1.dice.logic.Players.Player {
    private Score score;

    @Override
    public int[] doReshuffle(int[] dice, int number) {
        return new int[0];
    }

    @Override
    public Hand doHandScoring(int[] dice) {
        Hand selectedHand = null;
        int selectedHandScore = -1;

        for(Map.Entry<String, Hand> hand : DiceGame.hands.entrySet()) {
            if(score.getScore(hand.getKey()) != -1) continue;

            if(hand.getValue().calculate(dice) > selectedHandScore) {
                selectedHand = hand.getValue();
                selectedHandScore = selectedHand.calculate(dice);
            }
        }

        return selectedHand;
    }

    @Override
    public void doScoring(Score score) {
        this.score = score;
    }
}
