package pl.edu.agh.to1.dice.logic;

import java.util.*;

public class Score {
	private Map<String, Integer> scoreBoard;


	public Score(int numberOfRounds/*String n*/) {
		this.scoreBoard=new HashMap<String,Integer>();
		for (int i =1; i<numberOfRounds+1;i++){
			scoreBoard.put(String.format("1x%d",i), -1);
			scoreBoard.put(String.format("2x%d",i), -1);
			scoreBoard.put(String.format("3x%d",i), -1);
			scoreBoard.put(String.format("4x%d",i), -1);
			scoreBoard.put(String.format("5x%d",i), -1);
			scoreBoard.put(String.format("6x%d",i), -1);
			
			scoreBoard.put(String.format("3kix%d",i), -1);
			scoreBoard.put(String.format("4kix%d",i), -1);
			scoreBoard.put(String.format("gx%d",i), -1);
			
			scoreBoard.put(String.format("fulx%d",i), -1);
			
			scoreBoard.put(String.format("msx%d",i), -1);
			scoreBoard.put(String.format("dsx%d",i), -1);
			
			scoreBoard.put(String.format("szx%d",i), -1);
			scoreBoard.put(String.format("parx%d",i), -1);
			scoreBoard.put(String.format("nparx%d",i), -1);
		}

	}

	// private boolean isGeneral() {
	// boolean is=true;
	// for(int i=1;i<5;i++)
	// if(currentHand[0]!=currentHand[i])
	// is=false;
	// return is;
	// }

	// public boolean isJocker(String hands) {
	// return (isGeneral() &&
	// (scoreBoard.get("g")>0) &&
	// (scoreBoard.get(Integer.toString(currentHand[0]))>0));
	// }

	public void printPoint() {
		for (String i : scoreBoard.keySet()) {
			System.out.println("Uklad " + i + " Punkty: " + scoreBoard.get(i));
		}
	}


	public int getScore() {
		int sum = 0;
		for (int i : scoreBoard.values())
			sum += i;
		return sum;
	}

	public int getScore(String hand) {
		return scoreBoard.get(hand);
	}

	public void updateScoreboard(String hand, int points) {
		// if(isJocker(hands)) {
		// scoreBoard.put("premia",100);
		// }
		scoreBoard.put(hand, points);
	}
}
