package com.dsaproblems.DSAProblems.strings;

import java.util.HashMap;
import java.util.Map;

public class BestMatch {

    public static void main(String[] args) {
//        String[] documents = {
//                "A sample document for testing",
//                "Another document",
//                "One more sample document"
//        };

        String[] documents = {
                "This is a sample document for testing",
                "Another document",
                "One more sample document"
        };
        String query = "sample document";
        // Expected output is 0 (document at index 0 wins because of the earlier match)
        System.out.println(bestMatchDocIndexv1(documents, query));
        System.out.println(bestMatchDocIndexv2(documents, query));
        System.out.println(bestMatchDocIndexv3(documents, query));
    }

    public static int bestMatchDocIndexv3(String[] documents, String query) {
        String[] queryWords = query.trim().split("\\s+");
        Map<String, Integer> queryFreq = new HashMap<>();
        for (String word : queryWords) {
            queryFreq.put(word, queryFreq.getOrDefault(word, 0) + 1);
        }

        int bestIndex = -1, bestMatchCount = -1;
        for (int docIndex = 0; docIndex < documents.length; docIndex++) {
            String[] docWords = documents[docIndex].trim().split("\\s+");
            Map<String, Integer> docFreq = new HashMap<>();
            for (String word : docWords) {
                docFreq.put(word, docFreq.getOrDefault(word, 0) + 1);
            }
            int matchCount = 0;
            for (String word : queryFreq.keySet()) {
                matchCount += Math.min(queryFreq.get(word), docFreq.getOrDefault(word, 0));
            }
            if (matchCount > bestMatchCount) {
                bestMatchCount = matchCount;
                bestIndex = docIndex;
            }
        }
        return bestIndex;
    }

    public static int bestMatchDocIndexv2(String[] documents, String query) {
        String[] queryWords = query.trim().split("\\s+");
        int bestIndex = -1, bestMatchCount = -1, bestStartingIndex = Integer.MAX_VALUE;
        for (int docIndex = 0; docIndex < documents.length; docIndex++) {
            String[] docWords = documents[docIndex].trim().split("\\s+");
            for (int i = 0; i <= docWords.length - queryWords.length; i++) {
                int matchCount = 0;
                for (int j = 0; j < queryWords.length; j++) {
                    if (docWords[i + j].equals(queryWords[j])) {
                        matchCount++;
                    }
                }
                if (matchCount > bestMatchCount ||
                        (matchCount == bestMatchCount && i < bestStartingIndex)) {
                    bestMatchCount = matchCount;
                    bestStartingIndex = i;
                    bestIndex = docIndex;
                }
            }
        }
        return bestIndex;
    }

    public static int bestMatchDocIndexv1(String[] documents, String query) {
        String[] queryWords = query.trim().split("\\s+");
        int bestIndex = -1, bestMatchCount = -1, bestStartingIndex = Integer.MAX_VALUE;
        for (int docIndex = 0; docIndex < documents.length; docIndex++) {
            String[] docWords = documents[docIndex].trim().split("\\s+");
            int docPointer = 0, queryPointer = 0, currentMatchCount = 0, firstMatchPos = Integer.MAX_VALUE;
            while (docPointer < docWords.length && queryPointer < queryWords.length) {
                if (docWords[docPointer].equals(queryWords[queryPointer])) {
                    if (currentMatchCount == 0) {
                        firstMatchPos = docPointer;
                    }
                    queryPointer++;
                    currentMatchCount++;
                }
                docPointer++;
            }
            if (currentMatchCount > bestMatchCount) {
                bestMatchCount = currentMatchCount;
                bestStartingIndex = firstMatchPos;
                bestIndex = docIndex;
            } else if (currentMatchCount == bestMatchCount) {
                if (firstMatchPos < bestStartingIndex) {
                    bestStartingIndex = firstMatchPos;
                    bestIndex = docIndex;
                }
            }
        }
        return bestIndex;
    }
}
