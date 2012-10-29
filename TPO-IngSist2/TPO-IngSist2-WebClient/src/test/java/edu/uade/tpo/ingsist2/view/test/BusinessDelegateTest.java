package edu.uade.tpo.ingsist2.view.test;

import org.junit.Test;

import edu.uade.tpo.ingsist2.view.bd.BusinessDelegate;

import junit.framework.TestCase;

public class BusinessDelegateTest extends TestCase {

	@Test
	public void testConnection(){
		BusinessDelegate bd = new BusinessDelegate();
		
		assertTrue(bd.isConnectionWorking());
	}
}
