package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

import static java.lang.Math.abs;

/**
 * Pawn piece moves forward and eats diagonally.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 */
public class Pawn extends Piece {
    public Pawn(int x, int y, PlayerColor color, Board board) {
        super(x, y, color, board);
        type = PieceType.PAWN;
    }

    /**
     * Check if the pawn can make the en passant move.
     * @param x x coordinate of the target
     * @param y y coordinate of the target
     * @return true if the move is possible, false otherwise
     */
    private boolean tryEnPassant(int x, int y) {
        Piece target = board.grid[x][getY()];
        if (target instanceof Pawn && target.getFirstMoved() == board.getTurn() - 1 && target.getColor() != getColor()) {
            board.removePiece(target);
            return true;
        }
        return false;
    }
    @Override
    public boolean tryMove(int x, int y) {
        int dx = abs(x - getX());
        int dy = y - getY();

        if ((getColor() == PlayerColor.WHITE && dy <= 0) || (getColor() == PlayerColor.BLACK && dy >= 0)) {
            return false;
        }

        if (dx == 0 && board.grid[x][y] == null ) {
            if (abs(dy) == 1) {
                if (checkPromoted(y )) {
                    board.promote(this);
                }
                return true;
            } else if (abs(dy) == 2 && getFirstMoved() == -1) {
                return board.grid[getX()][getY() + dy / 2] == null;
            }
        } else if (dx == 1 && abs(dy) == 1) {
            Piece target = board.grid[x][y];
            if (target != null && target.getColor() != getColor()) {
                if (checkPromoted(y)) board.promote(this);
                return true;
            }
            return tryEnPassant(x, y);
        }
        return false;
    }

    /**
     * Check if the pawn can be promoted.
     * @param y y coordinate of the target
     * @return true if the pawn is promoted, false otherwise
     */
    private boolean checkPromoted(int y) {
        return ((getColor() == PlayerColor.WHITE && y == 7) || (getColor() == PlayerColor.BLACK && y == 0)) && !board.isSim();
    }
}