package chess;

public class Square {
	private final int y;
	private final String x;
	final Board board;
	Piece piece;
	public Square(int y, String x, Board board, Piece piece) {
		super();
		this.x = x;
		this.y = y;
		this.board = board;
		this.piece = piece;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public Board getBoard() {
		return board;
	}
	public int getY() {
		return y;
	}
	public String getX() {
		return x;
	}

}
