package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

/**
 * PieceFactory is a class that helps the board with the creation of pieces of different Type.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 */
public class PieceFactory {
    public static Piece createPiece(PieceType type, int x, int y, PlayerColor color, Board board) {
        switch (type) {
            case PAWN:
                return new Pawn(x, y, color, board);
            case ROOK:
                return new Rook(x, y, color, board);
            case KNIGHT:
                return new Knight(x, y, color, board);
            case BISHOP:
                return new Bishop(x, y, color, board);
            case QUEEN:
                return new Queen(x, y, color, board);
            case KING:
                return new King(x, y, color, board);
            default:
                throw new IllegalArgumentException("Unknown PieceType: " + type);
        }
    }
}
