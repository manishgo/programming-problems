package com.thoughtworks.maxpalindrome;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter any string ");
		String inputString = scanner.nextLine();

		PalindromeFinder palindromeFinder = new PalindromeFinder();
		String maxPalindrome = palindromeFinder.findMaxPalindrome(inputString);

		System.out.println("Maximum palindrome is " + maxPalindrome);
	}
}
