package chess;

public class Pawn extends Piece {
	private boolean firstMoveDone = false;
	public Pawn(boolean alive, String name, String color, String logo) {
		super(true, name, color, logo);
	}
	public int getMeRightY() {
		if (this.getColor() == "white") {
			return this.getPosition().getY() + 1;
		} else {
			return this.getPosition().getY() - 1;
		}
	}
	public boolean ableToAttack(Square positionDst) {
		if (positionDst.getY() != getMeRightY()) {
			return false;
		}
		int rightX = this.getConvertedX() - 1;
		int leftX = this.getConvertedX() + 1;
		int destinationX = this.convertX(positionDst.getX());
		if (destinationX != rightX && destinationX != leftX ) {
			return false;
		}
		return true;
	}
	@Override
	public boolean isValidMove(Square positionDst) {
		int movedOneSpot =  Math.abs(positionDst.getY() - this.getPosition().getY());
		//if moving backwards
		if ( (this.iAmBlack() && (positionDst.getY() >= this.getPosition().getY()) ) ||   (this.iAmWhite() && (positionDst.getY() <= this.getPosition().getY()) )  ) {
			System.out.println("You cant move the pawn backwards");
			return false;
		}
		//if moving a forbidden amount of spots
		if ((firstMoveDone && (movedOneSpot != 1)) ||
			(!firstMoveDone && ( movedOneSpot > 2) )){
			System.out.println("something's wrong");
			return false;
		}
		if(ableToAttack(positionDst)) {
		//attacking
			Piece destinationSquareContent = Board.whatIsInSquare(positionDst.getX(), positionDst.getY());
			if (destinationSquareContent != null) {
				//return true to move piece only if it is able to eat it else dont move it
               //return eat(positionDst) ? true : false;
				boolean didEat = eat(positionDst);
				if (didEat && !firstMoveDone) {
					firstMoveDone = true;
				}
				return didEat;

			} else {
				System.out.println("Cannot attack empty square");
				return false;
			}
		} else {
		//moving forward	
			//if trying to move diagonally
			if (positionDst.getX() != this.getPosition().getX()) {
				System.out.println("Can only move diagonally when attacking");
				return false;
			}
			int start = this.iAmWhite()? getMeRightY() : positionDst.getY();
			int end = this.iAmWhite()? positionDst.getY() : getMeRightY();
			for(int i = start; i <= end; i++) {
				if (Board.whatIsInSquare(positionDst.getX(), positionDst.getY()) != null) {
					System.out.println("other pieces on the way of pawn");
					return false;
				}
			}
		}
		if (!firstMoveDone) {
			firstMoveDone = true;
		}
		return true;
	}
}
