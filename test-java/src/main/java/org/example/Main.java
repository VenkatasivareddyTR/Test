package org.example;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello..!");
        String[] testStrings = {
                "A man, a plan, a canal: Panama",
                "racecar",
                "hello",
                "No 'x' in Nixon"
        };

        for (String s : testStrings) {
            System.out.println("Is \"" + s + "\" a palindrome? " + isPalindrome(s));
        }
    }

    public static boolean isPalindrome(String str) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanedStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Reverse the cleaned string
        String reversedStr = new StringBuilder(cleanedStr).reverse().toString();

        // Compare the cleaned string with the reversed string
        return cleanedStr.equals(reversedStr);
    }
}
