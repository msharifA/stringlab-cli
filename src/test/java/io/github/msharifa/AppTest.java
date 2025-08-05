package io.github.msharifa.stringlab;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;

public class AppTest {

    @Test
    public void testAnagramTrue() {
        assertTrue(App.isAnagram("listen", "silent"));
    }

    @Test
    public void testAnagramFalse() {
        assertFalse(App.isAnagram("hello", "world"));
    }

    @Test
    public void testPalindromeTrue() {
        assertTrue(App.isPalindrome("racecar"));
    }

    @Test
    public void testPalindromeFalse() {
        assertFalse(App.isPalindrome("apple"));
    }

    @Test
    public void testCharFreq() {
        Map<Character, Integer> freq = App.countLetters("aabbc");
        assertEquals((Integer)2, freq.get('a'));
        assertEquals((Integer)2, freq.get('b'));
        assertEquals((Integer)1, freq.get('c'));
    }

    @Test
    public void testDedup() {
        String result = App.removeDuplicates("mississippi");
        assertEquals("misp", result);
    }

    @Test
    public void testCaesarEncrypt() {
        String result = App.caesarCipher("abc", 3);
        assertEquals("def", result);
    }

    @Test
    public void testCaesarDecrypt() {
        String result = App.caesarCipher("def", -3);
        assertEquals("abc", result);
    }
}
