package com.dsaproblems.DSAProblems.advancedJava;

import java.util.Arrays;

public class TypeHeadClient {

    public static void main(String[] args) {
        TypeHead typeHead = new TypeHead();

        typeHead.incrementSearchTermFrequency("michelleobama", 100);  // Frequency: 100
        typeHead.incrementSearchTermFrequency("michaeljackson", 90);    // Frequency: 90
        typeHead.incrementSearchTermFrequency("michaeljordan", 120);

        // Find top 2 suggestion for prefix "mic"
        String[] output1 = typeHead.findTopXSuggestion("mic", 2);
        System.out.println(Arrays.toString(output1));

        TypeHead typeHead2 = new TypeHead();
        typeHead2.incrementSearchTermFrequency("bat", 10);      // Frequency: 10
        typeHead2.incrementSearchTermFrequency("cricket", 20);  // Frequency: 20
        typeHead2.incrementSearchTermFrequency("ball", 15);     // Frequency: 15

        // Find top 1 suggestion for prefix "b"
        String[] output2 = typeHead2.findTopXSuggestion("b", 1);
        // Expected candidate: "ball" because it has frequency 15 which is highest among "bat" and "ball".
        // Result after lexicographical sort (only one element remains same): ["ball"]
        System.out.println(Arrays.toString(output2));


        TypeHead typeHead3 = new TypeHead();
        // Two different terms with same frequency and overlapping prefix.
        typeHead3.incrementSearchTermFrequency("apple", 50);
        typeHead3.incrementSearchTermFrequency("apricot", 50);
        // For prefix "ap", tie break according to lexicographical comparison in descending order.
        // "apricot".compareTo("apple") returns positive value meaning "apricot" is lexicographically larger.
        // So order before final sort is: ["apricot", "apple"]
        // After final lexicographical sorting (ascending) they become: ["apple", "apricot"]
        String[] output4 = typeHead3.findTopXSuggestion("ap", 2);
        System.out.println(Arrays.toString(output4));
    }
}
