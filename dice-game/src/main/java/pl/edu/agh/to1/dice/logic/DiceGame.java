package pl.edu.agh.to1.dice.logic;

import java.io.*;
import java.util.*;
import pl.edu.agh.to1.dice.logic.Hands.*;

public class DiceGame {

	private int numberOfPlayers;
	private Player[] players;
	Dice dice;
	Map<String, Hand> hand = new HashMap<String, Hand>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public DiceGame() {

		System.out.println("Wybierz ilosc graczy:");
		try {
			numberOfPlayers = Integer.parseInt(br.readLine());
		} catch (Exception e) { /* e.printStackTrace(); */
		}

		players = new Player[numberOfPlayers];

		for (int i = 0; i < numberOfPlayers; ++i) {
			System.out.println("Wprowadz nazwe kolejnego gracza:");
			String playerName = "";
			try {
				playerName = br.readLine();
			} catch (Exception e) { /* e.printStackTrace(); */
			}
			players[i] = new Player(playerName);
		}

		hand.put("1", new Numbers(1));
		hand.put("2", new Numbers(2));
		hand.put("3", new Numbers(3));
		hand.put("4", new Numbers(4));
		hand.put("5", new Numbers(5));
		hand.put("6", new Numbers(6));

		hand.put("3ki", new SameKind(3));
		hand.put("4ki", new SameKind(4));
		hand.put("g", new SameKind(5));

		hand.put("ful", new Ful());

		hand.put("ms", new Straight(true));
		hand.put("ds", new Straight(false));

		hand.put("sz", new Chance());
		hand.put("npar", new Odd(false));
		hand.put("par", new Odd(true));

		dice = new Dice(5, 6);
	}

	public void play() {
		System.out.println("Playing Dice");

		for (int i = 0; i < 13; ++i) {

			for (Player player : players) {
				for (Player scoreBoardPlayer : players) {
					System.out.println("Gracz: " + scoreBoardPlayer.getName());
					scoreBoardPlayer.printPoint();
				}
				System.out.println("Gracz '" + player.getName() + "'");

				dice.roll();

				for (int j = 0; j < 2; ++j) {
					System.out.println("Nowy rzut:");
					dice.printDice();
					dice.reset();
					int numerKosci = 0;
					while (-1 != numerKosci) {
						System.out
								.println("Wpisz numer kości aby ją zablokować lub -1 aby kontynuowac.");

						try {
							numerKosci = Integer.parseInt(br.readLine());
						} catch (Exception e) { /* e.printStackTrace(); */
						}

						if (-1 != numerKosci) {
							if (dice.stop(numerKosci))
								System.out.println("OK");
							else
								System.out
										.println("Nie udalo sie. Czy na pewno nie wpisales za duzego lub za malego numeru?");
						}
						dice.printDice();
					}
					dice.roll();
				}
				dice.reset();
				System.out.println("Ostateczny wynik:");
				dice.printDice();
				Hand wybranyUklad;
				String uklad = "";

				do {
					System.out
							.println("Ktory uklad wybierasz? (1 - jedynki, …., 6 - szóstki, 3ki - trojki, 4ki - czwórki, ful, ms - mały strit, ds - duzy strit, g - general, sz - szansa)");

					try {
						uklad = br.readLine();
					} catch (Exception e) { /* e.printStackTrace(); */
					}

					wybranyUklad = hand.get(uklad);
					if (null == wybranyUklad) {
						System.out.println("Nie ma takiego ukladu!");
					} else if (-1 != player.getScore(uklad)) {
						System.out.println("Ten uklad jest juz zuzyty!");
					}
				} while (null == wybranyUklad || player.getScore(uklad) != -1);

				int wynik = wybranyUklad.calculate(dice.getDice());

				System.out.println("Gracz " + player.getName() + " otrzymuje "
						+ wynik + " punktow!");

				player.updateScoreboard(uklad, wynik);

			}

		}

		end();
	}

	public void end() {

		System.out.println("Koniec gry!");

		int maxScore = -1;

		for (Player player : players) {
			int score = player.getScore();
			if (score > maxScore)
				maxScore = score;
		}

		System.out.println("Zwyciezcy:");

		for (Player player : players) {
			int score = player.getScore();
			if (score == maxScore)
				System.out.println(player.getName());
		}

		for (Player scoreBoardPlayer : players)
			scoreBoardPlayer.printPoint();
	}
}
