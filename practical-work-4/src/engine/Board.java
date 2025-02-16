package engine;

import java.util.ArrayList;

import javax.swing.plaf.synth.ColorType;

import chess.ChessView.UserChoice;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.Piece;
import engine.pieces.PieceFactory;

/**
 * board store and control a Piece grid.
 *
 * @author Arnaut Leyre
 * @author Gerber Tristan
 * @author Sahitaj Edison
 */
public class Board {
    public Piece[][] grid;
    private final ArrayList<ArrayList<Piece>> pieces;
    private final Piece[] kings = new Piece[PlayerColor.values().length];
    private int countTurn = 0;
    private final ChessView view;
    private boolean sim = false;

    public Board(ChessView view) {
        this.view = view;
        this.pieces = new ArrayList<>();
        for (int i = 0; i < PlayerColor.values().length; i++) {
            pieces.add(new ArrayList<>());
        }
        this.grid = new Piece[8][8];
    }

    /**
     * Add a Piece to the board.
     *
     * @param type Type of Piece to add
     * @param color color of Piece to add
     * @param x,y coordinates of Piece to add
     */
    public void addPiece(PieceType type, PlayerColor color, int x, int y) {
        // Exemple de logique simplifiée pour ajouter une pièce
        grid[x][y] = PieceFactory.createPiece(type, x, y, color, this);
        view.putPiece(type, color, x, y);
        pieces.get(color.ordinal()).add(grid[x][y]);
        if (type == PieceType.KING) {
            kings[color.ordinal()] = grid[x][y];
        }
    }

    /**
     * remove a Piece from the board.
     *
     * @param p Piece to remove
     */
    public void removePiece(Piece p) {
        // Exemple de logique simplifiée pour ajouter une pièce
        grid[p.getX()][p.getY()] = null;
        view.removePiece(p.getX(), p.getY());
        pieces.get(p.getColor().ordinal()).remove(p);
    }

    /**
     * Simulate a move on the board to check if it is valid.
     *
     * @param x1,y1 starting coordinates
     * @param x2,y2 destination's coordinates
     * @return true if move valid
     */
    private boolean simMove(int x1, int y1, int x2, int y2) {

        if (grid[x1][y1] != grid[x2][y2]// Se déplace pas
                && grid[x1][y1].tryMove(x2, y2) // Déplacement conforme aux mouvements possibles de la pièce
        )
        {
            sim = true;
            Piece piece = grid[x1][y1]; // Pièce à déplacer
            Piece target = grid[x2][y2]; // Case où déplacer

            // Effectue temporairement le mouvement
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = null;
            piece.setPosition(x2, y2);
            if (target != null) pieces.get(target.getColor().ordinal()).remove(target);

            // Checkcheck le roi du joueur (vérifier illegal move mettant en danger son propre roi)
            Piece king = kings[piece.getColor().ordinal()];
            boolean res = !checkCheck(king.getX(), king.getY(), piece.getColor());
            // Inverser les changements faits
            piece.setPosition(x1, y1);
            grid[x1][y1] = grid[x2][y2];
            grid[x2][y2] = target;
            if (target != null) pieces.get(target.getColor().ordinal()).add(target);
            sim = false;
            return res;
        }
        return false;
    }

    /**
     * Move a Piece on the board.
     *
     * @param x1,y1 starting coordinates
     * @param x2,y2 destination's coordinates
     * @return true if move valid
     */
    public boolean move(int x1, int y1, int x2, int y2) {

        if(grid[x1][y1] == null) return false;
        if (simMove(x1, y1, x2, y2) && countTurn % PlayerColor.values().length == grid[x1][y1].getColor().ordinal())
        {
            grid[x1][y1].setPosition(x2, y2);
            if(grid[x2][y2] != null) pieces.get(grid[x2][y2].getColor().ordinal()).remove(grid[x2][y2]);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = null;

            if(grid[x2][y2].getFirstMoved() == -1)
                grid[x2][y2].setFirstMoved(countTurn);

            for (int i = 0; i < kings.length; i++) {
                if (i != grid[x2][y2].getColor().ordinal()) {
                    Piece king = kings[i];
                    if (checkCheck(king.getX(), king.getY(), king.getColor()) && !lookForSave(king)) {
                        view.displayMessage("checkmate!!!");
                    }
                }
            }

            // UI
            view.removePiece(x1, y1);
            view.putPiece(grid[x2][y2].getType(), grid[x2][y2].getColor(), x2, y2);
            countTurn++;
            return true;
        }
        return false;
    }

    /**
     * Implements interface 'UserChoice'.
     */
    private class PieceChoice implements UserChoice {
        PieceType p;
        PieceChoice (PieceType p){
            this.p = p;
        }

        public String textValue() {
            return p.name();
        }
    }

    /**
     * Promote a pawn.
     *
     * @param p Pawn to promote
     */
    public void promote(Piece p) {
        PieceChoice pc = view.askUser("promotion", "quelle promotion", new PieceChoice(PieceType.BISHOP), new PieceChoice(PieceType.KNIGHT), new PieceChoice(PieceType.QUEEN), new PieceChoice(PieceType.ROOK));
        if(pc.p == null){
            return;
        }
        removePiece(p);
        addPiece(pc.p, p.getColor(), p.getX(), p.getY());
    }

    /**
     * Check if the position can be captured.
     *
     * @param x,y coordinates of position to check
     * @param c color to attack
     * @return true if position can be captured
     */
    public boolean checkCheck(int x, int y, PlayerColor c){
        sim = true;
        for (int i = 0; i < pieces.size(); i++) {
            if (i != c.ordinal()) {
                for (Piece p : pieces.get(i)) {
                    if(p.tryMove(x, y)){
                        return true;
                    }
                }
            }
        }
        sim = false;
        return false;
    }

    /**
     * If a Piece that can save it's king exist.
     *
     * @param king king to save
     * @return true if king can be saved
     */
    private boolean lookForSave(Piece king) {
        // Logique pour identifier les déplacements possibles pour sauver le roi
        for (int i = 0; i < pieces.get(king.getColor().ordinal()).size(); i++) {
            Piece p = pieces.get(king.getColor().ordinal()).get(i);
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (simMove(p.getX(), p.getY(), x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Represents the board as a string.
     *
     * @return the board as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(grid[i][j] != null ? grid[i][j].toString() : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public PieceType getPieceType(int x, int y) {
        return null;
    }

    public int getTurn(){
        return countTurn;
    }
    public void setTurn(int turn){
        countTurn = turn;
    }

    public boolean isSim() {
        return sim;
    }
}