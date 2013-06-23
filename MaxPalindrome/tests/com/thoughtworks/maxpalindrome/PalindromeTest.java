package com.thoughtworks.maxpalindrome;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PalindromeTest {

	private static final String inputString = "mafdfam";
	private Palindrome palindrome;

	@Before
	public void setup() {
		palindrome = new Palindrome(inputString);
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
	public void shouldReturnPalindromeLengthStartingFromGivenPair() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		
		assertEquals(3, palindrome.getLength(getPair(1,5)));
	}

	@Test
	public void shouldReturnPalindromeLengthAsZeroStartingFromNonExistingPair() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		
		assertEquals(0, palindrome.getLength(getPair(3,3)));
	}

	@Test
	public void shouldReturnPalindromeLengthAsOneStartingFromInnerMostSinglePair() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		
		assertEquals(1, palindrome.getLength(getPair(3,3)));
	}

	@Test
	public void shouldReturnPalindromeLengthAsTwoStartingFromInnerMostNonSinglePair() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		
		assertEquals(2, palindrome.getLength(getPair(1,5)));
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
	
	@Test
	public void shouldReturnSubPalindromeGivenValidStartingPair() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		
		Palindrome subPalindrome = palindrome.getSubPalindrome(getPair(1,5));
		
		assertEquals("ada",subPalindrome.toString());
	}
	
	@Test
	public void shouldReturnEmptySubPalindromeGivenInvalidStartingPair() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		
		Palindrome subPalindrome = palindrome.getSubPalindrome(getPair(1,5));
		
		assertEquals("",subPalindrome.toString());
	}
	
	@Test
	public void shouldAddTwoNonIntersectingPalindromes() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		
		
		Palindrome anotherPalindrome = new Palindrome(inputString);
		anotherPalindrome.addPalindromeIndexPair(getPair(3, 3));
		
		Palindrome resultPalindrome = palindrome.add(anotherPalindrome);
		
		assertEquals("madam", resultPalindrome.toString());
	}

	@Test
	public void shouldAddTwoIntersectingPalindromes() {
		palindrome.addPalindromeIndexPair(getPair(0, 6));
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		
		Palindrome anotherPalindrome = new Palindrome(inputString);
		anotherPalindrome.addPalindromeIndexPair(getPair(2, 4));
		anotherPalindrome.addPalindromeIndexPair(getPair(3, 3));
		
		Palindrome resultPalindrome = palindrome.add(anotherPalindrome);
		
		assertEquals("mafdfam", resultPalindrome.toString());
	}

	@Test
	public void shouldPreferFirstPalindromeWhileAddingTwoConflictingPalindromes() {
		palindrome.addPalindromeIndexPair(getPair(1, 5));
		palindrome.addPalindromeIndexPair(getPair(3, 3));
		
		Palindrome anotherPalindrome = new Palindrome(inputString);
		anotherPalindrome.addPalindromeIndexPair(getPair(2, 2));
		anotherPalindrome.addPalindromeIndexPair(getPair(0, 6));
		
		Palindrome resultPalindrome = palindrome.add(anotherPalindrome);
		
		assertEquals("madam", resultPalindrome.toString());
	}
	
	private PalindromeIndexPair getPair(int headIndex, int tailIndex) {
		return new PalindromeIndexPair(headIndex, tailIndex);
	}
}
