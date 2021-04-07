package chess;

public class Utils {
	public static boolean goingUpLeft(Square positionDst, Piece piece) {
		return positionDst.getY() > piece.getPosition().getY() && piece.convertX(positionDst.getX()) < piece.getConvertedX();
	}
	public static boolean goingUpRight(Square positionDst, Piece piece) {
		return positionDst.getY() > piece.getPosition().getY() && piece.convertX(positionDst.getX()) > piece.getConvertedX();
	}
	public static boolean goingDownLeft(Square positionDst, Piece piece) {
		return positionDst.getY() < piece.getPosition().getY() && piece.convertX(positionDst.getX()) < piece.getConvertedX();
	}
	public static boolean goingDownRight(Square positionDst, Piece piece) {
		return positionDst.getY() < piece.getPosition().getY() && piece.convertX(positionDst.getX()) > piece.getConvertedX();
	}
	public static boolean checkDiagonal(int xModifier, int yModifier, Square positionDst, Square lastCheck, Piece piece) {
		int nextConvertedX = piece.convertX(lastCheck.getX()) + (1 * xModifier);
		String newX = Board.map.get(nextConvertedX);
		int newY = lastCheck.getY() + (1 * yModifier);
		Square newSquare = Board.giveMeSquare(newX, newY);
		//last
		if (!Board.isSquareWithinBoundaries(newSquare)) {
			return false;
		}
		Piece newSquareContent = newSquare.getPiece();
		//found!
		if (newSquare.getX() == positionDst.getX() && newSquare.getY() == positionDst.getY()) {
			if (newSquareContent != null) {
				return piece.eat(positionDst);
			}
			return true;
		}

		//call next
		if (newSquareContent != null) {
			System.out.println("Found " + newSquareContent.getName() + " on the way of " + piece.getName());
			return false;
		}
		return checkDiagonal(xModifier, yModifier, positionDst, newSquare, piece);
	}
	static boolean moveDiagonally(Square positionDst, Piece piece) {
		if (piece.squareIsOnAxis(positionDst)) {
			System.out.println("Bishop cannot move along x or y axis");
			return false;
		}
		if (goingDownRight(positionDst, piece)) {
			return checkDiagonal(1, -1, positionDst, piece.getPosition(), piece);
		}
		if (goingDownLeft(positionDst, piece)) {
			return checkDiagonal(-1, -1, positionDst, piece.getPosition(), piece);
		}
		if (goingUpRight(positionDst, piece)) {
			return checkDiagonal(1, 1, positionDst, piece.getPosition(), piece);
		}
		if (goingUpLeft(positionDst, piece)) {
			return checkDiagonal(-1, 1, positionDst, piece.getPosition(), piece);
		}
		return false;
	}
	public static boolean isOutOfBounds(Square positionDst, Piece piece) {
		int yBoundUp = piece.getPosition().getY() + 1;
		int xBoundUp = piece.getConvertedX() + 1;
		int xBoundDown = piece.getConvertedX() - 1;
		int yBoundDown = piece.getPosition().getY() - 1;
		int xTargetConverted = piece.convertX(positionDst.getX());
		return xTargetConverted < xBoundDown || xTargetConverted > xBoundUp || positionDst.getY() > yBoundUp || positionDst.getY() < yBoundDown;
	}
	static boolean moveLikeKing(Square positionDst, Piece piece) {
		if (isOutOfBounds(positionDst, piece)) {
			System.out.println("out of bounds of king");
			return false;
		} 
		Piece toBeEaten = Board.getToBeEaten(positionDst);
		if (toBeEaten != null) {
			return piece.eat(positionDst);
		}
		return true;
	}
}
