package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.tools.Tools;

/**
 * Rook Piece moves and eats in a line.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 * @see Piece
 */
public class Rook extends Piece {
    public Rook(int x, int y, PlayerColor color, Board board) {
        super(x, y, color, board);
        type = PieceType.ROOK;
    }

    @Override
    public boolean tryMove(int x, int y) {
        Piece target = Tools.getLine(getX(), getY(), x, y, board);
        return target == null || target.getX() == x && target.getY() == y && target.getColor() != getColor();
    }
}
