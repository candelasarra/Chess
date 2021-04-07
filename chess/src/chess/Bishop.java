package chess;

public class Bishop extends Piece {
	public Bishop(boolean alive, String name, String color, String logo) {
		super(true, name, color, logo);
	}
//	public boolean goingUpLeft(Square positionDst) {
//		return positionDst.getY() > this.getPosition().getY() && this.convertX(positionDst.getX()) < this.getConvertedX();
//	}
//	public boolean goingUpRight(Square positionDst) {
//		return positionDst.getY() > this.getPosition().getY() && this.convertX(positionDst.getX()) > this.getConvertedX();
//	}
//	public boolean goingDownLeft(Square positionDst) {
//		return positionDst.getY() < this.getPosition().getY() && this.convertX(positionDst.getX()) < this.getConvertedX();
//	}
//	public boolean goingDownRight(Square positionDst) {
//		return positionDst.getY() < this.getPosition().getY() && this.convertX(positionDst.getX()) > this.getConvertedX();
//	}
//	public boolean checkDiagonal(int xModifier, int yModifier, Square positionDst, Square lastCheck) {
//		int nextConvertedX = this.convertX(lastCheck.getX()) + (1 * xModifier);
//		String newX = Board.map.get(nextConvertedX);
//		int newY = lastCheck.getY() + (1 * yModifier);
//		Square newSquare = Board.giveMeSquare(newX, newY);
//		//last
//		if (!Board.isSquareWithinBoundaries(newSquare)) {
//			return false;
//		}
//		Piece newSquareContent = newSquare.getPiece();
//		//found!
//		if (newSquare.getX() == positionDst.getX() && newSquare.getY() == positionDst.getY()) {
//			if (newSquareContent != null) {
//				return eat(positionDst);
//			}
//			return true;
//		}
//
//		//call next
//		if (newSquareContent != null) {
//			System.out.println("Found " + newSquareContent.getName() + " on the way of " + this.getName());
//			return false;
//		}
//		return checkDiagonal(xModifier, yModifier, positionDst, newSquare);
//	}
	@Override
	public boolean isValidMove(Square positionDst) {
		return Utils.moveDiagonally(positionDst, this);
//		if (squareIsOnAxis(positionDst)) {
//			System.out.println("Bishop cannot move along x or y axis");
//			return false;
//		}
//		if (goingDownRight(positionDst)) {
//			return checkDiagonal(1, -1, positionDst, this.getPosition());
//		}
//		if (goingDownLeft(positionDst)) {
//			return checkDiagonal(-1, -1, positionDst, this.getPosition());
//		}
//		if (goingUpRight(positionDst)) {
//			return checkDiagonal(1, 1, positionDst, this.getPosition());
//		}
//		if (goingUpLeft(positionDst)) {
//			return checkDiagonal(-1, 1, positionDst, this.getPosition());
//		}
//		
//		return false;
	}

}
