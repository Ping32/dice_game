package pl.edu.agh.to1.dice.logic;

public class Ful extends Hand{
	private int factor;
	
	public Ful(int f){
	    factor =f;
	}
	
	public int calculate(int[] currHand){
	    
		int[] tmp=new int[6];
		boolean _3=false,_2=false;
		for(int i=0;i<5;i++){
			tmp[currHand[i]-1]++;
			}
		for (int i=0;i<6;i++)
			if(tmp[i]==3)
				_3=true;
			else 
			    if (tmp[i]==2)
				_2=true;
		
		if (_3 && _2)
			return 25*factor;
		else
			return 0;
	}
	
}