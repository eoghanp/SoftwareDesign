package JUnitTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Data.DBHandler;



public class SeatsTest {

	@Test
	public void test() throws IOException {

		DBHandler SeatNum = new DBHandler();
		
		// No Error if 2, 5 or 7
		// Error for every other number
		assertEquals(5, SeatNum.numberOfSeats(null));
		
	}
}
