package pl.edu.agh.to1.dice.logic;

import java.util.*;

public class Score {
	private String name;
	private Map<String, Integer> scoreBoard;

	// private int[] currentHand;

	public Score() {
		this.scoreBoard = new HashMap<String, Integer>();
		scoreBoard.put("1", -1);
		scoreBoard.put("2", -1);
		scoreBoard.put("3", -1);
		scoreBoard.put("4", -1);
		scoreBoard.put("5", -1);
		scoreBoard.put("6", -1);

		scoreBoard.put("3ki", -1);
		scoreBoard.put("4ki", -1);
		scoreBoard.put("g", -1);

		scoreBoard.put("ful", -1);

		scoreBoard.put("ms", -1);
		scoreBoard.put("ds", -1);

		scoreBoard.put("sz", -1);
		scoreBoard.put("par", -1);
		scoreBoard.put("npar", -1);
		// scoreBoard.put("premia", 0);
		// this.currentHand= new int[5];
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

	public String getName() {
		return name;
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