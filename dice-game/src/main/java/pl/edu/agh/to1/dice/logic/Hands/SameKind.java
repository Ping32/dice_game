package pl.edu.agh.to1.dice.logic.Hands;

import pl.edu.agh.to1.dice.logic.Hand;

public class SameKind extends Hand {
	private int amount;

	public SameKind(int a) {
		amount = a;
	}

	public int calculate(int[] currHand) {
		int[] tmp = new int[6];
		int returnVal = 0;
		boolean var = false;
		for (int i = 0; i < 5; i++) {
			if (!var && ++tmp[currHand[i] - 1] == amount)
				var = true;
			returnVal += currHand[i];
		}
		if (var)
			if (amount == 5)
				return 50;
			else
				return returnVal;
		else
			return 0;
	}

}