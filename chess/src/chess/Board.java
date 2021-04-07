package chess;

import java.util.*;

public class Board {
	static List<Square> squares = new ArrayList<Square>();
	static HashMap<Integer, String> map = new HashMap<Integer, String>();
	static HashMap<String, Integer> mapNumToStr = new HashMap<String, Integer>();
	static List<Piece> pieces = new ArrayList<>();

	public static Piece whatIsInSquare(String x, int y) {
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getX() == x && squares.get(i).getY() == y) {
				return squares.get(i).getPiece();
			}
		}
		return null;
	}

	public static Piece getToBeEaten(Square positionDst) {
		return Board.whatIsInSquare(positionDst.getX(), positionDst.getY());
	}

	public static boolean isPieceWithinBoundaries(Piece piece) {
		boolean yInBoundaries = piece.getPosition().getY() >= 0 && piece.getPosition().getY() < 9;
		boolean xInBoundaries = piece.getConvertedX() >= 0 && piece.getConvertedX() < 9;
		return (yInBoundaries && xInBoundaries);
	}

	public static boolean isSquareWithinBoundaries(Square square) {
		if (square == null) {
			return false;
		}
		boolean yInBoundaries = square.getY() >= 0 && square.getY() < 9;
		boolean xInBoundaries = Board.mapNumToStr.get(square.getX()) >= 0 && Board.mapNumToStr.get(square.getX()) < 9;
		return (yInBoundaries && xInBoundaries);
	}

	public static Square giveMeSquare(String x, int y) {
		for (int i = 0; i < squares.size(); i++) {
			if (squares.get(i).getX() == x && squares.get(i).getY() == y) {
				return squares.get(i);
			}
		}
		return null;
	}

	public static Piece giveMePiece(String name) {
		for (int i = 0; i < pieces.size(); i++) {
			Piece thisPiece = pieces.get(i);
			if (thisPiece.getName().equals(name)) {
				return pieces.get(i);
			}
		}
		return null;
	}

	public static final int BOARD_SIZE = 8;

	public void displayChessBoard() {
//		   String brdStr = "";
//		    brdStr += " a b c d e f g h\n";
		for (int row = 0; row < BOARD_SIZE; row++) {
			System.out.println("");
			if (row == 0) {
				System.out.println("-----------------------------------");
				System.out.println("  | a | b | c | d | e | f | g | h |");
			}
			System.out.println("-----------------------------------");

			for (int column = 0; column < BOARD_SIZE; column++) {
				if (column == 0) {
					System.out.print("|" + (row + 1));
				}
				if (giveMeSquare(map.get(column), row).getPiece() != null) {
					System.out.print("| " + giveMeSquare(map.get(column), row).getPiece().getLogo() + " ");
				} else {
					System.out.print("| " + "•" + " ");
				}
				if (column == BOARD_SIZE - 1) {
					System.out.print("|");
				}

			}
		}
		System.out.println("");
		System.out.println("-----------------------------------");
	}

	public void setUpBoard() {
		// create map for letters
		map.put(0, "a");
		map.put(1, "b");
		map.put(2, "c");
		map.put(3, "d");
		map.put(4, "e");
		map.put(5, "f");
		map.put(6, "g");
		map.put(7, "h");
		mapNumToStr.put("a", 0);
		mapNumToStr.put("b", 1);
		mapNumToStr.put("c", 2);
		mapNumToStr.put("d", 3);
		mapNumToStr.put("e", 4);
		mapNumToStr.put("f", 5);
		mapNumToStr.put("g", 6);
		mapNumToStr.put("h", 7);
		// instantiate pieces
		pieces.add(new Rook(true, "white-rook-l", "white", "♖"));
		pieces.add(new Knight(true, "white-knight-l", "white", "♘"));
		pieces.add(new Bishop(true, "white-bishop-l", "white", "♗"));
		pieces.add(new King(true, "white-king", "white", "♔"));
		pieces.add(new Queen(true, "white-queen", "white", "♕"));
		pieces.add(new Bishop(true, "white-bishop-r", "white", "♗"));
		pieces.add(new Knight(true, "white-knight-r", "white", "♘"));
		pieces.add(new Rook(true, "white-rook-r", "white", "♖"));
		for (int i = 0; i < 8; i++) {
			pieces.add(new Pawn(true, String.format("white-pawn-%s", i), "white", "♙"));
		}
		for (int i = 0; i < 8; i++) {
			pieces.add(new Pawn(true, String.format("black-pawn-%s", i), "black", "♟"));
		}
		pieces.add(new Rook(true, "black-rook-r", "black", "♜"));
		pieces.add(new Knight(true, "black-knight-r", "black", "♞"));
		pieces.add(new Bishop(true, "black-bishop-r", "black", "♝"));
		pieces.add(new King(true, "black-king", "black", "♚"));
		pieces.add(new Queen(true, "black-queen", "black", "♛"));
		pieces.add(new Bishop(true, "black-bishop-l", "black", "♝"));
		pieces.add(new Knight(true, "black-knight-l", "black", "♞"));
		pieces.add(new Rook(true, "black-rook-l", "black", "♜"));

		// set up pieces on board
		// numbers
		int pieceIndex = 0;
		for (int row = 0; row < 8; row++) {
			// letters
			for (int column = 0; column < 8; column++) {
				if (row == 0 || row == 1 || row == 6 || row == 7) {
					squares.add(new Square(row, map.get(column), this, pieces.get(pieceIndex)));
					pieces.get(pieceIndex).setPosition(squares.get(squares.size() - 1));
					pieceIndex++;
				} else {
					squares.add(new Square(row, map.get(column), this, null));
				}

			}
		}
		for (int i = 0; i < pieces.size(); i++) {
			System.out.println(pieces.get(i).getName() + "   " + pieces.get(i).getPosition().getX() + "    "
					+ pieces.get(i).getPosition().getY());
		}
//		System.out.println(pieces.get(17).getName() +pieces.get(17).getPosition().getX() +pieces.get(17).getPosition().getY() );
//		pieces.get(17).move(giveMeSquare("b", 4));
//		pieces.get(17).move(giveMeSquare("b", 3));
//		pieces.get(17).move(giveMeSquare("b", 2));
//		pieces.get(17).move(giveMeSquare("c", 1));
//		System.out.println(pieces.get(17).getName() +pieces.get(17).getPosition().getX() +pieces.get(17).getPosition().getY() );
//		pieces.get(1).move(giveMeSquare("c", 2));
		// one up
//		pieces.get(14).move(giveMeSquare("g", 3));
		// two up
//		pieces.get(14).move(giveMeSquare("g", 4));
		// one backwards
//		giveMePiece("white-pawn", "g", 1).move(giveMeSquare("g", 0));
		// stays on same
//		giveMePiece("white-pawn", "g", 1).move(giveMeSquare("g", 1));
		// tries to eat attack its empty
//		giveMePiece("white-pawn-").move(giveMeSquare("f", 2));
//		System.out.println(pieces.get(1).getName() +pieces.get(1).getPosition().getX() +pieces.get(1).getPosition().getY() );
//		System.out.println(giveMeSquare("c", 2).getPiece().getName());
//		System.out.println("is alive " + pieces.get(17).isAlive());
//		System.out.println(giveMePiece("black-bishop-r").getName() + giveMePiece("black-bishop-r").getPosition().getX() +giveMePiece("black-bishop-r").getPosition().getY() );
//		giveMePiece("black-queen").move(giveMeSquare("d", 7));
//		giveMePiece("black-queen").move(giveMeSquare("f", 7));
//		giveMePiece("black-queen").move(giveMeSquare("e", 6));

//		giveMePiece("black-queen").move(giveMeSquare("e", 6));
//		giveMePiece("black-bishop-r").move(giveMeSquare("a", 5));
//		System.out.println(giveMePiece("black-bishop-r").getName() + giveMePiece("black-bishop-r").getPosition().getX() + giveMePiece("black-bishop-r").getPosition().getY() );
//		giveMePiece("black-bishop-r").move(giveMeSquare("c", 7));
//		System.out.println(giveMePiece("black-bishop-r").getName() + giveMePiece("black-bishop-r").getPosition().getX() + giveMePiece("black-bishop-r").getPosition().getY() );
//
//		giveMePiece("black-pawn-3").move(giveMeSquare("d", 4));
//		giveMePiece("black-king").move(giveMeSquare("d", 6));
//		giveMePiece("black-king").move(giveMeSquare("d", 5));
//		giveMePiece("black-king").move(giveMeSquare("c", 5));
//		giveMePiece("black-king").move(giveMeSquare("c", 4));
//		giveMePiece("black-king").move(giveMeSquare("c", 3));
//		giveMePiece("black-king").move(giveMeSquare("d", 4));
//		giveMePiece("black-king").move(giveMeSquare("c", 1));
//
//		giveMePiece("black-pawn-1").move(giveMeSquare("b", 3));
//		giveMePiece("black-bishop-r").move(giveMeSquare("e", 1));
//		giveMePiece("black-bishop-r").move(giveMeSquare("h", 2));
//		giveMePiece("black-bishop-r").move(giveMeSquare("c", 7));
//		giveMePiece("black-bishop-r").move(giveMeSquare("f", 4));
//		giveMePiece("black-bishop-r").move(giveMeSquare("d", 2));
//		giveMePiece("black-bishop-r").move(giveMeSquare("e", 3));
//		System.out.println(giveMePiece("black-bishop-r").getName() + giveMePiece("black-bishop-r").getPosition().getX() + giveMePiece("black-bishop-r").getPosition().getY() );
//		giveMePiece("black-rook-r").move(giveMeSquare("a", 1));
//		giveMePiece("black-rook-r").move(giveMeSquare("c", 1));
//		System.out.println(giveMePiece("black-rook-r").getName() + giveMePiece("black-rook-r").getPosition().getX() +giveMePiece("black-rook-r").getPosition().getY() );
//		giveMePiece("black-rook-r").move(giveMeSquare("c", 5));
//		System.out.println(giveMePiece("black-rook-r").getName() + giveMePiece("black-rook-r").getPosition().getX() +giveMePiece("black-rook-r").getPosition().getY() );
//		giveMePiece("black-rook-r").move(giveMeSquare("h", 5));
//		System.out.println(giveMePiece("black-rook-r").getName() + giveMePiece("black-rook-r").getPosition().getX() +giveMePiece("black-rook-r").getPosition().getY() );
//		giveMePiece("black-rook-r").move(giveMeSquare("h", 6));
//		System.out.println(giveMePiece("black-rook-r").getName() + giveMePiece("black-rook-r").getPosition().getX() +giveMePiece("black-rook-r").getPosition().getY() );
//		giveMePiece("black-rook-r").move(giveMeSquare("h", 5));
//		System.out.println(giveMePiece("black-rook-r").getName() + giveMePiece("black-rook-r").getPosition().getX() +giveMePiece("black-rook-r").getPosition().getY() );
//		giveMePiece("black-rook-r").move(giveMeSquare("a", 5));
//		System.out.println(giveMePiece("black-rook-r").getName() + giveMePiece("black-rook-r").getPosition().getX() +giveMePiece("black-rook-r").getPosition().getY() );
//		System.out.println(pieces.get(2).getName() + pieces.get(2).getPosition().getX() +pieces.get(2).getPosition().getY() );
//		pieces.get(11).move(giveMeSquare("d", 2));
//		pieces.get(2).move(giveMeSquare("f", 3));
//		System.out.println(pieces.get(11).getName() + pieces.get(11).getPosition().getX() +pieces.get(11).getPosition().getY() );
//		System.out.println(pieces.get(2).getName() + pieces.get(2).getPosition().getX() +pieces.get(2).getPosition().getY() );
//		System.out.println(giveMePiece("white-knight-r").getName() + giveMePiece("white-knight-r").getPosition().getX() +giveMePiece("white-knight-r").getPosition().getY() );
//		giveMePiece("white-knight-r").move(giveMeSquare("e", 4));
//		System.out.println(giveMePiece("white-knight-r").getName() + giveMePiece("white-knight-r").getPosition().getX() +giveMePiece("white-knight-r").getPosition().getY() );
//		giveMePiece("black-knight-l").move(giveMeSquare("g", 3));
//		System.out.println(giveMePiece("black-knight-l").getName() + giveMePiece("black-knight-l").getPosition().getX() +giveMePiece("black-knight-l").getPosition().getY() );
//		giveMePiece("black-knight-l").move(giveMeSquare("f", 1));
//		System.out.println(giveMePiece("black-knight-l").getName() + giveMePiece("black-knight-l").getPosition().getX() +giveMePiece("black-knight-l").getPosition().getY() );
//		System.out.println(giveMePiece("white-pawn-5").getName() + " Is alive: "+ giveMePiece("white-pawn-5").isAlive()+ "  Position: " + giveMePiece("white-pawn-5").getPosition()  );
		displayChessBoard();
	}

}
