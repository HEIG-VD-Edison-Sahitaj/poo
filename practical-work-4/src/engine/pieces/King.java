package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.Board;
import engine.tools.Tools;

/**
 * King Piece moves and eats in 8 directions by 1 tile.
 * 2 special behavior: win condition and rock maneuvers.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 * @see Piece
 */
public class King extends Piece {
    public King(int x, int y, PlayerColor color, Board board) {
        super(x, y, color, board);
        type = PieceType.KING;
    }

    @Override
    public boolean tryMove(int x, int y) {
        if(Tools.getdist(getX(),x) <= 1 && Tools.getdist(getY(),y) <= 1) {
            return board.grid[x][y] == null || board.grid[x][y].getColor() != getColor();
        }
        return Castle(x,y);
    }

    /**
     * Discover if a rock maneuver, requested by a 2 tile sideways movement, is possible,
     * and move preemptively the rook if it is.
     *
     * @param x,y coordinates targeted for movement
     * @return true if a rock maneuver is possible
     */
    public boolean Castle(int x, int y){
        int moveVect = Tools.getVectComp(getX(),x);
        if (getFirstMoved() == -1 && Tools.getdist(getX(),x) == 2 && Tools.getdist(getY(),y) == 0){
            Piece target = Tools.getAlongVect(getX(),getY(),new Tools.Vect(moveVect,0),-1,board);
            int j = getX();
            while ( Tools.isin(j,getY(),board) && j <= x){
                if (board.checkCheck(j,getY(),getColor())){
                    return false;
                }
                j++;
            }
            if(target != null && target.getType() == PieceType.ROOK &&
                    target.getColor() == getColor() && target.getFirstMoved() == -1)
            {
                board.move(target.getX(),target.getY(),x-moveVect,y);
                board.setTurn(board.getTurn()-1);
                return true;
            }
        }
        return false;
    }
}
