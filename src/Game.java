import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board newGame = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", newGame);
        System.out.println(newGame);

        int totalMoveCounter = 1;

        Scanner scanner = new Scanner(System.in);

        String playerMove;
        String[] splitPlayerMove;
        boolean isLegal;

        while(!newGame.isGameOver()) {
            if (totalMoveCounter % 2 == 0) {
                System.out.println("It's currently black's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                playerMove = scanner.nextLine();
                splitPlayerMove = playerMove.split(" ");

                isLegal = newGame.movePiece(Integer.parseInt(splitPlayerMove[0]), Integer.parseInt(splitPlayerMove[1]), Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3]));

                //                check pawn promotion
                if (newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u265f' || newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u2659') {
                    newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).promotePawn(Integer.parseInt(splitPlayerMove[2]), newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).getIsBlack());
                }

                System.out.println(newGame);

                while (!isLegal) {
//                    while loop to ask the player to choose a valid move
                    System.out.println("Invalid move, black move again. \nWhat is your move? (format: [start row] [start col] [end row] [end col]");
                    playerMove = scanner.nextLine();
                    splitPlayerMove = playerMove.split(" ");
                    isLegal = newGame.movePiece(Integer.parseInt(splitPlayerMove[0]), Integer.parseInt(splitPlayerMove[1]), Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3]));

                    //                check pawn promotion
                    if (newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u265f' || newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u2659') {
                        newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).promotePawn(Integer.parseInt(splitPlayerMove[2]), newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).getIsBlack());
                    }

                    System.out.println(newGame);
                }


                totalMoveCounter++;
            } else {
                System.out.println("It's currently white's turn to play.");
                System.out.println("What is your move? (format: [start row] [start col] [end row] [end col]");
                playerMove = scanner.nextLine();
                splitPlayerMove = playerMove.split(" ");
                isLegal = newGame.movePiece(Integer.parseInt(splitPlayerMove[0]), Integer.parseInt(splitPlayerMove[1]), Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3]));

                //                check pawn promotion
                if (newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u265f' || newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u2659') {
                    newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).promotePawn(Integer.parseInt(splitPlayerMove[2]), newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).getIsBlack());
                }

                System.out.println(newGame);

                while (!isLegal) {
//                    while loop to ask the player to choose a valid move
                    System.out.println("Invalid move, white move again. \nWhat is your move? (format: [start row] [start col] [end row] [end col]");
                    playerMove = scanner.nextLine();
                    splitPlayerMove = playerMove.split(" ");
                    isLegal = newGame.movePiece(Integer.parseInt(splitPlayerMove[0]), Integer.parseInt(splitPlayerMove[1]), Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3]));

                    //                check pawn promotion
                    if (newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u265f' || newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).character == '\u2659') {
                        newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).promotePawn(Integer.parseInt(splitPlayerMove[2]), newGame.getPiece(Integer.parseInt(splitPlayerMove[2]), Integer.parseInt(splitPlayerMove[3])).getIsBlack());
                    }

                    System.out.println(newGame);
                }


                totalMoveCounter++;
            }
        }
    }
}
