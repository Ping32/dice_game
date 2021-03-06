package pl.edu.agh.to1.dice.logic.Players;

import pl.edu.agh.to1.dice.logic.Dice;
import pl.edu.agh.to1.dice.logic.DiceGame;
import pl.edu.agh.to1.dice.logic.Hand;
import pl.edu.agh.to1.dice.logic.Score;

import java.util.*;

public class HumanPlayer implements pl.edu.agh.to1.dice.logic.Players.Player {
    private Score score;

    @Override
    public int[] doReshuffle(int[] dice, int number) {
        System.out.println("Wylosowano:");
        Dice.printDice(dice);

        int numerKosci = -1;
        Set<Integer> block = new HashSet<Integer>();

        while (0 != numerKosci) {
            System.out.print("Do zablokowania kości:");
            for(Integer i : block) {
                System.out.print(" ");
                System.out.print(i+1);
            }
            System.out.println("\nWpisz numer kości aby ją zablokować lub 0 aby kontynuowac.");

            try {
                numerKosci = Integer.parseInt(DiceGame.br.readLine());

                if(numerKosci == 0) {
                    break;
                }

                if(1 <= numerKosci && numerKosci <= Dice.diceNumber) {
                    block.add(numerKosci - 1);
                } else {
                    System.out.println("Błędny numer kości!");
                }
            } catch (Exception e) { /* e.printStackTrace(); */}
        }

        int[] ret = new int[block.size()];
        int i = 0;

        for(Integer it : block) {
            ret[i++] = it;
        }

        return ret;
    }

    @Override
    public Hand doHandScoring(int[] dice) {
        Hand wybranyUklad;
        String uklad = "";
        
        System.out.println("Wylosowano:");
        Dice.printDice(dice);

        do {
            System.out.println("Ktory uklad wybierasz? (1 - jedynki, …., 6 - szóstki, 3ki - trojki, 4ki - czwórki, ful, ms - mały strit, ds - duzy strit, g - general, sz - szansa)xnumerRundy");

            try {
                uklad = DiceGame.br.readLine();
            } catch (Exception e) { /* e.printStackTrace(); */
            }

            wybranyUklad = DiceGame.hands.get(uklad);
            if (null == wybranyUklad) {
                System.out.println("Nie ma takiego ukladu!");
            } else if (-1 != this.score.getScore(uklad)) {
                System.out.println("Ten uklad jest juz zuzyty!");
            }
        } while (null == wybranyUklad || score.getScore(uklad) != -1);

        int wynik = wybranyUklad.calculate(dice);

        System.out.println("Układ za " + wynik + " punktow!");

        return wybranyUklad;
    }

    @Override
    public void doScoring(Score score) {
        this.score = score;
    }
}
