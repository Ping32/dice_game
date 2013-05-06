package pl.edu.agh.to1.dice.logic;

import java.util.Random;

public class Die {
	private boolean stopped;
	private int value;
	private int range;
	private Random generator = new Random();

	public Die(int range) {
		stopped = false;
		value = 0;
		this.range = range;
	}

	public boolean getStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public int getValue() {
		return value;
	}

	public void roll() {
		if (!stopped)
			value = (generator.nextInt(range) + 1);
	}

	public void setValue(int value) {
		this.value = value;
	}
}