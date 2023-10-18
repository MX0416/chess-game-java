public class Queen {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Queen(int row, int col, boolean isBlack) {
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
//        check if move is horizontal
        if (col != endCol && row == endRow) {
            if (board.verifyHorizontal(row, col, endRow, endCol)) {
                if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
                    return true;
                }
            }
        }

//        check if move is vertical
        if (row != endRow && col == endCol) {
            if (board.verifyVertical(row, col, endRow, endCol)) {
                if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
                    return true;
                }
            }
        }

//        check if move is diagonal
        if (row != endRow && col != endCol) {
            if (board.verifyDiagonal(row, col, endRow, endCol)) {
                if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
