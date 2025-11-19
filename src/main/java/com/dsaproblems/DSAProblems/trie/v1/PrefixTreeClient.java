package com.dsaproblems.DSAProblems.trie.v1;

public class PrefixTreeClient {

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Test Case 1: Insert "apple" and search for it
        trie.insert("apple");                                       // Insert the word "apple"
        // Check that "apple" exists in the Trie → Expected: true
        System.out.println("Search for 'apple': " + trie.search("apple")); // Output should be true

        // Test Case 2: Search for "app" when only "apple" is inserted
        // Check that "app" is not a complete word in the Trie → Expected: false
        System.out.println("Search for 'app': " + trie.search("app"));  // Output should be false

        // Test Case 3: Check prefix "app" → Expected: true as "apple" starts with "app"
        System.out.println("Starts with 'app': " + trie.startsWith("app"));  // Output should be true

        // Test Case 4: Insert "app" and test search again
        trie.insert("app");                                          // Insert the word "app"
        // Now "app" should be found as an exact word → Expected: true
        System.out.println("Search for 'app' after inserting 'app': " + trie.search("app"));  // Output should be true

        // Test Case 5: Insert and search for a single character word
        trie.insert("a");                                            // Insert single letter "a"
        // Check that "a" exists in Trie → Expected: true
        System.out.println("Search for 'a': " + trie.search("a"));     // Output should be true

        // Additional Test Case 6: Prefix test for a non-existent prefix
        // "b" has not been inserted at any point → Expected: false
        System.out.println("Starts with 'b': " + trie.startsWith("b")); // Output should be false


        WordDictionary wordDictionary = new WordDictionary();

        // Add words "bad", "dad", "mad" to the Trie
        wordDictionary.addWord("bad"); // Adds "bad"
        wordDictionary.addWord("dad"); // Adds "dad"
        wordDictionary.addWord("mad"); // Adds "mad"

        // Unit Test 1: Search for "pad"
        // Expected output: false, since "pad" is not added to the Trie
        System.out.println("Search for 'pad': " + wordDictionary.search("pad"));  // Output: false

        // Unit Test 2: Search for "bad"
        // Expected output: true, since "bad" is present in the Trie
        System.out.println("Search for 'bad': " + wordDictionary.search("bad"));  // Output: true

        // Unit Test 3: Search for ".ad"
        // Expected output: true, since ".ad" can match "bad", "dad", or "mad"
        System.out.println("Search for '.ad': " + wordDictionary.search(".ad"));  // Output: true

        // Unit Test 4: Search for "b.."
        // Expected output: true, since "b.." can match "bad"
        System.out.println("Search for 'b..': " + wordDictionary.search("b.."));  // Output: true
    }
}
