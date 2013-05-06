package pl.edu.agh.to1.dice.logic;
import  java.io.*;
import java.util.*;

public class DiceGame {

  private int numberOfPlayers;
  private Player[] players;
  Dice dice;
  Map<String,Hand> hand = new HashMap<String,Hand>();
  
  Map<String,Integer> ranking = new HashMap<String,Integer>();
  private final File plikZRankingiem;
  
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
    public DiceGame(){
      
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
      int numberOfRounds=1;
      try{
        numberOfRounds = Integer.parseInt(br.readLine());
      }catch(Exception e){ /*e.printStackTrace();*/ }
      for (int i=1;i<numberOfRounds+1;i++){
	    hand.put(String.format("1x%d",i), new Numbers(1,i));
	    hand.put(String.format("2x%d",i), new Numbers(2,i));
	    hand.put(String.format("3x%d",i), new Numbers(3,i));
	    hand.put(String.format("4x%d",i), new Numbers(4,i));
	    hand.put(String.format("5x%d",i), new Numbers(5,i));
	    hand.put(String.format("6x%d",i), new Numbers(6,i));
	    
	    hand.put(String.format("3kix%d",i), new SameKind(3,i));
	    hand.put(String.format("4kix%d",i), new SameKind(4,i));
	    hand.put(String.format("gx%d",i), new SameKind(5,i));
	    
	    hand.put(String.format("fulx%d",i), new Ful(i));
	    
	    hand.put(String.format("msx%d",i), new Straight(true,i));
	    hand.put(String.format("dsx%d",i), new Straight(false,i));
	    
	    hand.put(String.format("szx%d",i), new Chance(i));
	    hand.put(String.format("nparx%d",i), new Odd(false,i));
	    hand.put(String.format("parx%d",i),new Odd(true,i));
      }
      System.out.println("Wybierz ilosc graczy:");
      try{
        numberOfPlayers = Integer.parseInt(br.readLine());
      }catch(Exception e){ /*e.printStackTrace();*/ }
      players = new Player[numberOfPlayers];
      
      for(int i=0;i<numberOfPlayers;++i){
        System.out.println("Wprowadz nazwe kolejnego gracza:");
        String playerName = "";
        try{
          playerName = br.readLine();
        }catch(Exception e){ /*e.printStackTrace();*/ }
        players[i] = new Player(playerName,numberOfRounds);
        
        if(null == ranking.get(playerName))
          ranking.put(playerName, 0);
      }

      dice = new Dice(5,6);
    }
    
    public void play() {
        System.out.println("Playing Dice");
        
        System.out.println();
        System.out.println("Ranking:");
        for(Map.Entry<String,Integer> entry : ranking.entrySet())
          System.out.println(entry.getKey() + ": " + entry.getValue());
        
        for(int i=0;i<13;++i){
          
          for(Player player : players){
            for(Player scoreBoardPlayer : players){
              System.out.println("Gracz: "+scoreBoardPlayer.getName());
              scoreBoardPlayer.printPoint();
            }
            System.out.println("Gracz '" + player.getName() + "'");
            
            dice.roll();
            
            for(int j=0;j<2;++j){
		
	      System.out.println("Nowy rzut:");
	      dice.printDice();
              dice.reset();
              int numerKosci = 0;
              while(-1 != numerKosci  ){
                System.out.println("Wpisz numer kości aby ją zablokować lub -1 aby kontynuowac.");
                
                try{
                  numerKosci = Integer.parseInt(br.readLine());
                }catch(Exception e){ /*e.printStackTrace();*/ }
                
                if(-1 != numerKosci){
                  if(dice.stop(numerKosci))
                    System.out.println("OK");
                  else
                    System.out.println("Nie udalo sie. Czy na pewno nie wpisales za duzego lub za malego numeru?");
                }
		dice.printDice();
              }
              dice.roll();
              }
              dice.reset(); 
              System.out.println("Ostateczny wynik:");
              dice.printDice();
              Hand wybranyUklad;
              String uklad="";
              
              do{
                System.out.println("Ktory uklad wybierasz? (1 - jedynki, …., 6 - szóstki, 3ki - trojki, 4ki - czwórki, ful, ms - mały strit, ds - duzy strit, g - general, sz - szansa)+xmnozqnik");
                
                try{
                  uklad = br.readLine();
                }catch(Exception e){ /*e.printStackTrace();*/ }
                wybranyUklad = hand.get(uklad);
                if(null == wybranyUklad)
                  System.out.println("Nie ma takiego ukladu!");
                else
                if(-1 != player.getScore(uklad))
                  System.out.println("Ten uklad jest juz zuzyty!");
              }while(null == wybranyUklad || player.getScore(uklad)!=-1);
              
              int wynik = wybranyUklad.calculate(dice.getDice());
              
              System.out.println("Gracz "+player.getName()+" otrzymuje " + wynik + " punktow!");
              
              player.updateScoreboard(uklad, wynik);
            
          }
          
        }

        end();
    }
    
    public void end() {
              
        System.out.println("Koniec gry!");
        
        int maxScore = -1;
        
        for(Player player : players){
          int score = player.getScore();
          if(score > maxScore)
            maxScore = score;
        }
        
        System.out.println("Zwyciezcy:");
        
        for(Player player : players){
          int score = player.getScore();
          if(score == maxScore){
            System.out.println(player.getName());
            ranking.put(player.getName(), ranking.get(player.getName())+1);
          }
        }
        
        for(Player scoreBoardPlayer : players)
          scoreBoardPlayer.printPoint();
        
        ObjectOutputStream oos=null;
    
        try{
          oos=new ObjectOutputStream(new FileOutputStream(plikZRankingiem));
          
          oos.writeObject(ranking);
          oos.close();
        }catch(Exception e){e.printStackTrace();}
    } 
}
