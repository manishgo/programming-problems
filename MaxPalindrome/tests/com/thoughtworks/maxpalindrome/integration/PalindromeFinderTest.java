package com.thoughtworks.maxpalindrome.integration;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.thoughtworks.maxpalindrome.PalindromeFinder;

@RunWith(value = Parameterized.class)
public class PalindromeFinderTest {
	
	private PalindromeFinder palindromeFinder;
	private String input;
	private String expected;
	
	public PalindromeFinderTest(String expected, String input) {
		this.input = input;
		this.expected = expected;
	}
	
	@Before
	public void setup() {
		palindromeFinder = new PalindromeFinder();
	}
	
	@Parameters
	 public static Collection<Object[]> data() {
	   Object[][] data = getTestCases();
	   return Arrays.asList(data);
	 }
	 
	 private static String[][] getTestCases() {
		 return new String[][] {
				 { "", "" }, 
				 { "a", "a" }, 
				 { "a", "ab" },
				 { "aa", "aa" },
				 { "aba", "aba" },
				 { "aba", "abca" },
				 { "acca", "abcca" },
				 { "acca", "abccda" },
				 { "abccba", "abccba" },
				 { "bcb", "bcbda" },
				 { "bcacb", "abceacdb" }
		 };
	 }
	
	@Test
	public void shouldGetPalindromeOfMaxLength() {
		assertEquals(expected, palindromeFinder.findMaxPalindrome(input));
	}

}
