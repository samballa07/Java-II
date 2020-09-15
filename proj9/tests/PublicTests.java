package tests;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import wordFrequencyCounter.WordFrequencyCounter;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // Tests the basic operation of addWordOccurrence(), numWords(), and
  // getOccurrences().  Note that this test does not create or use any
  // threads.
  @Test public void testPublic1() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] words= {"dolphins", "are", "highly", "social", "animals",
                     "often", "living", "in", "pods", "of", "up", "to", "a",
                     "dozen", "members", "though", "sizes", "and",
                     "structures", "vary", "greatly", "they", "can",
                     "establish", "strong", "bonds", "even", "helping",
                     "injured", "or", "ill", "individuals", "by",
                     "bringing", "them", "towards", "the", "surface",
                     "when", "help", "is", "needed"};

    // add the words in the array
    for (String word : words)
      wfc.addWordOccurrence(word);

    // test the total number of words
    assertEquals(42, wfc.numWords());

    // test the occurrences of each word
    for (String word : words) 
      	assertEquals(1, wfc.getOccurrences(word));
    

    // test the occurrences of a word that is not in the current object
    assertEquals(0, wfc.getOccurrences("whale"));
  }

  // Tests the basic operation of removeWordOccurrence().  Note that this
  // test does not create or use any threads.
  @Test public void testPublic2() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] words= {"Java", "loves", "you", "Java", "loves", "me", "Java",
                     "longs", "for", "us", "all", "to", "be", "very",
                     "very", "happy", "so", "everyone", "join", "in",
                     "thanking", "Java", "today"};
    String[] uniqueWords= {"all", "be", "everyone", "for", "happy", "in",
                           "java", "join", "longs", "loves", "me", "so",
                           "thanking", "to", "today", "us", "very", "you"};
    int[] expectedOccurrences= {1, 1, 1, 1, 1, 1, 4, 1, 1, 2, 1, 1, 1, 1,
                                1, 1, 2, 1};
    int[] expectedOccurrences2= {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 1, 1, 1,
                                 0, 1, 2, 1};
    int i;

    // add the words in the array
    for (String word : words)
      wfc.addWordOccurrence(word);

    // test the total number of unique words
    assertEquals(18, wfc.numWords());

    // test the occurrences of each word using corresponding elements of the
    // array of unique words and expected occurrences of each one
    for (i= 0; i < uniqueWords.length; i++)
      assertEquals(expectedOccurrences[i],
                   wfc.getOccurrences(uniqueWords[i]));

    // remove a few words
    wfc.removeWordOccurrence("all");
    wfc.removeWordOccurrence("java");
    wfc.removeWordOccurrence("me");
    wfc.removeWordOccurrence("java");
    wfc.removeWordOccurrence("today");

    // test the occurrences of each word now
    for (i= 0; i < uniqueWords.length; i++)
      assertEquals(expectedOccurrences2[i],
                   wfc.getOccurrences(uniqueWords[i]));
  }

  // Tests the basic operation of wordsWithOccurrences().  Note that this
  // test does not create or use any threads.
  @Test public void testPublic3() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] words= {"Java", "loves", "you", "Java", "loves", "me", "Java",
                     "loves", "for", "us", "all", "to", "be", "very",
                     "very", "happy", "so", "all", "of", "us", "should",
                     "join", "in", "thanking", "Java", "today"};

    // add the words in the array
    for (String word : words)
      wfc.addWordOccurrence(word);

    // test some words that have different occurrences
    assertTrue(TestUtilities.compareCollection(wfc.wordsWithOccurrences(4),
                                               Arrays.asList("java")));
    assertTrue(TestUtilities.compareCollection(wfc.wordsWithOccurrences(3),
                                               Arrays.asList("loves")));
    assertTrue(TestUtilities.compareCollection(wfc.wordsWithOccurrences(2),
                                               Arrays.asList("all", "us",
                                                             "very")));
    assertTrue(TestUtilities.compareCollection(wfc.wordsWithOccurrences(1),
                                               Arrays.asList("be", "for",
                                                             "happy", "in",
                                                             "join", "me",
                                                             "of", "should",
                                                             "so", "thanking",
                                                             "to", "today",
                                                             "you")));
    // there are no words with 5 occurrences
    assertTrue(TestUtilities.compareCollection(wfc.wordsWithOccurrences(5),
                                               new ArrayList<String>()));
  }

  // Tests calling readWords() to create one thread to read words from one
  // file that only contains unique words, to ensure that one thread can be
  // created and manipulated correctly.
  @Test public void testPublic4() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] words= {"dolphins", "are", "highly", "social", "animals",
                     "often", "living", "in", "pods", "of", "up", "to", "a",
                     "dozen", "members", "though", "sizes", "and",
                     "structures", "vary", "greatly", "they", "can",
                     "establish", "strong", "bonds", "even", "helping",
                     "injured", "or", "ill", "individuals", "by",
                     "bringing", "them", "towards", "the", "surface",
                     "when", "help", "is", "needed"};

    // read words from an external file
    wfc.readWords(Arrays.asList("public4-words"));

    // test the total number of words
    assertEquals(42, wfc.numWords());

    // test the occurrences of each word
    for (String word : words)
      assertEquals(1, wfc.getOccurrences(word));
  }

  // Tests calling readWords() to create one thread to read words from one
  // file that contains multiple occurrences of some words.
  @Test public void testPublic5() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] uniqueWords= {"a", "about", "alone", "and", "at", "back",
                           "bark", "began", "birds", "branches", "bread",
                           "breakfast", "brook", "butter", "carried",
                           "city", "clear", "cool", "cupboard", "cut",
                           "delicious", "dorothy", "down", "drink",
                           "emeralds", "feel", "filled", "finding", "for",
                           "from", "fruit", "gathered", "gave", "get",
                           "good", "hanging", "having", "help", "helped",
                           "her", "herself", "him", "house", "hungry", "it",
                           "journey", "just", "left", "little", "making",
                           "of", "out", "over", "pail", "ran", "ready",
                           "saw", "set", "she", "shelf", "sitting", "so",
                           "some", "sparkling", "spread", "such", "taking",
                           "that", "the", "then", "there", "to", "toto",
                           "trees", "wanted", "was", "water", "went",
                           "what", "when", "which", "with"};
    int[] expectedOccurrences= {2, 1, 1, 7, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1,
                                1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1,
                                1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1,
                                1, 1, 4, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1,
                                1, 1, 9, 1, 1, 1, 3, 1, 1, 1, 1, 1, 10, 1,
                                1, 11, 3, 1, 1, 1, 2, 3, 1, 1, 1, 2};
    int i;

    // read words from an external file
    wfc.readWords(Arrays.asList("public5-words"));

    // test the total number of words
    assertEquals(82, wfc.numWords());

    // test the occurrences of each word using corresponding elements of the
    // array of unique words and expected occurrences of each one
    for (i= 0; i < uniqueWords.length; i++)
      assertEquals(expectedOccurrences[i],
                   wfc.getOccurrences(uniqueWords[i]));
  }

  // Tests calling readWords() to create two threads to read words from two
  // files, which both contain unique words and that don't have any words
  // in common.
  @Test public void testPublic6() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] uniqueWords= {"a", "and", "animals", "are", "bonds",
                           "bringing", "by", "can", "cat", "creatures",
                           "dolphins", "dozen", "eel", "elephant",
                           "establish", "even", "frog", "greatly",
                           "hamster", "help", "helping", "here", "highly",
                           "ill", "in", "individuals", "injured", "is",
                           "kangaroo", "living", "many", "members", "moth",
                           "needed", "nice", "of", "often", "ones", "or",
                           "penguin", "pods", "quokka", "random", "rat",
                           "see", "sizes", "social", "some", "strong",
                           "structures", "surface", "the", "them", "they",
                           "though", "to", "toad", "towards", "up", "vary",
                           "wallaby", "walrus", "when", "words", "written",
                           "zebra"};

    // read words from both external files
    wfc.readWords(Arrays.asList("public6-words1", "public6-words2"));

    assertEquals(66, wfc.numWords());

    for (String word : uniqueWords) 
      assertEquals(1, wfc.getOccurrences(word));
    
  }

  // Tests calling readWords() to create two threads to read words from two
  // files, which both have multiple occurrences of some words and that have
  // some words in common.
  @Test public void testPublic7() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] uniqueWords= {"a", "above", "add", "advanced", "ago", "all",
                           "altogether", "and", "any", "are", "as",
                           "battlefield", "be", "before", "birth", "brave",
                           "brought", "but", "by", "can", "cause", "civil",
                           "come", "conceived", "consecrate", "consecrated",
                           "continent", "created", "dead", "dedicate",
                           "dedicated", "detract", "devotion", "did",
                           "died", "do", "earth", "endure", "engaged",
                           "equal", "far", "fathers", "field", "final",
                           "fitting", "for", "forget", "forth", "fought",
                           "four", "freedom", "from", "full", "gave", "god",
                           "government", "great", "ground", "hallow",
                           "have", "here", "highly", "honored", "in",
                           "increased", "is", "it", "larger", "last",
                           "liberty", "little", "live", "lives", "living",
                           "long", "measure", "men", "met", "might",
                           "nation", "never", "new", "nobly", "nor", "not",
                           "note", "now", "of", "on", "or", "our", "people",
                           "perish", "place", "poor", "portion", "power",
                           "proper", "proposition", "rather", "remaining",
                           "remember", "resolve", "resting", "say", "score",
                           "sense", "seven", "shall", "should", "so",
                           "struggled", "take", "task", "testing", "that",
                           "the", "their", "these", "they", "this", "those",
                           "thus", "to", "under", "unfinished", "us",
                           "vain", "war", "we", "what", "whether", "which",
                           "who", "will", "work", "world", "years"};
    int[] expectedOccurrences= {7, 1, 1, 1, 1, 1, 1, 6, 1, 3, 1, 1, 2, 1, 1,
                                1, 1, 2, 1, 5, 1, 1, 1, 2, 1, 1, 1, 1, 3, 2,
                                4, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1,
                                5, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 3, 1, 1, 5,
                                8, 1, 1, 4, 1, 3, 5, 1, 1, 1, 1, 1, 1, 2, 2,
                                1, 2, 1, 1, 5, 1, 2, 1, 1, 5, 1, 1, 5, 2, 2,
                                2, 3, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1,
                                1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 13, 11, 1, 2,
                                3, 4, 1, 1, 8, 1, 1, 3, 1, 2, 10, 2, 1, 2,
                                3, 1, 1, 1, 1};
    int i;

    // read words from both external files
    wfc.readWords(Arrays.asList("public7-words1", "public7-words2"));

    // test the total number of words
    assertEquals(138, wfc.numWords());

    // test the occurrences of each word using corresponding elements of the
    // array of unique words and expected occurrences of each one
    for (i= 0; i < uniqueWords.length; i++)
      assertEquals(expectedOccurrences[i],
                   wfc.getOccurrences(uniqueWords[i]));
  }

  // Tests calling readWords() to create several threads to read words from
  // several files, which have multiple occurrences of some words and that
  // have some words in common.
  @Test public void testPublic8() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] uniqueWords= {"a", "adventure", "affliction", "afraid",
                           "after", "age", "ah", "all", "allow", "also",
                           "always", "am", "an", "and", "answered",
                           "anywhere", "are", "as", "askance", "asked",
                           "at", "away", "back", "baker", "be", "been",
                           "before", "began", "bell", "best", "better",
                           "beyond", "birmingham", "bitterness", "both",
                           "bought", "boy", "brass", "breakfast", "british",
                           "built", "burned", "business", "busy", "but",
                           "by", "cab", "cabby", "can", "cares", "case",
                           "cast", "catch", "causes", "certainly", "chair",
                           "chill", "circular", "classifying", "client",
                           "closed", "closely", "colds", "collection",
                           "come", "companion's", "concern", "confidence",
                           "confined", "connected", "connection",
                           "consider", "continued", "contrary", "convinced",
                           "could", "course", "curative", "damp", "dance",
                           "days", "dear", "debt", "declined", "deduced",
                           "deductive", "deeper", "delighted", "did",
                           "district", "do", "doctor", "don't", "doorstep",
                           "down", "drugs", "drying", "earth", "energy",
                           "entirely", "established", "even", "ever",
                           "every", "example", "excellent", "excitements",
                           "experiences", "explain", "explained", "far",
                           "farquhar", "features", "feet", "fellow", "few",
                           "fire", "flourishing", "followed", "for", "four",
                           "friend", "from", "full", "general", "gentleman",
                           "give", "glanced", "goes", "got", "ha", "had",
                           "half", "hall", "hand", "hardly", "has", "have",
                           "he", "heal", "health", "hear", "heard",
                           "hieroglyphics", "high", "him", "himself", "his",
                           "hold", "holmes", "holmes's", "hope", "horse",
                           "house", "houses", "how", "however", "hundred",
                           "i", "if", "impressive", "in", "inches",
                           "instant", "instep", "interest", "into",
                           "introduce", "is", "it", "itself", "joined",
                           "journal", "june", "just", "keenly", "kept",
                           "know", "last", "lately", "leaning", "leathers",
                           "lids", "like", "little", "look", "looking",
                           "looks", "man", "marriage", "matter", "me",
                           "medical", "methods", "might", "mine", "moment",
                           "months", "more", "morning", "mr", "mrs", "much",
                           "must", "my", "myself", "nature", "near",
                           "neighbor", "neighbor's", "new", "night",
                           "nodding", "not", "note", "notes", "nothing",
                           "obliterated", "of", "off", "old", "on", "once",
                           "one", "only", "others", "our", "outside",
                           "outstretched", "over", "own", "paddington",
                           "paper", "past", "patent", "perceive", "plate",
                           "powers", "practice", "predecessor",
                           "presenting", "principle", "problems",
                           "professional", "public", "purchased", "pycroft",
                           "question", "rather", "reach", "read", "reading",
                           "ready", "reasoning", "recovered", "remarkably",
                           "removed", "results", "ring", "robust",
                           "rocking", "room", "rushed", "said", "same",
                           "sat", "save", "saw", "scorched", "scribbled",
                           "see", "seemed", "seldom", "severe", "shaking",
                           "shall", "sherlock", "shopman's", "shortly",
                           "should", "sign", "simplicity", "since",
                           "sitting", "slightly", "slippers", "small",
                           "smile", "so", "soles", "some", "somewhat", "st",
                           "steps", "street", "strident", "striding",
                           "such", "suffered", "summer", "sunk",
                           "surprised", "take", "taking", "than", "thank",
                           "that", "the", "them", "then", "there",
                           "therefore", "they", "thing", "think", "thinned",
                           "this", "thought", "three", "thus", "time",
                           "tinge", "to", "today", "tones", "too", "trace",
                           "train", "trust", "trying", "twelve", "two",
                           "under", "unnaturally", "until", "unwell", "up",
                           "upon", "upstairs", "used", "very", "visit",
                           "vitus's", "voice", "wafer", "warmly", "was",
                           "watson", "we", "weakened", "wearing", "week",
                           "weeks", "well", "went", "were", "wet", "what",
                           "wheeler", "when", "which", "whip", "who",
                           "whole", "whom", "whose", "wife", "wish", "with",
                           "without", "work", "worn", "would", "year",
                           "years", "yes", "you", "your", "yours", "youth"};
    int[] expectedOccurrences= {16, 1, 1, 1, 3, 1, 2, 4, 1, 1, 2, 2, 4, 15,
                                2, 1, 8, 9, 1, 1, 10, 1, 1, 1, 3, 4, 1, 1,
                                1, 1, 2, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1,
                                1, 5, 4, 1, 1, 1, 1, 2, 1, 1, 1, 3, 2, 1, 1,
                                1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1,
                                1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1,
                                3, 1, 3, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2,
                                1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1,
                                1, 6, 2, 1, 8, 1, 1, 1, 1, 1, 2, 2, 1, 9, 1,
                                1, 1, 1, 1, 9, 13, 1, 1, 1, 1, 1, 1, 3, 2,
                                8, 1, 4, 1, 1, 1, 1, 1, 3, 2, 2, 33, 3, 1,
                                14, 1, 1, 1, 1, 1, 1, 7, 13, 1, 1, 1, 2, 2,
                                1, 1, 3, 2, 1, 1, 1, 1, 2, 5, 1, 2, 1, 2, 1,
                                1, 3, 2, 1, 1, 1, 2, 1, 4, 1, 2, 1, 2, 1,
                                17, 1, 1, 1, 2, 1, 2, 1, 1, 4, 1, 1, 2, 1,
                                18, 3, 4, 3, 2, 3, 2, 1, 5, 1, 1, 2, 2, 1,
                                1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 2, 1,
                                1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1,
                                1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1,
                                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 9,
                                42, 1, 5, 1, 1, 1, 1, 1, 1, 4, 3, 4, 1, 2,
                                1, 17, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1,
                                1, 1, 4, 1, 1, 5, 1, 1, 1, 1, 1, 10, 2, 2,
                                1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 5, 5, 1, 1, 1,
                                1, 1, 1, 2, 3, 1, 2, 1, 4, 1, 1, 2, 21, 5,
                                1, 1};
    int i;

    // read words from both external files
    wfc.readWords(Arrays.asList("public8-words1", "public8-words2",
                                "public8-words3", "public8-words4"));

    // test the total number of words
    assertEquals(380, wfc.numWords());

    // test the occurrences of each word using corresponding elements of the
    // array of unique words and expected occurrences of each one
    for (i= 0; i < uniqueWords.length; i++)
      assertEquals(expectedOccurrences[i],
                   wfc.getOccurrences(uniqueWords[i]));
  }

  // Tests calling readWords() with some invalid filenames in its argument.
  @Test public void testPublic9() {
    WordFrequencyCounter wfc= new WordFrequencyCounter();
    String[] uniqueWords= {"a", "above", "add", "advanced", "ago", "all",
                           "altogether", "and", "any", "are", "as",
                           "battlefield", "be", "before", "birth", "brave",
                           "brought", "but", "by", "can", "cause", "civil",
                           "come", "conceived", "consecrate", "consecrated",
                           "continent", "created", "dead", "dedicate",
                           "dedicated", "detract", "devotion", "did",
                           "died", "do", "earth", "endure", "engaged",
                           "equal", "far", "fathers", "field", "final",
                           "fitting", "for", "forget", "forth", "fought",
                           "four", "freedom", "from", "full", "gave", "god",
                           "government", "great", "ground", "hallow",
                           "have", "here", "highly", "honored", "in",
                           "increased", "is", "it", "larger", "last",
                           "liberty", "little", "live", "lives", "living",
                           "long", "measure", "men", "met", "might",
                           "nation", "never", "new", "nobly", "nor", "not",
                           "note", "now", "of", "on", "or", "our", "people",
                           "perish", "place", "poor", "portion", "power",
                           "proper", "proposition", "rather", "remaining",
                           "remember", "resolve", "resting", "say", "score",
                           "sense", "seven", "shall", "should", "so",
                           "struggled", "take", "task", "testing", "that",
                           "the", "their", "these", "they", "this", "those",
                           "thus", "to", "under", "unfinished", "us",
                           "vain", "war", "we", "what", "whether", "which",
                           "who", "will", "work", "world", "years"};
    int[] expectedOccurrences= {7, 1, 1, 1, 1, 1, 1, 6, 1, 3, 1, 1, 2, 1, 1,
                                1, 1, 2, 1, 5, 1, 1, 1, 2, 1, 1, 1, 1, 3, 2,
                                4, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1,
                                5, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 3, 1, 1, 5,
                                8, 1, 1, 4, 1, 3, 5, 1, 1, 1, 1, 1, 1, 2, 2,
                                1, 2, 1, 1, 5, 1, 2, 1, 1, 5, 1, 1, 5, 2, 2,
                                2, 3, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1,
                                1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 13, 11, 1, 2,
                                3, 4, 1, 1, 8, 1, 1, 3, 1, 2, 10, 2, 1, 2,
                                3, 1, 1, 1, 1};
    int i;

    // read words from both external files
    wfc.readWords(Arrays.asList("anteater", "public9-words1", "hedgehog",
                                "public9-words2", "salamander"));

    // test the total number of words
    assertEquals(138, wfc.numWords());

    // test the occurrences of each word using corresponding elements of the
    // array of unique words and expected occurrences of each one
    for (i= 0; i < uniqueWords.length; i++)
      assertEquals(expectedOccurrences[i],
                   wfc.getOccurrences(uniqueWords[i]));
  }

}
