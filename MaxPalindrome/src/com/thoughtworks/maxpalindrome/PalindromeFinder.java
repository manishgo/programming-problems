package com.thoughtworks.maxpalindrome;

public class PalindromeFinder {

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
			return getCalculatedPalindrome(palindrome, pair, calculatedLength);
		} 
		Palindrome originalPalindrome = palindrome.clone();
		Palindrome palindromeIncludingPair = getPalindromeWithPair(str, palindrome, pair);
		Palindrome palindromeExcludingPair = findMaxPalindrome(str, originalPalindrome, startIndex+1, endIndex);
		Palindrome longerPalindrome = getLongerPalindrome(palindromeIncludingPair, palindromeExcludingPair);
		if(longerPalindrome.getLength() > maxPalindrome.getLength()) {
			maxPalindrome = longerPalindrome;
		}
		return longerPalindrome;
	}


	private Palindrome getPalindromeWithPair(String str, Palindrome palindrome, PalindromeIndexPair pair) {
		palindrome.addPalindromeIndexPair(pair);
		PalindromeIndexPair innerMostRegion = palindrome.getInnerMostRegion();
		return innerMostRegion!=null ? findMaxPalindrome(str, palindrome, innerMostRegion.getHeadIndex(), innerMostRegion.getTailIndex()) : palindrome;
	}


	private Palindrome getCalculatedPalindrome(Palindrome palindrome,
			PalindromeIndexPair pair, int calculatedLength) {
		int maxLength = palindrome.getLength() + calculatedLength;
		if(maxLength > maxPalindrome.getLength()) {
			maxPalindrome = palindrome.add(maxPalindrome.getSubPalindrome(pair));
			return maxPalindrome;
		}
		return palindrome;
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
}
