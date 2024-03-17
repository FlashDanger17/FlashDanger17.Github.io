import java.util.Scanner;
public class Main {
  public static int shipHrow = 0;
      public static int shipHcolumn = 0;
      public static int shipVrow = 0;
      public static int shipVcolumn = 0;

      public static int AIshipHrow = 0;
      public static int AIshipHcolumn = 0;
      public static int AIshipVrow = 0;
      public static int AIshipVcolumn = 0;

      public static int playerTally1 = 0;
      public static int computerTally1 = 0;
      public static int playerTally2 = 0;
      public static int computerTally2 = 0;

    public static void main(String[] args) {
      char[][] player = new char[10][10];
      char[][] computer = new char[10][10];
      char[][] computerGuess = new char[10][10];
      char[][] playerGuess = new char[10][10];
      for (int i = 0; i < player.length; i++) {
        for (int j = 0; j < player[i].length; j++) {
          player[i][j] = '-';
          computer[i][j] = '-';
          computerGuess[i][j] = '-';
          playerGuess[i][j] = '-';
        }
      }

      System.out.println("Welcome to Java Battleship!");
      System.out.println("Try your hand at facing the fearsome computer and win!");
      playerPlace(player);
      print(player);
      computerPlace(computer);
      while(true) {
      playerGuess(computer,  playerGuess);
      System.out.println("Your grid:");
      print(playerGuess);
      computerGuess(player,  computerGuess);
      System.out.println("Computer grid:");
      print(computerGuess);

      if ((playerTally1 + playerTally2) == 2) {
        System.out.println("Player wins!!!");
        break;
      }

      if ((computerTally1 + computerTally2) == 2) {
        System.out.println("Computer wins!!!");
        break;
      }

      }
    }


    public static int[] playerPlace(char[][] player) {
      Scanner grid = new Scanner(System.in);
      while (true) {
      System.out.println("insert Horizontal Ship Row: ");
      int hrow = grid.nextInt();
      System.out.println("Insert Horizontal Ship Column: ");
      int hcolumn = grid.nextInt();
      if(hrow < 0 || hrow > 9 || hcolumn < 0 || hcolumn >= 8) {
        continue;
      }
      for (int i = 0; i <= 2; i++) {
        player[hrow][hcolumn + i] = 'x';
      }
      shipHrow = hrow;
      shipHcolumn = hcolumn;
      break;
      }

      while (true) {
      System.out.println("insert Vertical Ship Row: ");
      int vrow = grid.nextInt();
      System.out.println("Insert Vertical Ship Column: ");
      int vcolumn = grid.nextInt();
      if(vrow < 0 || vrow >= 7 || vcolumn < 0 || vcolumn > 9) {
        continue;
      }
      else if (player[vrow][vcolumn] == 'x' || player[vrow + 1][vcolumn] == 'x' || player[vrow + 2][vcolumn] == 'x' || player[vrow + 3][vcolumn] == 'x') {
        continue;
      }
      for (int i = 0; i <= 3; i++) {
        player[vrow + i][vcolumn] = 'x';
      }
      shipVrow = vrow;
      shipVcolumn = vcolumn;
      break;

      }
      int[] Values = {shipHrow, shipHcolumn, shipVrow, shipVcolumn};
      return Values;
    }


    public static int[] computerPlace(char[][] computer) {
      AIshipHrow = ((int)(Math.random()*10));
      AIshipHcolumn = ((int)(Math.random()*8));
      for (int i = 0; i <= 2; i++) {
        computer[AIshipHrow][AIshipHcolumn + i] = 'x';
      }

      while (true) {
       AIshipVrow = ((int)(Math.random()*7));
       AIshipVcolumn = ((int)(Math.random()*10));
       if (computer[AIshipVrow][AIshipVcolumn] == 'x' || computer[AIshipVrow + 1][AIshipVcolumn] == 'x' || computer[AIshipVrow + 2][AIshipVcolumn] == 'x' || computer[AIshipVrow + 3][AIshipVcolumn] == 'x') {
        continue;
      }
      for (int i = 0; i <= 3; i++) {
        computer[AIshipVrow + i][AIshipVcolumn] = 'x';
      }
      break;
      }
      int[] AIValues = {AIshipHrow, AIshipHcolumn, AIshipVrow, AIshipVcolumn};
      return AIValues;
    }

    public static void playerGuess(char[][] computer, char[][] playerGuess) {
      Scanner guess = new Scanner(System.in);
      int row = 0;
      int column = 0;

      while (true) {
        while (true) {
          System.out.println("Guess a row: ");
          row = guess.nextInt();
          if (row < 0 || row > 9) {
            continue;
          }
          break;
        }
        while (true) {
          System.out.println("Guess a column: ");
          column = guess.nextInt();
          if (column < 0 || column > 9) {
            continue;
          }
          break;
        }

        if (playerGuess[row][column] != '1' && playerGuess[row][column] != '0') {
          break;
        }
      }  

      if (computer[row][column] == 'x') {
        playerGuess[row][column] = '1';
      }
      else {
        playerGuess[row][column] = '0';
      }

      if (playerGuess[AIshipHrow][AIshipHcolumn] == '1' && playerGuess[AIshipHrow][AIshipHcolumn + 1] == '1' && playerGuess[AIshipHrow][AIshipHcolumn + 2] == '1' && playerTally1 == 0) {
        playerTally1 = 1;
        System.out.println("You sunk a ship!");
      }

      else if (playerGuess[AIshipVrow][AIshipVcolumn] == '1' && playerGuess[AIshipVrow + 1][AIshipVcolumn] == '1' && playerGuess[AIshipVrow + 2][AIshipVcolumn] == '1' && playerGuess[AIshipVrow + 3][AIshipVcolumn] == '1' && playerTally2 == 0) {
        playerTally2 = 1;
        System.out.println("You sunk a ship!");

      }
    }

    public static void computerGuess(char[][]player, char[][]computerGuess) {
      while (true) {
      int row = ((int)(Math.random()*10));
      int column = ((int)(Math.random()*10));

      if (computerGuess[row][column] != '-') {
        continue;
      }

      if (player[row][column] == 'x') {
        computerGuess[row][column] = '1';
        break;
      }
      else {
        computerGuess[row][column] = '0';
        break;
      }

      }

      if (computerGuess[shipHrow][shipHcolumn] == '1' && computerGuess[shipHrow][shipHcolumn + 1] == '1' && computerGuess[shipHrow][shipHcolumn + 2] == '1' && computerTally1 == 0) {
        computerTally1 = 1;
        System.out.println("A ship of yours was sunk!");
      }



      if (computerGuess[shipVrow][shipVcolumn] == '1' && computerGuess[shipVrow + 1][shipVcolumn] == '1' && computerGuess[shipVrow + 2][shipVcolumn] == '1' && computerGuess[shipVrow + 3][shipVcolumn] == '1' && computerTally2 == 0) {
        computerTally2 = 1;
        System.out.println("A ship of yours was sunk!");
      }



    }

    public static void print(char[][] arr) {
    System.out.println("  0 1 2 3 4 5 6 7 8 9 ");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(i);
        for (int j = 0; j < arr[i].length; j++) {
          System.out.print(" "+arr[i][j]);
        }
        System.out.println();
      }

  }













}
