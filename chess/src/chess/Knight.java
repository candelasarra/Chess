package chess;


public class Knight extends Piece{
	public Knight(boolean alive, String name, String color, String logo) {
		super(true, name, color, logo);
	}
	
	@Override
	//TODO check for out of bounds first when looping
	public boolean isValidMove(Square positionDst) {
		// TODO Auto-generated method stub
		if (squareIsOnAxis(positionDst)) {
			System.out.println("Knight only moves on L shape, cannot move straight up or down");
			return false;
		}
		int convertedTargetX = this.convertX(positionDst.getX());
		for(int i = 1; i < 3; i++) {
			//-2 i
			int moveYThisAmount = 3 - i;
			int topPairY = (this.getPosition().getY() + moveYThisAmount);
			int bottomPairY = (this.getPosition().getY() - moveYThisAmount);
			int shiftedRightX = this.getConvertedX() + i;
			int shiftedLeftX = this.getConvertedX() - i;
			if ( (convertedTargetX != shiftedLeftX) && (convertedTargetX != shiftedRightX) ) {
				continue;
			}
			if (topPairY == positionDst.getY() || bottomPairY == positionDst.getY()) {
				Piece toBeEaten = Board.getToBeEaten(positionDst);
				if (toBeEaten == null) {
					return true;
				} else {
					return eat(positionDst);
				}
				
			} 
		}
		return false;
	}

}
