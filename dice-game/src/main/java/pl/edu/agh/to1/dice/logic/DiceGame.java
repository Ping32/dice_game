package pl.edu.agh.to1.dice.logic;

import java.io.*;
import java.util.*;
import pl.edu.agh.to1.dice.logic.Hands.*;
import pl.edu.agh.to1.dice.logic.Players.HumanPlayer;

public class DiceGame {

	private int numberOfPlayers;
	private PlayerData[] players;
	Dice dice;
	public static final Map<String, Hand> hands = new HashMap<String, Hand>();
	public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static {
        hands.put("1", new Numbers(1));
        hands.put("2", new Numbers(2));
        hands.put("3", new Numbers(3));
        hands.put("4", new Numbers(4));
        hands.put("5", new Numbers(5));
        hands.put("6", new Numbers(6));

        hands.put("3ki", new SameKind(3));
        hands.put("4ki", new SameKind(4));
        hands.put("g", new SameKind(5));

        hands.put("ful", new Ful());

        hands.put("ms", new Straight(true));
        hands.put("ds", new Straight(false));

        hands.put("sz", new Chance());
        hands.put("npar", new Odd(false));
        hands.put("par", new Odd(true));
    }

	public DiceGame() {
		System.out.println("Wybierz ilosc graczy:");

		try {
			numberOfPlayers = Integer.parseInt(br.readLine());
		} catch (Exception e) { /* e.printStackTrace(); */
		}

		players = new PlayerData[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; ++i) {
			System.out.println("Wprowadz nazwe kolejnego gracza:");
			String playerName = "";
			try {
				playerName = br.readLine();
			} catch (Exception e) { /* e.printStackTrace(); */
			}
			players[i] = new PlayerData(playerName, new HumanPlayer());
		}

		dice = new Dice(6);
	}

	public void play() {
		System.out.println("Playing Dice");

		for (int i = 0; i < 13; ++i) {
			for (PlayerData player : players) {
				for (PlayerData scoreBoardScore : players) {
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

		for (PlayerData playerScore : players) {
			int score = playerScore.getScore().getScore();
			if (score > maxScore)
				maxScore = score;
		}

		System.out.println("Zwyciezcy:");

		for (PlayerData playerScore : players) {
			int score = playerScore.getScore().getScore();
			if (score == maxScore)
				System.out.println(playerScore.getName());
		}

		for (PlayerData scoreBoardScore : players)
			scoreBoardScore.getScore().printPoint();
	}
}
