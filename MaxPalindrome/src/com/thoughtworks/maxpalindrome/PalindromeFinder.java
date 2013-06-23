package com.thoughtworks.maxpalindrome;

import java.util.ArrayList;
import java.util.TreeMap;

public class PalindromeFinder {

	private int maxLength;
	private Palindrome maxPalindrome;
	
	public String findMaxPalindrome(String str) {
		if(str.isEmpty() || str == null) {
			return str;
		}
		maxPalindrome = new Palindrome(str);
		findMaxPalindrome(str, maxPalindrome,0, str.length() - 1);
		
		return maxPalindrome.toString();
		
	}
	
	
	private Palindrome findMaxPalindrome(String str, Palindrome palindrome, int startIndex, int endIndex) {
		if(endIndex < startIndex) {
			return palindrome;
		}
		PalindromeIndexPair pair = findPair(str, startIndex, endIndex);
		int calculatedLength = maxPalindrome.getLength(pair);
		if(calculatedLength != 0) {
			int maxLength = palindrome.getLength() + calculatedLength;
			if(maxLength > maxPalindrome.getLength()) {
				maxPalindrome = palindrome.add(maxPalindrome.getSubPalindrome(pair));
				return maxPalindrome;
			}
			return palindrome;
		} 
		Palindrome originalPalindrome = palindrome.clone();
		palindrome.addPalindromeIndexPair(pair);
		PalindromeIndexPair innerMostRegion = palindrome.getInnerMostRegion();
		Palindrome palindromeIncludingPair = innerMostRegion!=null ? findMaxPalindrome(str, palindrome, innerMostRegion.getHeadIndex(), innerMostRegion.getTailIndex()) : palindrome;
		Palindrome palindromeExcludingPair = findMaxPalindrome(str, originalPalindrome,startIndex+1, endIndex);
		Palindrome longerPalindrome = getLongerPalindrome(palindromeIncludingPair, palindromeExcludingPair);
		if(longerPalindrome.getLength() > maxPalindrome.getLength()) {
			maxPalindrome = longerPalindrome;
		}
		return longerPalindrome;
//			int maxLengthExcludingPair = palindrome.getLength() + findMaximumPalindrome(str.substring(1), palindrome.clone());
//			int maxLengthIncludingPair = palindrome.getLength() + findMaximumPalindrome(newString, palindrome);
//			maxLength = Math.max(maxLengthExcludingPair, maxLengthIncludingPair);
	}
	
	private Palindrome getLongerPalindrome(Palindrome palindrome1, Palindrome palindrome2) {
		
		return palindrome1.getLength()>= palindrome2.getLength() ? palindrome1 : palindrome2;
	}


	private PalindromeIndexPair findPair(String str, int startIndex, int endIndex) {
		char ch = str.charAt(startIndex);
		int tailIndex = findFarthestIndex(str, ch, startIndex, endIndex);
		return new PalindromeIndexPair(startIndex, tailIndex);
	}
	
	private int findFarthestIndex(String str, char ch, int startIndex, int endIndex) {
		for(int i = endIndex; i>=startIndex; i--) {
			if(str.charAt(i) == ch) {
				return i;
			}
		}
		throw new IllegalStateException();
	}
	
	private PalindromeIndexPair getPair(int headIndex, int tailIndex) {
		return new PalindromeIndexPair(headIndex, tailIndex);
	}

	

}
