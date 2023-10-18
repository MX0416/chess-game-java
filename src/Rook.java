public class Rook {
        private int row;
        private int col;
        private boolean isBlack;

        public Rook(int row, int col, boolean isBlack) {
            this.row = row;
            this.col = col;
            this.isBlack = isBlack;
        }

        public boolean isMoveLegal(Board board, int endRow, int endCol) {
            // rook can move horizontally or vertically

//            check if move is horizontal
            if (col != endCol) {
                if (board.verifyHorizontal(this.row, this.col, endRow, endCol)) {
                    if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
                        return true;
                    }
                }
            }
//            check if move is vertical
            if (row != endRow) {
                if (board.verifyVertical(this.row, this.col, endRow, endCol)) {
                    if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, isBlack)) {
                        return true;
                    }
                }
            }
            return false;
        }

}
