package com.thoughtworks.maxpalindrome;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PalindromeTest {

	private Palindrome palindrome;

	@Before
	public void setup() {
		palindrome = new Palindrome("mafdgam");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionGivenInvalidPalindromeIndexes() {
		palindrome.addPalindromeIndexPair(getPair(0, 5));
	}
	
	@Test
	public void shouldReturnOneCharacterGivenSamePalindromeIndexes() {
		palindrome.addPalindromeIndexPair(getPair(1,1));
		assertEquals("a", palindrome.toString());
	}
	
	@Test
	public void shouldReturnPalindromeCharactersInOrderGivenValidPalindromeIndexesInAnyOrder() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		assertEquals("madam", palindrome.toString());
	}
	
	@Test
	public void shouldReturnOddPalindromeLength() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		assertEquals(3, palindrome.getLength());
	}

	@Test
	public void shouldReturnIndexOfInnerRegionIfInnerMostPairIsNeitherSingleNorAdjacent() {
		PalindromeIndexPair pair = mock(PalindromeIndexPair.class);
		when(pair.isSingle()).thenReturn(false);
		when(pair.isAdjacent()).thenReturn(false);
		when(pair.getHeadIndex()).thenReturn(0);
		when(pair.getTailIndex()).thenReturn(6);
		palindrome.addPalindromeIndexPair(pair);
		
		PalindromeIndexPair innerMostRegion = palindrome.getInnerMostRegion();
		assertEquals(getPair(1, 5), innerMostRegion);
	}
	
	@Test
	public void shouldReturnNullAsInnerRegionIfInnerMostPairIsSingle() {
		PalindromeIndexPair pair = mock(PalindromeIndexPair.class);
		when(pair.isSingle()).thenReturn(true);
		palindrome.addPalindromeIndexPair(pair);
		
		PalindromeIndexPair innerMostRegion = palindrome.getInnerMostRegion();
		assertNull(innerMostRegion);
	}
	
	@Test
	public void shouldReturnNullAsInnerRegionIfInnerMostPairIsAdjacent() {
		PalindromeIndexPair pair = mock(PalindromeIndexPair.class);
		when(pair.isAdjacent()).thenReturn(true);
		palindrome.addPalindromeIndexPair(pair);
		
		PalindromeIndexPair innerMostRegion = palindrome.getInnerMostRegion();
		assertNull(innerMostRegion);
	}
	
	@Test
	public void shouldReturnNullAsInnerRegionIfThereIsNoPair() {
		PalindromeIndexPair innerMostRegion = palindrome.getInnerMostRegion();
		assertNull(innerMostRegion);
	}
	
	private PalindromeIndexPair getPair(int headIndex, int tailIndex) {
		return new PalindromeIndexPair(headIndex, tailIndex);
	}
}
