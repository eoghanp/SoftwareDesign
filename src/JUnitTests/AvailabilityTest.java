package JUnitTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Data.DBHandler;
import Vehicle.Vehicle;



public class AvailabilityTest {

	@Test
	public void test() throws IOException {

		DBHandler isAvailable = new DBHandler();
		
		// No Error = Vehicles available in DP
		// Errors = No Vehicle available
		assertEquals(true, isAvailable.getAvailability(null));
		
	}
}
