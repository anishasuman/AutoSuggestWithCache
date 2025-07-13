package test;

import autocomplete.Trie;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TrieTest {

    @Test
    public void testInsertAndSuggestions() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apply");

        List<String> results = trie.getSuggestions("app");

        assertTrue(results.contains("apple"));
        assertTrue(results.contains("app"));
        assertTrue(results.contains("apply"));
        assertFalse(results.contains("apt")); // not added
    }

    @Test
    public void testEmptyPrefix() {
        Trie trie = new Trie();
        trie.insert("ball");
        trie.insert("bat");

        List<String> results = trie.getSuggestions("");

        assertEquals(2, results.size());
        assertTrue(results.contains("ball"));
        assertTrue(results.contains("bat"));
    }

    @Test
    public void testNoMatch() {
        Trie trie = new Trie();
        trie.insert("cat");

        List<String> results = trie.getSuggestions("dog");

        assertTrue(results.isEmpty());
    }
}
