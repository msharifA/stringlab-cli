package io.github.msharifa.stringlab;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            help();
            return;
        }

        switch (args[0]) {
            case "anagram":
                if (args.length < 3) { System.out.println("Usage: anagram <s> <t>"); return; }
                System.out.println(isAnagram(args[1], args[2]));
                break;

            case "pal":
                if (args.length < 2) { System.out.println("Usage: pal <s>"); return; }
                System.out.println(isPalindrome(args[1]));
                break;
               
             case "freq":
                if (args.length < 2) { System.out.println("Usage: freq <s>"); return; }
                System.out.println(countLetters(args[1]));
                 break;
            
            case "wordfreq":
                if (args.length < 2) { System.out.println("Usage: wordfreq <sentence>"); return; }
                System.out.println(countWords(args[1]));
                break;
                
            case "dedup":
                if (args.length < 2) { System.out.println("Usage: dedup <s>"); return; }
                System.out.println(removeDuplicates(args[1]));
                break;
                
            case "caesar":
                if (args.length < 3) {
                    System.out.println("Usage: caesar <shift> <s>");
                    return;
                }
                int shift = Integer.parseInt(args[1]);
                System.out.println(caesarCipher(args[2], shift));
                break;
               
             case "decrypt":
                if (args.length < 3) {
                    System.out.println("Usage: decrypt <shift> <s>");
                    return;
                }
                int decShift = Integer.parseInt(args[1]);
                System.out.println(caesarCipher(args[2], -decShift));
                break;
            
            case "bruteforce":
                if (args.length < 2) {
                    System.out.println("Usage: bruteforce <s>");
                    return;
                }
                System.out.println(bruteforceCaesar(args[1]));
                break;
            
            default:
                help();
                break;
        }
    }

    private static void help() {
        System.out.println("""
        StringLab commands:
          anagram <s> <t>  - Check if two strings are anagrams.
          pal <s>          - Check if a string is a palindrome.
          freq <s>         - Show character frequency map.
          wordfreq <sentence> - Show word frequency map.
          dedup <s>        - Remove duplicate characters, keep first occurrence.
          caesar <shift> <s> - Apply Caesar cipher to shift letters.

        """);
    }

    // --- logic ---
    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        var a = new HashMap<Character,Integer>();
        var b = new HashMap<Character,Integer>();
        for (int i = 0; i < s.length(); i++) {
            a.put(s.charAt(i), a.getOrDefault(s.charAt(i), 0) + 1);
            b.put(t.charAt(i), b.getOrDefault(t.charAt(i), 0) + 1);
        }
        return a.equals(b);
    }

    static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
    static java.util.Map<Character, Integer> countLetters(String s) {
        // Optionally normalize: uncomment the next line to ignore spaces & case
         s = s.replaceAll("\\s+", "").toLowerCase();
    
        var counts = new java.util.TreeMap<Character, Integer>(); // TreeMap = sorted keys
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        return counts;
    }
    static java.util.Map<String, Integer> countWords(String s) {
        if (s == null || s.isEmpty()) return java.util.Collections.emptyMap();
    
        // normalize: lowercase
        s = s.toLowerCase();
    
        // split on any non-letter/digit (punctuation, spaces, etc.)
        String[] parts = s.split("[^a-z0-9]+");
    
        var counts = new java.util.TreeMap<String, Integer>(); // alphabetical by word
        for (String w : parts) {
            if (w.isEmpty()) continue;
            counts.put(w, counts.getOrDefault(w, 0) + 1);
        }
        return counts;
    }
    static String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        java.util.Set<Character> seen = new java.util.HashSet<>();
        for (char c : s.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }
    static String caesarCipher(String s, int shift) {
        StringBuilder sb = new StringBuilder();
        shift = shift % 26; // wrap large shifts
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ('A' + (c - 'A' + shift + 26) % 26));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ('a' + (c - 'a' + shift + 26) % 26));
            } else {
                sb.append(c); // non-letters unchanged
            }
        }
        return sb.toString();
    }
    static String bruteforceCaesar(String s) {
        StringBuilder out = new StringBuilder();
        for (int shift = 0; shift < 26; shift++) {
            out.append(String.format("%2d: %s%n", shift, caesarCipher(s, shift)));
        }
        return out.toString();
    }
    
}
