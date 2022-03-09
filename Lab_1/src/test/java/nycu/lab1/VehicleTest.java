package nycu.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

class VehicleTest {
	private Vehicle vehicle;

	@BeforeEach
	void setup() {
		this.vehicle = new Vehicle();
	}

	@AfterEach
	void tearDown() {
		this.vehicle.finalize();
		this.vehicle = null;
	}

	@Test
	void testInit() {
		assertEquals(0, vehicle.getSpeed());
		assertEquals("north", vehicle.getDir());
	}

	@Test
	void testInitWithParam() {
		Vehicle vehicle_ = new Vehicle(10, "Hi");
		
		assertEquals(10, vehicle_.getSpeed());
		assertEquals("Hi", vehicle_.getDir());

		vehicle_ = null;
	}

	@Test
	void testFinalize() throws InterruptedException {
		this.vehicle.finalize();
		assertEquals(0, Vehicle.totalVehicle());
	}

	@Test
	void testGetSpeed() {
		assertEquals(0, this.vehicle.getSpeed());
	}

	@Test
	void testGetDir() {
		assertEquals("north", this.vehicle.getDir());
	}

	@Test
	void testSetSpeed() {
		this.vehicle.setSpeed(10);
		assertEquals(10, this.vehicle.getSpeed());
	}

	@Test
	void testSetDir() {
		this.vehicle.setDir("hello");
		assertEquals("hello", this.vehicle.getDir());
	}
}
