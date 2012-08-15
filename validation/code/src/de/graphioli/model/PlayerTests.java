package de.graphioli.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTests {

	@Test
	public void equals() {
		Player p1 = new LocalPlayer("Bob");
		Player p2 = null;
		Object o = new Object();
		
		assertTrue(p1.equals(p1));
		assertFalse(p1.equals(p2));
		assertFalse(p1.equals(o));
		//assertFalse(p2.equals(p1));
		p2 = new LocalPlayer("Alice");
		assertFalse(p2.equals(p1));
	}

}
