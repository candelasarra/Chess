package chess;

public class King extends Piece {
	public King(boolean alive, String name, String color, String logo) {
		super(true, name, color, logo);
	}
//	public boolean isOutOfBounds(Square positionDst) {
//		int yBoundUp = this.getPosition().getY() + 1;
//		int xBoundUp = this.getConvertedX() + 1;
//		int xBoundDown = this.getConvertedX() - 1;
//		int yBoundDown = this.getPosition().getY() - 1;
//		int xTargetConverted = this.convertX(positionDst.getX());
//		return xTargetConverted < xBoundDown || xTargetConverted > xBoundUp || positionDst.getY() > yBoundUp || positionDst.getY() < yBoundDown;
//	}
	@Override
	public boolean isValidMove(Square positionDst) {
		// TODO Auto-generated method stub
//		if (isOutOfBounds(positionDst)) {
//			System.out.println("out of bounds of king");
//			return false;
//		} 
//		Piece toBeEaten = Board.getToBeEaten(positionDst);
//		if (toBeEaten != null) {
//			return eat(positionDst);
//		}
//		return true;
		return Utils.moveLikeKing(positionDst, this);
	}
}
