package com.dsaproblems.DSAProblems.navan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecodeMessage {

    public static String decode(List<String> words, String message) {
        // Create a HashMap to map the generated key to the original word.
        // This map facilitates quick lookup of original words based on their generated keys.
        Map<String, String> keyToWord = new HashMap<>();

        // For each word in the given known words list, generate its key and put in the map.
        for (String word : words) {
            // Generate the key for the current word.
            String key = generateKey(word);
            // Map the generated key to the original word.
            keyToWord.put(key, word);
        }

        // Split the encoded message by space to process each encoded word individually.
        String[] encodedWords = message.split(" ");

        // Use a StringBuilder to efficiently build the decoded message.
        StringBuilder decodedMessage = new StringBuilder();

        // Process each encoded word:
        for (int i = 0; i < encodedWords.length; i++) {
            String encodedWord = encodedWords[i];
            // Generate the key for the encoded word.
            String key = generateKey(encodedWord);
            // Look up the corresponding original word from our precomputed map.
            String decodedWord = keyToWord.get(key);
            // Append the decoded word to the result
            decodedMessage.append(decodedWord);
            // Add a space between words if not processing the last word.
            if (i < encodedWords.length - 1) {
                decodedMessage.append(" ");
            }
        }

        // Return the fully decoded message as a string.
        return decodedMessage.toString();
    }

    private static String generateKey(String word) {
        // If word is length 2 or less, no reordering is needed.
        if (word.length() <= 2) {
            return word;
        }

        // Get the first character of the word.
        char first = word.charAt(0);
        // Get the last character of the word.
        char last = word.charAt(word.length() - 1);

        // Extract the substring that contains the letters between the first and last characters.
        String middleSubstring = word.substring(1, word.length() - 1);

        // Convert the middle substring to a character array to allow sorting.
        char[] middleChars = middleSubstring.toCharArray();

        // Sort the character array for the middle characters.
        Arrays.sort(middleChars);

        // Build and return the key: combination of first character, sorted middle characters, and last character.
        return first + new String(middleChars) + last;
    }


    public static void main(String[] args) {
        // Create some sample vocabulary words.
        List<String> vocab = Arrays.asList("hello", "this", "message", "is", "funny", "ball", "strawberry");

        // Test case 1: Basic test provided in sample.
        String encodedMessage1 = "hlelo tihs masegse is fnnuy";
        String result1 = decode(vocab, encodedMessage1);
        System.out.println("Decoded Message 1: " + result1);
        // Expected output: "hello this message is funny"

        // Test case 2: Words with one-letter words.
        List<String> vocab2 = Arrays.asList("a", "i", "am", "ok");
        String encodedMessage2 = "a i am ok";
        String result2 = decode(vocab2, encodedMessage2);
        System.out.println("Decoded Message 2: " + result2);
        // Expected output: "a i am ok" (words with length <=2 should be unchanged)

        // Test case 3: Words that remain the same when encoded.
        List<String> vocab3 = Arrays.asList("at", "in", "on");
        String encodedMessage3 = "at in on";
        String result3 = decode(vocab3, encodedMessage3);
        System.out.println("Decoded Message 3: " + result3);
        // Expected output: "at in on"

        // Test case 4: Another test case with different encoded messages.
        List<String> vocab4 = Arrays.asList("ball", "funny", "hello", "message");
        String encodedMessage4 = "blla fnnuy hlelo masegse";
        String result4 = decode(vocab4, encodedMessage4);
        System.out.println("Decoded Message 4: " + result4);
        // Expected output: "ball funny hello message"
    }
}
