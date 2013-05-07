package pl.edu.agh.to1.dice.logic;

public abstract class Hand{
	public int factor;
	public abstract int calculate(int[] currHand); 
}