package pl.edu.agh.to1.dice.logic;

public class Dice {
	public static final int diceNumber = 5;
	private Die[] dice;

	public Dice(int range) {
		dice = new Die[diceNumber];
		for (int i = 0; i < diceNumber; i++)
			dice[i] = new Die(range);
	}

	public void roll() {
		for (Die die : dice)
			die.roll();
	}

	public boolean stop(int which) {
		if (which >= 0 && which < diceNumber) {
			dice[which].setStopped(true);
			return true;
		}
		return false;
	}

	public int[] getDice() {
		int[] ret = new int[diceNumber];

		for (int i = 0; i < diceNumber; ++i)
			ret[i] = dice[i].getValue();
		return ret;
	}

	public void printDice() {
		for (int i = 0; i < diceNumber; ++i)
            System.out.println("Kosc" + (i+1) + "=" + dice[i]);
	}

    public static void printDice(int[] dice) {
        for (int i = 0; i < dice.length; ++i)
            System.out.println("Kosc" + (i+1) + "=" + dice[i]);
    }

	public void reset() {
		for (Die die : dice)
			die.setStopped(false);
	}
}