package chess;

public abstract class Piece {
	private Square position;
	private boolean alive;
	private String name;
	private String color;
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Piece(boolean alive, String name, String color, String logo) {
		super();
		this.alive = alive;
		this.name = name;
		this.color = color;
		this.logo = logo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Square getPosition() {
		return position;
	}
	public void setPosition(Square position) {
		this.position = position;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public int convertX(String character) {
		return Board.mapNumToStr.get(character);
	}
	public int getConvertedX() {
		return Board.mapNumToStr.get(this.getPosition().getX());
	}
	public String revertX(int x) {
		return Board.map.get(x);
	}
	public boolean squareIsNotOnAxis(Square positionDst) {
		boolean itIsNotOnAxis = positionDst.getX() != this.getPosition().getX() && positionDst.getY() != this.getPosition().getY();
		return itIsNotOnAxis;
	}
	public boolean squareIsOnAxis(Square positionDst) {
		boolean itIsOnAxis = positionDst.getX() == this.getPosition().getX() || positionDst.getY() == this.getPosition().getY();
		return itIsOnAxis;
	}
	public boolean iAmWhite() {
		return this.getColor().equals("white");
	}
	public boolean iAmBlack() {
		return this.getColor().equals("black");
	}
	public int giveMeNumberRightDirection(int number) {
		if (this.getColor().equals("white")) {
			return number;
		} else {
			return number * (-1);
		}
	}
	public boolean isItMySquare(Square square) {
		return square.getX() == this.getPosition().getX() && square.getY() == this.getPosition().getY();
	}
	//TODO before is valid move, calculate possible spots so I can show hints
	public abstract boolean isValidMove(Square positionDst);
	//TODO check first if trying to move to same spot I am on
	public void move(Square positionDst) {
		if (!Board.isSquareWithinBoundaries(positionDst)) {
			System.out.println("Square out of boundaries");
			return;
		}
		if (isItMySquare(positionDst)) {
			System.out.println("You are already at that position");
			return;
		}
		if (isValidMove(positionDst)) {
			System.out.println("move valid");
			Board.giveMeSquare(this.getPosition().getX(), this.getPosition().getY()).setPiece(null);
			positionDst.setPiece(this);
			this.setPosition(positionDst);
		}
	}
	public boolean eat(Square positionDst) {
		Piece toBeEaten = Board.getToBeEaten(positionDst);
		if (toBeEaten == null) {
			System.out.println("BUG. Cannot eat from empty square!");
			return false;
		}
		if (toBeEaten.getColor() != this.getColor()) {
			toBeEaten.setAlive(false);
			toBeEaten.setPosition(null);
			positionDst.setPiece(null);
			System.out.println(toBeEaten.getName() + " eaten by " + this.getName());
			return true;
		} else {
			System.out.println("Cannot eat pieces of your own team! Both pieces are " + this.getColor() + " and " + toBeEaten.getColor());
			return false;
		}

	}
}
