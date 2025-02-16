package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.tools.Tools;

/**
 * Bishop Piece moves and eats diagonally.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 * @see Piece
 */
public class Bishop extends Piece {
    public Bishop(int x, int y, PlayerColor color, Board board) {
        super(x, y, color, board);
        type = PieceType.BISHOP;
    }

    @Override
    public boolean tryMove(int x, int y) {
        Piece target = Tools.getDiag(getX(), getY(), x, y, board);
        return target == null || (target.getX() == x && target.getY() == y && target.getColor() != getColor());
    }
}
