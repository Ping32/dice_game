package pl.edu.agh.to1.dice.logic.Hands;

import pl.edu.agh.to1.dice.logic.Hand;

public class Chance extends Hand {
	public int calculate(int[] currHand) {
		int tmp = 0;
		for (int i : currHand)
			tmp += i;
		return tmp;
	}
}
