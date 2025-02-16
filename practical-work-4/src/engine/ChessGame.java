package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PieceType;
import chess.PlayerColor;

public class ChessGame implements ChessController {

  protected ChessView view;
  public Board board;

  @Override
  public void start(ChessView view) {
    setView(view);
    view.startView();
  }

  public void setView(ChessView view) {
    this.view = view;
  }

  @Override
  public boolean move(int fromX, int fromY, int toX, int toY) {
    return board.move(fromX, fromY, toX, toY);
  }

  @Override
  public void newGame() {
    // Affiche un message de démarrage de partie
    view.displayMessage("Starting a new chess game!");
    // Initialise le plateau avec les pièces de départ
    setPieces();
    view.displayMessage("All pieces placed. Game is ready to start!");
  }

  public void setPieces() {
    board = new Board(view);
    // Placer les pièces blanches
    board.addPiece(PieceType.ROOK, PlayerColor.WHITE, 0, 0);    // Tour
    board.addPiece(PieceType.KNIGHT, PlayerColor.WHITE, 1, 0);  // Cavalier
    board.addPiece(PieceType.BISHOP, PlayerColor.WHITE, 2, 0);  // Fou
    board.addPiece(PieceType.QUEEN, PlayerColor.WHITE, 3, 0);   // Dame
    board.addPiece(PieceType.KING, PlayerColor.WHITE, 4, 0);    // Roi
    board.addPiece(PieceType.BISHOP, PlayerColor.WHITE, 5, 0);  // Fou
    board.addPiece(PieceType.KNIGHT, PlayerColor.WHITE, 6, 0);  // Cavalier
    board.addPiece(PieceType.ROOK, PlayerColor.WHITE, 7, 0);    // Tour
    for (int i = 0; i < 8; i++) {
      board.addPiece(PieceType.PAWN, PlayerColor.WHITE, i, 1); // Pions blancs
    }

    // Placer les pièces noires
    board.addPiece(PieceType.ROOK, PlayerColor.BLACK, 0, 7);    // Tour
    board.addPiece(PieceType.KNIGHT, PlayerColor.BLACK, 1, 7);  // Cavalier
    board.addPiece(PieceType.BISHOP, PlayerColor.BLACK, 2, 7);  // Fou
    board.addPiece(PieceType.QUEEN, PlayerColor.BLACK, 3, 7);   // Dame
    board.addPiece(PieceType.KING, PlayerColor.BLACK, 4, 7);    // Roi
    board.addPiece(PieceType.BISHOP, PlayerColor.BLACK, 5, 7);  // Fou
    board.addPiece(PieceType.KNIGHT, PlayerColor.BLACK, 6, 7);  // Cavalier
    board.addPiece(PieceType.ROOK, PlayerColor.BLACK, 7, 7);    // Tour
    for (int i = 0; i < 8; i++) {
      board.addPiece(PieceType.PAWN, PlayerColor.BLACK, i, 6); // Pions noirs
    }
  }
}