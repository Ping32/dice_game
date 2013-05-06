package pl.edu.agh.to1.dice.logic.Hands;

import pl.edu.agh.to1.dice.logic.Hand;

public class Straight extends Hand {
	private boolean isSmall;

	public Straight(boolean a) {
		isSmall = a;
	}

	public int calculate(int[] currHand) {
		int[] tmp = new int[6];
		int maxSub = 0;
		boolean var = false;
		for (int i = 0; i < 5; i++) {
			++tmp[currHand[i] - 1];
		}
		if (!isSmall && tmp[1] > 0 && tmp[2] > 0 && tmp[3] > 0 && tmp[4] > 0
				&& (tmp[0] > 0 || tmp[5] > 0)) {
			return 40;
		}
		for (int i = 0; i < 5 && !var; i++) {
			if (tmp[i] > 0)
				maxSub++;

		}
		if (maxSub >= 4
				&& tmp[2] > 0
				&& tmp[3] > 0
				&& (tmp[0] > 0 && tmp[1] > 0 || tmp[1] > 0 && tmp[4] > 0 || tmp[4] > 0
						&& tmp[5] > 0))
			var = true;
		if (var)
			return 30;
		else
			return 0;
	}
}
