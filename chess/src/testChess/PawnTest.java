package testChess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import chess.Board;
import chess.Piece;
import chess.Square;

class PawnTest {
	static Board testBoard = new Board();
	static Piece instance;
	static Square square;
	@BeforeAll
	static void setUp() {
		testBoard.setUpBoard();
//		square = Board.giveMeSquare(x, y);
		instance = Board.giveMePiece("black-pawn-0");			
	}
//	@Test
//	void testIsValidMove() {
//		System.out.println(square.getX());
//	}

	@Test
	void testPawnMoveUpOnce() {
		String squareX = "a";
		int newSquareY = 5;
		int oldSquareY = 6;
		square = Board.giveMeSquare(squareX, newSquareY);
		assertSame(square.getPiece(), null, "Pawn Test: square is not null before moving pawn 1");
		instance.move(square);
		assertAll("Should return right position after moving once",
			    () -> assertEquals(newSquareY, instance.getPosition().getY(), "Pawn Test: moving once, piece Y is not expected"),
			    () -> assertEquals(squareX, instance.getPosition().getX(), "Pawn Test: moving once, piece X is not expected")
			);
	    assertSame(square.getPiece(), instance, "Pawn Test: get piece not working 1");
		assertSame(Board.giveMeSquare(squareX, oldSquareY).getPiece(), null, "Pawn Test:old square didnt get set to null after moving pawn");
	}

	@Test
	void testPawnMoveUpTwice() {
		String squareX = "a";
		int newSquareY = 4;
		int oldSquareY = 6;
		square = Board.giveMeSquare(squareX, newSquareY);
		assertSame(square.getPiece(), null, "Pawn Test: square is not null before moving pawn 2");
		instance.move(square);
		assertAll("Should return right position after moving once",
			    () -> assertEquals(newSquareY, instance.getPosition().getY(), "Pawn Test: moving once, piece Y is not expected"),
			    () -> assertEquals(squareX, instance.getPosition().getX(), "Pawn Test: moving once, piece X is not expected")
			);
	    assertSame(square.getPiece(), instance, "Pawn Test: get piece not working 1");
		assertSame(Board.giveMeSquare(squareX, oldSquareY).getPiece(), null, "Pawn Test:old square didnt get set to null after moving pawn");
		Square failAtMovingTwice = Board.giveMeSquare(squareX, 2);
		assertFalse(instance.isValidMove(failAtMovingTwice), "Pawn Test: can only move two spots at a time once in the game");
		instance.move(failAtMovingTwice);
		assertSame(failAtMovingTwice.getPiece(), null, "Pawn Test: square should be null becuase I shouldnt have been able to move the pawn");
		assertSame(instance.getPosition(), square, "Pawn Test: pawn should be same as square since I shouldnt have been able to move it to new square");
	}
//	
//	@Test
//	void testPawnMoveDown() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testPawnMoveRight() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testPawnMoveLeft() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testGetMeRightY() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAbleToAttack() {
//		fail("Not yet implemented");
//	}

}
