package com.thoughtworks.maxpalindrome;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PalindromeIndexPairTest {
	private PalindromeIndexPair innerPair;
	private PalindromeIndexPair outerPair;

	@Before
	public void setup() {
		innerPair = new PalindromeIndexPair(3, 4);
		outerPair = new PalindromeIndexPair(1, 8);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRaiseExceptionIfHeadIsGreaterThanTail() {
		new PalindromeIndexPair(5,4);
	}
	
	@Test
	public void shouldReturnPositiveIfInnerPairIsBeingComparedToOuterPair() {
		assertEquals(1,innerPair.compareTo(outerPair));
	}
	
	@Test
	public void shouldReturnNegativeIfOuterPairIsBeingComparedToInnerPair() {
		assertEquals(-1,outerPair.compareTo(innerPair));
	}
	
	@Test
	public void shouldReturnZeroIfPairsArePartiallyOverlapping() {
		PalindromeIndexPair pair1 = new PalindromeIndexPair(3, 5);
		PalindromeIndexPair pair2 = new PalindromeIndexPair(4, 8);
		
		assertEquals(0, pair1.compareTo(pair2));
		assertEquals(0, pair2.compareTo(pair1));
	}
	
	@Test
	public void shouldReturnZeroIfPairsAreFullyNonOverlapping() {
		PalindromeIndexPair pair1 = new PalindromeIndexPair(3, 5);
		PalindromeIndexPair pair2 = new PalindromeIndexPair(6, 8);
		
		assertEquals(0, pair1.compareTo(pair2));
		assertEquals(0, pair2.compareTo(pair1));
	}
	
	@Test
	public void shouldIndicateSingleIfHeadAndTailAreSame() {
		PalindromeIndexPair pair = new PalindromeIndexPair(5, 5);
		
		assertTrue(pair.isSingle());
	}
	
	@Test
	public void shouldIndicateNotSingleIfHeadAndTailAreSame() {
		PalindromeIndexPair pair = new PalindromeIndexPair(5, 6);
		
		assertFalse(pair.isSingle());
	}
	
	@Test
	public void shouldIndicateAdjacentIfHeadAndTailIndexDifferByOne() {
		PalindromeIndexPair pair = new PalindromeIndexPair(5, 6);
		
		assertTrue(pair.isAdjacent());
	}
	
	@Test
	public void shouldIndicateNotAdjacentIfHeadAndTailIndexDifferByMoreThanOne() {
		PalindromeIndexPair pair = new PalindromeIndexPair(5, 7);
		
		assertFalse(pair.isAdjacent());
	}
}
