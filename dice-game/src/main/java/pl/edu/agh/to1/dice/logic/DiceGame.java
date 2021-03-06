package pl.edu.agh.to1.dice.logic;

import java.io.*;
import java.util.*;

import pl.edu.agh.to1.dice.logic.Hands.*;
import pl.edu.agh.to1.dice.logic.Players.HumanPlayer;

public class DiceGame {

	private int numberOfPlayers;
	private int numberOfRounds;
	private PlayerHandler[] playerHandlers;
	Dice dice;
	public static Map<String, Hand> hands = new HashMap<String, Hand>();
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Map<String,Integer> ranking = new HashMap<String,Integer>();
	private final File plikZRankingiem;
  
	
	public DiceGame() {
		      
		plikZRankingiem = new File("ranking.dat");
		
		if(plikZRankingiem.exists()){
		    ObjectInputStream ois = null;
		    
		    try{
		    
		    ois=new ObjectInputStream(new FileInputStream(plikZRankingiem));
		    
		    ranking = (Map<String,Integer>)ois.readObject();
		    ois.close();
		    
		    }catch(Exception e){e.printStackTrace();}
		}
		
		
		
		System.out.println("Ilosc rund:");
		numberOfRounds=1;
		try{
		    numberOfRounds = Integer.parseInt(br.readLine());
		}catch(Exception e){ /*e.printStackTrace();*/ }
		for (int i=1;i<numberOfRounds+1;i++){
			hands.put(String.format("1x%d",i), new Numbers(1,i));
			hands.put(String.format("2x%d",i), new Numbers(2,i));
			hands.put(String.format("3x%d",i), new Numbers(3,i));
			hands.put(String.format("4x%d",i), new Numbers(4,i));
			hands.put(String.format("5x%d",i), new Numbers(5,i));
			hands.put(String.format("6x%d",i), new Numbers(6,i));
			
			hands.put(String.format("3kix%d",i), new SameKind(3,i));
			hands.put(String.format("4kix%d",i), new SameKind(4,i));
			hands.put(String.format("gx%d",i), new SameKind(5,i));
			
			hands.put(String.format("fulx%d",i), new Ful(i));
			
			hands.put(String.format("msx%d",i), new Straight(true,i));
			hands.put(String.format("dsx%d",i), new Straight(false,i));
			
			hands.put(String.format("szx%d",i), new Chance(i));
			hands.put(String.format("nparx%d",i), new Odd(false,i));
			hands.put(String.format("parx%d",i),new Odd(true,i));
		}
		
		
		System.out.println("Wybierz ilosc graczy:");

		try {
			numberOfPlayers = Integer.parseInt(br.readLine());
		} catch (Exception e) { /* e.printStackTrace(); */
		}

		playerHandlers = new PlayerHandler[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; ++i) {
			System.out.println("Wprowadz nazwe kolejnego gracza:");
			String playerName = "";
			try {
				playerName = br.readLine();
			} catch (Exception e) { /* e.printStackTrace(); */
			}
			playerHandlers[i] = new PlayerHandler(playerName, new HumanPlayer(),numberOfRounds);
			
			if(null == ranking.get(playerName))
				ranking.put(playerName, 0);
		}

		dice = new Dice(6);
	}

	public void play() {
		System.out.println("Playing Dice");
		System.out.println("Ranking:");
		for(Map.Entry<String,Integer> entry : ranking.entrySet())
			System.out.println(entry.getKey() + ": " + entry.getValue());

		for (int i = 0; i < 15*numberOfRounds; ++i) {
			for (PlayerHandler player : playerHandlers) {
				for (PlayerHandler scoreBoardScore : playerHandlers) {
					System.out.println("Gracz: " + scoreBoardScore.getName());
					scoreBoardScore.getScore().printPoint();
				}

				System.out.println("Gracz '" + player.getName() + "'");

				player.handleRound(dice);
			}
		}

		end();
	}

	public void end() {
		System.out.println("Koniec gry!");

		int maxScore = -1;

		for (PlayerHandler playerScore : playerHandlers) {
			int score = playerScore.getScore().getScore();
			if (score > maxScore)
				maxScore = score;
		}

		System.out.println("Zwyciezcy:");

		for (PlayerHandler playerScore : playerHandlers) {
			int score = playerScore.getScore().getScore();
			if (score == maxScore){
				System.out.println(playerScore.getName());
				ranking.put(playerScore.getName(), ranking.get(playerScore.getName())+1);
			}
		}

		for (PlayerHandler scoreBoardScore : playerHandlers)
			scoreBoardScore.getScore().printPoint();
		        
		ObjectOutputStream oos=null;
	    
		try{
			oos=new ObjectOutputStream(new FileOutputStream(plikZRankingiem));
			
			oos.writeObject(ranking);
			oos.close();
		}catch(Exception e){e.printStackTrace();}
	}

}
