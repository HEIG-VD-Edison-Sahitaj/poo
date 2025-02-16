package engine.tools;

import engine.Board;
import engine.pieces.Piece;

import static java.lang.Math.max;

/**
 * Collection of functions used throughout the chess engine.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 */
public final class Tools {
    public record Vect(int x, int y) {}
    /**
     * Evaluate if the coordinates are part of the board.
     *
     * @param x,y coordinates to evaluate
     * @param board board of the game
     * @return true if the coordinates are part of the board
     */
    public static boolean isin(int x, int y,Board board) {
        return x > -1 && x < board.grid.length && y > -1 && y < board.grid[0].length;
    }

    /**
     * find the first Piece encountered along the vector at a certain distance.
     *
     * @param x1,y1 coordinates of origin
     * @param v the vector of movement
     * @param dist distance of the movement to evaluate (-1 to search until the edge of the board)
     * @param board board of the game
     * @return first Piece encountered along the vector at a certain distance, null if no Piece was found
     */
    public static Piece getAlongVect(int x1, int y1, Vect v, int dist, Board board) {
        int i = x1+v.x;
        int j = y1+v.y;
        while (dist != 0 && (isin(i,j,board))) {
            if(board.grid[i][j] != null){
                return board.grid[i][j];
            }
            i+=v.x;
            j+=v.y;
            dist--;
        }
        return null;
    }

    /**
     * Evaluate the difference between 2 values.
     *
     * @param x1,x2 values to compare
     * @return the absolute value of the difference between the 2 values
     */
    public static int getdist(int x1, int x2)
    {
        return Math.abs(x2-x1);
    }

    /**
     * Gets the component of direction between 2 values.
     *
     * @param x1,x2 values to compare
     * @return the component of direction between the 2 values or 0 if the difference is null
     */
    public static int getVectComp(int x1, int x2)
    {
        return (x2-x1) == 0 ? 0 : (x2-x1) / getdist(x1,x2);
    }

    /**
     * Apply getAlongVect along a diagonal.
     *
     * @param x1,y1 coordinates of origin
     * @param x2,y2 coordinates of target
     * @param board board of the game
     * @return Piece given by getAlongVect or Piece of origin if the 2 points do not form a diagonal
     */
    public static Piece getDiag(int x1, int y1, int x2, int y2, Board board) {
        if (getdist(x1,x2) != getdist(y1,y2)) {
            return board.grid[x1][y1];
        }
        Vect move = new Vect(getVectComp(x1,x2), getVectComp(y1,y2));
        return getAlongVect(x1, y1, move, getdist(x1,x2), board);
    }

    /**
     * Apply getAlongVect along a line.
     *
     * @param x1,y1 coordinates of origin
     * @param x2,y2 coordinates of target
     * @param board board of the game
     * @return Piece given by getAlongVect or Piece of origin if the 2 points do not form a line
     */
    public static Piece getLine(int x1, int y1, int x2, int y2, Board board) {
        if (getdist(x1,x2) != 0 && getdist(y1,y2) != 0) {
            return board.grid[x1][y1];
        }
        Vect move = new Vect(getVectComp(x1,x2), getVectComp(y1,y2));
        return getAlongVect(x1, y1, move, max(getdist(x1,x2),getdist(y1,y2)), board);
    }
}
