package pl.edu.agh.to1.dice.logic;

public class Numbers extends Hand{
	private int number;
	
	public Numbers(int num,int f){
		factor=f;
		this.number=num;
	}
	
	public int calculate(int[] currHand){
		int tmp=0;
		for(int i=0;i<5;i++)
			if (currHand[i]==number)
				tmp+=currHand[i];
		return tmp*factor;
	}
	
}