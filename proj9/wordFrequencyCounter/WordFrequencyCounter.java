

package wordFrequencyCounter;

import java.util.Set;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class WordFrequencyCounter {

	private HashMap<String, Integer> words = 
			new HashMap<String, Integer>();

	public synchronized void addWordOccurrence(String word) {
		if (word == null) {
			throw new IllegalArgumentException();
		}

		boolean containsLetterOrDigit = false;
		for (Character c : word.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				containsLetterOrDigit = true;
			}
		}

		if (word.isEmpty() || containsLetterOrDigit == false) {
			return;
		}

		while (Character.isLetterOrDigit(word.charAt(word.length() - 1)) 
															== false) {
			word = word.substring(0, word.length() - 1);
		}
		word = word.toLowerCase();

		if (!words.containsKey(word)) {
			words.put(word, 1);

		} else {
			int val = words.get(word);
			words.put(word, ++val);

		}
	}

	public int numWords() {
		return words.size();
	}

	public int getOccurrences(String word) {
		if (word == null) {
			throw new IllegalArgumentException();
		}

		if (!words.containsKey(word))
			return 0;

		return words.get(word);
	}

	public boolean removeWordOccurrence(String word) {
		if (word == null) {
			throw new IllegalArgumentException();
		}

		if (!words.containsKey(word))
			return false;

		int num = words.get(word);
		num--;

		if (num > 0) {
			words.put(word, num);
		} else {
			words.remove(word);
		}

		return true;

	}

	public Set<String> wordsWithOccurrences(int occurrences) {

		Set<String> newWords = new HashSet<String>();

		if (!words.isEmpty()) {

			for (String word : words.keySet()) {
				if (words.get(word) == occurrences) {
					newWords.add(word);
				}
			}
		}

		return newWords;
	}

	public class FileName implements Runnable {

		String fileName;
		
		public FileName(String name) {
			this.fileName = name;
		}


		public void run() {
			File text = new File(fileName);

			try {
				
				Scanner input = new Scanner(text);
				while (input.hasNext()) {
					addWordOccurrence(input.next());

				}
				input.close();

			} catch(FileNotFoundException e) {
				System.out.println("Invalid file name");
			}

		}
	}

	public void readWords(List<String> fileNames)  {
		
		FileName newFile = null;

		ArrayList<Thread> fileThreads = new ArrayList<Thread>();
		
		for(String file : fileNames) {
			newFile = new FileName(file);
			fileThreads.add(new Thread(newFile));
			
		}
		
		for(Thread t : fileThreads) {
			t.start();
		}
		
		for(Thread t : fileThreads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception");
			}
		}
		
	}

}
