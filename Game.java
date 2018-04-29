
import java.io.*;

public class Game {
    
    public static int play(InputStreamReader input){
        BufferedReader keyboard = new BufferedReader(input);
        Configuration c = new Configuration();
        int columnPlayed = 3; int player;
        
        c.addDisk(firstMovePlayer1(), 1);
        int nbTurn = 1;
        
        while (nbTurn < 42){
            player = nbTurn %2 + 1;
            if (player == 2){
                columnPlayed = getNextMove(keyboard, c, 2);
            }
            if (player == 1){
                columnPlayed = movePlayer1(columnPlayed, c);
            }
            System.out.println(columnPlayed);
            c.addDisk(columnPlayed, player);
            if (c.isWinning(columnPlayed, player)){
                c.print();
                System.out.println("Congrats to player " + player + " !");
                return(player);
            }
            nbTurn++;
        }
        return -1;
    }
    
    public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
       
        int input = 0;
        int player2 = 0;
        boolean truth = true;
        
        if (player == 1) {
            player2 = 2;
        } else {
            player2 = 1;
        }
        
        
        System.out.println("Your opponent has made a move. It is your turn.");
        c.print();
        
        try {
            while (truth) {
                input = Integer.parseInt(keyboard.readLine());
                if (input < 0||input > 6) {
                    System.out.println("ERROR! COLUMN NOT FOUND");
                    System.out.println("PlEASE CHOOSE ANOTHER COLUMN PLEASE THANKS!");
                    continue;
                }
                if (c.available[input] >= 6) {
                    System.out.println("ERROR ! COLUMN IS FILLED! PLEASE CHOOSE ANOTHER ONE");
                    continue;
                } else {
                    truth = false;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return input;
    }
    
    public static int firstMovePlayer1 (){
        return 3;
    }
    
    public static int movePlayer1 (int columnPlayed2, Configuration c){
      
        int opp = 1;
        
        if (c.canWinNextRound(opp) != -1) {
            return c.canWinNextRound(opp);
        } else if (c.canWinTwoTurns(opp) != -1) {
            return c.canWinTwoTurns(opp);
        } else {
            if (c.available[columnPlayed2]!=6) {
                return columnPlayed2;
            } else {
                
                for (int i = 1; i < 7; i++) {
                    if (columnPlayed2 - i > -1) {
                        if (c.available[columnPlayed2 - i] !=6) {
                            return columnPlayed2 - i;
                        }
                    }
                    if (columnPlayed2 + i < 7) {
                        if (c.available[columnPlayed2 + i]  !=6) {
                            return columnPlayed2 + i;
                        }
                    }
                    
                }
            }
        }
        
        return -1;
    }
    
}
