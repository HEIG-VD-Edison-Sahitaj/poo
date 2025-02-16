package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.tools.Tools;

/**
 * Queen Piece moves and eats, diagonally and in a line.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 * @see Piece
 */
public class Queen extends Piece {
    public Queen(int x, int y, PlayerColor color, Board board) {
        super(x, y, color, board);
        type = PieceType.QUEEN;
    }

    @Override
    public boolean tryMove(int x, int y) {
        Piece target = Tools.getDiag(getX(), getY(), x, y, board);
        if (target == this)
            target = Tools.getLine(getX(), getY(), x, y, board);
        return target == null || target.getX() == x && target.getY() == y && target.getColor() != getColor();
    }
}
