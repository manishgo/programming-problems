package com.thoughtworks.maxpalindrome;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Palindrome implements Cloneable {
	private String originalString;
	private SortedSet<PalindromeIndexPair> palindromeCharIndexes;
	
	public Palindrome(String str) {
		originalString = str.toLowerCase();
		palindromeCharIndexes = new TreeSet<PalindromeIndexPair>();
	}
	
	public void addPalindromeIndexPair(PalindromeIndexPair pair) {
		int headIndex = pair.getHeadIndex();
		int tailIndex = pair.getTailIndex();
		if(charAt(headIndex) != charAt(tailIndex)) {
			throw new IllegalArgumentException("Invalid pair of indexes. Cannot create a pair of the given indexes");
		}
		palindromeCharIndexes.add(pair);
	}
	
	public void removePalindromeIndex(int index) {
		palindromeCharIndexes.remove(index);
	}
	
	
	public int getLength() {
		return calculateLength(palindromeCharIndexes);
	}

	
	public int getLength(PalindromeIndexPair pair) {
		if(!palindromeCharIndexes.contains(pair)) {
			return 0;
		}
		SortedSet<PalindromeIndexPair> indexPairs = palindromeCharIndexes.tailSet(pair);
		return calculateLength(indexPairs);
	}

	public PalindromeIndexPair getInnerMostRegion() {
		
		if(!doesInnerRegionExist()) {
			return null;
		}
		
		PalindromeIndexPair innerMostPair = palindromeCharIndexes.last();
		int startingIndex  = innerMostPair.getHeadIndex() + 1;
		int endingIndex = innerMostPair.getTailIndex() - 1;
		return new PalindromeIndexPair(startingIndex, endingIndex);
	}
	
	public Palindrome getSubPalindrome(PalindromeIndexPair startingPair) {
		Palindrome palindrome = new Palindrome(originalString);
		if(palindromeCharIndexes.contains(startingPair)) {
			addPairs(palindrome, palindromeCharIndexes.tailSet(startingPair));
		}
		return palindrome;
	}

	
	@Override
	public String toString() {
		StringBuilder palindrome = new StringBuilder();
		int j=0;
		Iterator<PalindromeIndexPair> iterator = palindromeCharIndexes.iterator();
		
		addCharactersFromIndexPairs(palindromeCharIndexes, iterator, palindrome);
		return palindrome.toString();
	}
	
	@Override
	protected Palindrome clone() throws RuntimeException {
		Palindrome clonedPalindrome = new Palindrome(originalString);
		clonedPalindrome.palindromeCharIndexes = new TreeSet<PalindromeIndexPair>(palindromeCharIndexes);
		return clonedPalindrome;
	}
	
	private int calculateLength(SortedSet<PalindromeIndexPair> indexPairs) {
		if(indexPairs == null || indexPairs.isEmpty()) {
			return 0;
		}
		int length = indexPairs.size() * 2;
		if(indexPairs.last().isSingle()) {
			length--;
		}
		return length;
	}

	private void addCharactersFromIndexPairs(SortedSet<PalindromeIndexPair> indexPairs, Iterator<PalindromeIndexPair> iterator, StringBuilder str) {
		if(iterator.hasNext()) {
			PalindromeIndexPair indexPair = iterator.next();
			str.append(charAt(indexPair.getHeadIndex()));
			addCharactersFromIndexPairs(indexPairs, iterator, str);
			if(!indexPair.isSingle()) {
				str.append(charAt(indexPair.getTailIndex()));
			}
		}
	}

	private boolean doesInnerRegionExist() {
		if(palindromeCharIndexes.isEmpty()) {
			return false;
		}
		PalindromeIndexPair innerMostPair = palindromeCharIndexes.last();
		return !(innerMostPair.isSingle() || innerMostPair.isAdjacent());
	}

	private char charAt(int index) {
		return originalString.charAt(index);
	}

	public Palindrome add(Palindrome anotherPalindrome) {
		if(anotherPalindrome.originalString.equals(originalString)) {
			Palindrome palindrome = new Palindrome(originalString);
			addPairs(palindrome, palindromeCharIndexes);
			addPairs(palindrome, anotherPalindrome.palindromeCharIndexes);
			return palindrome;
		}
		return null;
	}

	private void addPairs(Palindrome palindrome, SortedSet<PalindromeIndexPair> indexPairs) {
		for(PalindromeIndexPair pair: indexPairs) {
			palindrome.addPalindromeIndexPair(pair);
		}
	}

}
