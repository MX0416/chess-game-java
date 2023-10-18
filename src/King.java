public class King {

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyAdjacent(row, col, endRow, endCol) && board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
//           check if move is adjacent
            return true;
        }
        return false;
    }

    // Instance variables
    private int row;
    private int col;
    private boolean isBlack;
}
