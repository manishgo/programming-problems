package com.thoughtworks.maxpalindrome;

public class PalindromeIndexPair implements Comparable<PalindromeIndexPair>{
	int headIndex;
	int tailIndex;
	public PalindromeIndexPair(int head, int tail) {
		if(tail < head) {
			throw new IllegalArgumentException("Head cannot be greater than tail");
		}
		headIndex = head;
		tailIndex = tail;
	}
	
	public int getHeadIndex() {
		return headIndex;
	}
	
	public int getTailIndex() {
		return tailIndex;
	}
	
	public boolean isSingle() {
		return headIndex == tailIndex;
	}

	public boolean isAdjacent() {
		return tailIndex - headIndex == 1;
	}
	
	@Override
	public int compareTo(PalindromeIndexPair otherPair) {
		if(headIndex > otherPair.headIndex && tailIndex < otherPair.tailIndex) {
			return 1;
		} else if (headIndex < otherPair.headIndex && tailIndex > otherPair.tailIndex) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof PalindromeIndexPair)) {
			return false;
		}
		PalindromeIndexPair otherPair = (PalindromeIndexPair) obj;
		return otherPair.headIndex == headIndex && otherPair.tailIndex == tailIndex;
	}
	
	@Override
	public int hashCode() {
		return headIndex + tailIndex;
	}
}
