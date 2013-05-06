package pl.edu.agh.to1.dice.logic.Hands;

import pl.edu.agh.to1.dice.logic.Hand;

public class Odd extends Hand {
	private boolean isEven;

	public Odd(boolean isEven) {
		this.isEven = isEven;
	}

	public int calculate(int[] currHand) {
		int ret = 0;
		for (int i : currHand)
			if (isEven)
				ret += ((i % 2 == 0) ? i : 0);
			else
				ret += ((i % 2 == 1) ? i : 0);

		return ret;
	}

}