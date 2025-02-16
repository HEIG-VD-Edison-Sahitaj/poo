package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;

/**
 * Piece is an abstract class that represents a chess Piece.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 */
public abstract class Piece {
    private int firstMoved = -1;
    private int x, y;
    private final PlayerColor color;
    protected PieceType type;
    protected Board board;

    public Piece(int x, int y, PlayerColor color, Board board) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.board = board;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    public PlayerColor getColor() { return color; }
    public PieceType getType() { return type; }

    public int getFirstMoved() { return firstMoved; }
    public void setFirstMoved(int turn) { this.firstMoved = turn; }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + color + ")";
    }

    /**
     * Evaluate if the Piece can move to the targeted coordinates.
     *
     * @param x,y coordinates of target
     * @return true if the piece can move to the coordinates
     */
    public abstract boolean tryMove(int x, int y);
}
