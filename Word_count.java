package com.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Word_count {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Prompt the user to enter text or provide a file
        System.out.print("Please enter text or provide a file path:");

        String inputText = "";

        // Step 2: Read the input text or file and store it in a string.
        try {
            String userInput = scanner.nextLine();
            if (userInput.toLowerCase().endsWith(".txt")) {
                // Read text from a file
                inputText = readTextFromFile(userInput);
            } else {
                // Read text from user input
                inputText = userInput;
            }

            if (inputText.isEmpty()) {
                System.out.print("Input is empty.");
                return;
            }

            // Step 3: Split the string into an array of words using space or punctuation as delimiters.
            String[] words = inputText.split("[\\s\\p{Punct}]+");

            // Step 4: Initialize a counter variable to keep track of the number of words.
            int wordCount = words.length;

            // Step 5: Iterate through the array of words and increment the counter for each word encountered.
            // Step 7: Ignore common words or stop words.
            Set<String> stopWords = createStopWordsSet();
            int uniqueWordCount = 0;
            Map<String, Integer> wordFrequencyMap = new HashMap<>();
            for (String word : words) {
                if (!stopWords.contains(word.toLowerCase())) {
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                    uniqueWordCount++;
                }
            }

            // Step 6: Display the total count of words to the user.
            System.out.print("Total Word Count: " + wordCount);
            System.out.print("Unique Word Count: " + uniqueWordCount);

            // Step 8: Providing statistics like the number of unique words or the frequency of each word.
            System.out.println("\nWord Frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            System.out.print("Error reading the file. Please check the file path.");
        } finally {
            scanner.close();
        }
    }

    private static String readTextFromFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private static Set<String> createStopWordsSet() {
        // You can customize this set with more stop words if needed.
        return new HashSet<>(Arrays.asList(
                "a", "an", "the", "in", "on", "at", "for", "to", "from", "with",
                "and", "or", "but", "is", "are", "was", "were", "it", "i", "you", "he", "she", "they"));
    }
    
}
