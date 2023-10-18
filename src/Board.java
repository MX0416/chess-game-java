public class Board {

    // Instance variables
    private Piece[][] board;


    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }


    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not 
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        System.out.println(board[startRow][startCol]);
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol)) {
            board[startRow][startCol].setPosition(endRow, endCol);
            setPiece(endRow, endCol, board[startRow][startCol]);

            setPiece(startRow, startCol, null);
            return true;
        }
        return false;
    }

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int kingCheck = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].character == '\u265a' || board[i][j].character == '\u2654') {
                        kingCheck++;
                    }
                }
            }
        }
        if (kingCheck == 2) {
            return false;
        }
        return true;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for (int i = 0; i < 8; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for (int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public void clear() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (       startRow <= 7
                && startRow >= 0
                && startCol <= 7
                && startCol >= 0
                && endRow <= 7
                && endRow >= 0
                && endCol <= 7
                && endCol >= 0
                && board[startRow][startCol] != null
                && board[startRow][startCol].getIsBlack() == isBlack
                && ((board[endRow][endCol] == null) || (board[endRow][endCol].getIsBlack() != isBlack))) {
            return true;
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow == endRow) && (startCol == endCol)) {
            return true;
        }

        if ((endRow == (startRow + 1) && startCol == endCol) ||
                (endRow == (startRow - 1) && startCol == endCol) ||
                (startRow == endRow && (endCol == (startCol + 1))) ||
                (startRow == endRow && (endCol == (startCol - 1))) ||
                (endRow == (startRow + 1) && endCol == (startCol + 1)) ||
                (endRow == (startRow - 1) && endCol == (startCol - 1)) ||
                (endRow == (startRow - 1) && endCol == (startCol + 1)) ||
                (endRow == (startRow + 1) && endCol == (startCol - 1))
        ) {
            return true;
        }

        return false;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }

//        check direction
        if (startCol < endCol) {
//            direction going right
            for (int col = startCol + 1; col < endCol; col++) {
                if (board[startRow][col] != null) {
                    return false;
                }
            }
        } else {
//            direction going left
            for (int col = startCol - 1; col > endCol; col--) {
                if (board[startRow][col] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }

//        check direction
        if (startRow < endRow) {
//            direction going down
            for (int row = startRow + 1; row < endRow; row++) {
                if (board[row][startCol] != null) {
                    return false;
                }
            }
        } else {
//            direction going up
            for (int row = startRow - 1; row > endRow; row--) {
                if (board[row][startCol] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            return true;
        }

        if (Math.abs(endRow - startRow) == Math.abs(endCol - startCol)) {
            if (startRow < endRow && startCol < endCol) {
//            direction: down right
                int col = startCol + 1;
                for (int row = startRow + 1; row < endRow; row++) {
                    if (board[row][col] != null) {
                        return false;
                    }
                    col++;
                }
                return true;
            } else if (startRow < endRow && startCol > endCol) {
//            direction: down left
                int col = startCol - 1;
                for (int row = startRow + 1; row < endRow; row++) {

                    if (board[row][col] != null) {
                        return false;
                    }
                    col--;
                }
                return true;
            } else if (startRow > endRow && startCol < endCol) {
//            direction: up right
                int col = startCol + 1;
                for (int row = startRow - 1; row > endRow; row--) {
                    if (board[row][col] != null) {
                        return false;
                    }
                    col++;
                }
                return true;
            } else if (startRow > endRow && startCol > endCol) {
//            direction: up left
                int col = startCol - 1;
                for (int row = startRow - 1; row > endRow; row--) {
                    if (board[row][col] != null) {
                        return false;
                    }
                    col--;
                }
                return true;
            } else {return false;}
        }return false;
    }
}

