package pl.edu.agh.to1.dice.logic;

public class SameKind extends Hand{
	private int amount;
	private int factor;
	
	public SameKind(int a,int f){
		factor =f;
		amount=a;
	}
	
	public int calculate(int[] currHand){
		int[] tmp=new int[6];
		int returnVal=0;
		boolean var=false,gen=false;
		for(int i=0;i<5;i++){
			if(!var && ++tmp[currHand[i]-1]==amount)
				var=true;
			returnVal+=currHand[i];
		}
		if (var)
		    if (amount==5)
			return 50*factor;
		    else
			return returnVal*factor;
		else
			return 0;
	}
	
}