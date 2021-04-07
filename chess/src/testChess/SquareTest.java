package testChess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Board;
import chess.Pawn;
import chess.Piece;
import chess.Square;


public class SquareTest {
    final private int y = 7;
    final private int yFail = 4;
    final private String x = "a";
    final private String xFail = "c";
	Board testBoard = new Board();
    Piece testPiece = new Pawn(false, "black-pawn-0", "black", "♟");
    Piece testFailPiece = new Pawn(false, "black-pawn-1", "black", "♟");
    Square instance = new Square(y, x, testBoard, testPiece);

    
    // think i don't need this..?
//	@BeforeEach
//	void init() {
//		testBoard = new Board();
//		instance = new Square(y, x, testBoard, null);
//		testPiece = new Pawn(false, "black-pawn-0", "black", "♟");
//		System.out.println("now");
//    }
// test constructor????
//	@Test
//	void testSquare() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testSetPiece() {
	    instance.setPiece(testPiece);
	    assertSame(testPiece, instance.getPiece(), "Square Test: get piece not working 1");
	    assertNotSame(testFailPiece, instance.getPiece(), "Square Test: get piece not working 2");
//		fail("Not yet implemented");
	}
	@Test
	void testGetPiece() {
	    assertSame(testPiece, instance.getPiece(), "Square Test: get piece not working 1");
//		fail("Not yet implemented");
	}


	@Test
	void testGetBoard() {
	    assertSame(testBoard, instance.getBoard(), "Square Test: get board not working 1");
//		fail("Not yet implemented");
	}
//
	@Test
	void testGetY() {
	    assertEquals(y, instance.getY(), "Square Test: get y not working 1");
	    assertNotEquals(yFail, instance.getY(), "Square Test: get y not working 2");
//		fail("Not yet implemented");
	}
//
	@Test
	void testGetX() {
	    assertEquals(x, instance.getX(), "Square Test: get x not working 1");
	    assertNotEquals(xFail, instance.getX(), "Square Test: get x not working 2");
//		fail("Not yet implemented");
	}

}
