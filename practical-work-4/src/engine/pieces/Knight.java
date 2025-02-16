package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

/**
 * Knight Piece moves and eats in all "L"(3 by 2) shape directions.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 * @see Piece
 */
public class Knight extends Piece {
    public Knight(int x, int y, PlayerColor color, Board board) {
        super(x, y, color, board);
        type = PieceType.KNIGHT;
    }

    @Override
    public boolean tryMove(int x, int y) {
        int dx = Math.abs(x - getX());
        int dy = Math.abs(y - getY());
        if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
            Piece target = board.grid[x][y];
            return target == null || target.getColor() != getColor();
        }
        return false;
    }
}
