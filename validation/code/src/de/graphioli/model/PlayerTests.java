package de.graphioli.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTests {

	@Test
	public void testName() {
		Player p1;
		try {
			p1 = new LocalPlayer("");
		}
		catch(IllegalArgumentException e) {
			p1 = new LocalPlayer("Bob");
		}
		assertEquals(p1.getName(), "Bob");
	}
	
	@Test
	public void testEquals() {
		Player p1 = new LocalPlayer("Bob");
		Player p2 = null;
		Object o = new Object();
		
		assertTrue(p1.equals(p1));
		assertFalse(p1.equals(p2));
		assertFalse(p1.equals(o));
		p2 = new LocalPlayer("Bob");
		assertFalse(p2.equals(p1));
		p2 = new LocalPlayer("Alice");
		assertFalse(p2.equals(p1));
	}
	
	@Test
	public void testHashCode() {
		Player p1 = new LocalPlayer("Bob");
		
		int expectedResult = 31 + p1.getUID().hashCode();
		assertEquals(p1.hashCode(), expectedResult);
	}
	

}
