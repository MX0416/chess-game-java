public class Knight {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {

//        up, down, right, left
        if (        (endRow == (row + 2) && (endCol == col + 1 || endCol == col - 1))
                ||  (endRow == (row - 2) && (endCol == col + 1 || endCol == col - 1))
                ||  (endCol == (col + 2) && (endRow == row + 1 || endRow == row - 1))
                ||  (endCol == (col - 2) && (endRow == row + 1 || endRow == row - 1)) ){
            if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
                return true;
            }
        }

        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
