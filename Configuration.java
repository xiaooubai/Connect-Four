
public class Configuration {
    
    public int[][] board;
    public int[] available;
    boolean spaceLeft;
    
    public Configuration(){
        board = new int[7][6];
        available = new int[7];
        spaceLeft = true;
    }
    
    public void print(){
        System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
        System.out.println("+---+---+---+---+---+---+---+");
        for (int i = 0; i < 6; i++){
            System.out.print("|");
            for (int j = 0; j < 7; j++){
                if (board[j][5-i] == 0){
                    System.out.print("   |");
                }
                else{
                    System.out.print(" "+ board[j][5-i]+" |");
                }
            }
            System.out.println();
        }
    }
    
    public void addDisk (int index, int player){
     
        
        if (available[index] < 6) {
            this.board[index][available[index]] = player;
            available[index]+=1;
        }
    }
    
    
    public boolean isWinning (int lastColumnPlayed, int player){
        
        
        for (int i = 0; i < 4; i++ ) {
            if (board[i][available[lastColumnPlayed]-1]
                == player && board[i+1][available[lastColumnPlayed]-1]
                == player && board[i+2][available[lastColumnPlayed]-1]
                == player && board[i+3][available[lastColumnPlayed]-1]
                ==player) {
                return true;
            }
        }
        
        
        for (int j = 0; j < 3; j++ ) {
            if (board[lastColumnPlayed][2-j]
                == player && board[lastColumnPlayed][3-j]
                == player && board[lastColumnPlayed][4-j]
                == player && board[lastColumnPlayed][5-j]
                ==player) {
                return true;
            }
        }
        
        for (int k = 0; k < 3; k++) {
            for (int x = 0; x < 0; x--){
                if (board[k][x]
                    == player && board[k+1][x+1]
                    == player && board[k+2][x+2]
                    == player && board[k+3][x+3]
                    ==player) {
                    return true;
                }
            }
        }
        
        for (int y = 0; y < 3; y++) {
            for (int z = 5; z > 2; z--) {
                if (board[y][z]
                    == player && board[y+1][z-1]
                    == player && board[y+2][z-2]
                    == player && board[y+3][z-3]
                    ==player) {
                    return true;
                }
            }
        }
        
        
        
        
        return false;
        
    }
    public int canWinNextRound (int player){
       
        
        
        for (int i = 0; i < 7; i++) {
            if (available[i] == 6) {
                continue;
            }
            addDisk(i, player);
            if (isWinning(i, player) == true) {
                available[i]-=1;
                board[i][available[i]] = 0;
                return i;
            } else {
                available[i]-=1;
                board[i][available[i]] = 0;
                
            }
        }
        
        return -1;
    }
    
    public int canWinTwoTurns (int player){
       
        int opp = 0;
        
        if (player == 1) {
            opp = 2;
        } else if (player == 2){
            opp = 1;
        }
        
        
        for (int i = 0; i <7; i++) {
            boolean test = true;
            if (available[i] < 6) {
               
                addDisk(i,player);
              
                if (canWinNextRound(opp) != -1) {
                    
                    board[i][available[i]-1] = 0;
                    available[i] = available[i] -1;
                    continue;
                }
                for (int j = 0; j <7; j++) {
                    if (available[j] < 6) {
                        
                        addDisk(j, opp);
                        if (canWinNextRound(player) != -1) {
                            board[j][available[j]-1] = 0;
                            available[j] = available[j] -1;
                        }
                        else {
                            board[j][available[j]-1] = 0;
                            available[j] = available[j] -1;
                            test = false;
                            break;
                        }
                    }
                }
                board[i][available[i]-1] = 0;
                available[i] = available[i] -1;
                
                if (test) {
                    return i;
                }
                
            }
        }
        
        
        return -1; 
    }
    
}


