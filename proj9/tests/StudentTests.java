/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized  
 * assistance on this assignment
 */

package tests;

import org.junit.*;

import wordFrequencyCounter.WordFrequencyCounter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StudentTests {

	// tests that addOccurrence works correctly
	@Test public void testAddOccurrence() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		wfc.readWords(Arrays.asList("student-test3"));
		assertEquals(6, wfc.numWords());
		
		String[] words = {"i", "found", "an", "old", "apple", "product"};
		for(String word: words) {
			assertEquals(1, wfc.getOccurrences(word));
		}


	}
	// tests that readWords skips invalid filenames
	@Test public void testReadWords() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		wfc.readWords(Arrays.asList("student-test1", "student-tet5", 
				"public6-words2"));

		assertEquals(46, wfc.numWords());

	}

	// tests that readWords skips over null filenames
	@Test public void testReadWords2() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		wfc.readWords(Arrays.asList("student-test1", null, 
				"public6-words2"));

		assertEquals(46, wfc.numWords());


	}

	// tests readWords for when none of the filenames are valid
	@Test public void testReadWords3() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		wfc.readWords(Arrays.asList("student-tet1", null, 
				"public6-wors2", null));

		assertEquals(0, wfc.numWords());

	}

	// tests readWords when the parameter list is empty
	@Test public void testReadWords4() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		List<String> fileNames = new ArrayList<String>();
		
		wfc.readWords(fileNames);

		assertEquals(0, wfc.numWords());
	}

	// tests readWords for two calls to the method happens
	@Test public void testReadWords5() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		wfc.readWords(Arrays.asList("student-test1", null, 
				"public6-words2"));
		assertEquals(46, wfc.numWords());

		wfc.readWords(Arrays.asList("student-test2", 
				"public6-words1"));
		assertEquals(111, wfc.numWords());

	}

	// tests wordsWithOccurrences with an invalid parameter int
	@Test public void testWordsWithOccurrences() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		wfc.readWords(Arrays.asList("student-test3"));

		Set<String> occurrences = wfc.wordsWithOccurrences(-1);
		assertTrue(occurrences.isEmpty());

	}

	// tests wordsWithOccurrences with an empty wfc object
	@Test public void testWordsWithOccurrences1() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();

		Set<String> occurrences = wfc.wordsWithOccurrences(-1);
		assertTrue(occurrences.isEmpty());

	}

	// tests basic functionality of wordsWithOccurrences 
	@Test public void testWordsWithOccurrences2() {
		WordFrequencyCounter wfc= new WordFrequencyCounter();
		
		wfc.readWords(Arrays.asList("student-test3"));

		Set<String> occurrences = wfc.wordsWithOccurrences(1);
		assertEquals(6, occurrences.size());

	}


}


