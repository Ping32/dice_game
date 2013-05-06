package pl.edu.agh.to1.dice.logic.Players;

import pl.edu.agh.to1.dice.logic.Hand;
import pl.edu.agh.to1.dice.logic.Score;

public interface Player {
    public int[] doReshuffle(int[] dice, int number);
    public Hand doHandScoring(int[] dice);
    public void doScoring(Score score);
}
