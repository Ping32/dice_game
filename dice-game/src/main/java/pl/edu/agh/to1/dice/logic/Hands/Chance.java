package pl.edu.agh.to1.dice.logic;

public class Chance extends Hand{
	private int factor;
	
	public Chance(int f){
	    factor =f;
	}

	public int calculate(int[] currHand){
	    int tmp=0;
	    for(int i :currHand)
		    tmp+=i;
	    return tmp*factor;
	}
	
} 
