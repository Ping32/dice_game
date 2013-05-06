package pl.edu.agh.to1.dice.logic;

public class Dice {

	private int amount;
	private Die[] dice;

	public Dice(int amount, int range) {
		this.amount = amount;
		dice = new Die[amount];
		for (int i = 0; i < amount; i++)
			dice[i] = new Die(range);
	}

	public void roll() {
		for (Die die : dice)
			die.roll();
	}

	public boolean stop(int which) {
		if (which >= 0 && which < amount) {
			dice[which].setStopped(true);
			return true;
		}
		return false;
	}

	public int[] getDice() {
		int[] ret = new int[amount];

		for (int i = 0; i < amount; ++i)
			ret[i] = dice[i].getValue();
		return ret;
	}

	public void printDice() {
		for (int i = 0; i < amount; ++i)
			System.out.println("Kosc" + i + "=" + dice[i].getValue());
	}

	public void reset() {
		for (Die die : dice)
			die.setStopped(false);
	}
}