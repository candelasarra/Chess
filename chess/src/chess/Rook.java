package chess;

public class Rook extends Piece {
	public Rook(boolean alive, String name, String color, String logo) {
		super(true, name, color, logo);
	}
	public boolean movingFoward(Square positionDst) {
		return this.convertX(positionDst.getX()) > this.getConvertedX();
	}
	public boolean movingUp(Square positionDst) {
		return positionDst.getY() > this.getPosition().getY();
	}
	@Override
	public boolean isValidMove(Square positionDst) {
		// TODO Auto-generated method stub
		int start;
		int end;
		//positionDst.getX() != this.getPosition().getX() && positionDst.getY() != this.getPosition().getY()
		if (squareIsNotOnAxis(positionDst) ) {
			System.out.println("Rook can only move up and down");
			return false;
		}
		//moving side to side
		if (positionDst.getY() == this.getPosition().getY()) {
			//moving 
				start = movingFoward(positionDst) ? this.getConvertedX() + 1 : this.convertX(positionDst.getX());
				end = movingFoward(positionDst) ? this.convertX(positionDst.getX()) : this.getConvertedX()- 1;
			for(int i = start; i <= end; i++) {
				Piece onTheWay = Board.whatIsInSquare(Board.map.get(i), this.getPosition().getY());
				if(onTheWay != null) {
					if (i == end) {
						//if checked entire path and didn't find any on the way, eat last
//						return eat(positionDst) ? true : false;
						return eat(positionDst);

					}
					//if piece on the way, and not currently on destination, cant move
					System.out.println("Found "+ onTheWay.getName() + " while moving accross x axis on the way of " + this.getName());
					return false;
				}
			}
		} else {
			//moving up and down because y's are different
			//starting one after the one I am on
			
			start = movingUp(positionDst)? this.getPosition().getY() + 1  : positionDst.getY();
			end = movingUp(positionDst)? positionDst.getY() : this.getPosition().getY() - 1;
			for(int i = start; i <= end; i++) {
				Piece onTheWay = Board.whatIsInSquare(this.getPosition().getX(), start);
				//checking if any pieces on the way
				if(onTheWay != null) {
					if ((this.iAmWhite() && i == end) || (this.iAmBlack() && i == start) ) {
						//if checked entire path and didn't find any on the way, eat last
						return eat(positionDst);
					}
					//if piece on the way, and not currently on destination, cant move

					System.out.println("Found "+ onTheWay.getName() + " while moving accross y axis on the way of " + this.getName());
					return false;
				}
			}
		}
		System.out.println("moved piece");
		return true;
	}

}
