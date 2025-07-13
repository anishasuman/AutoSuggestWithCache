package test;

import cache.LRUCache;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTest {

    @Test
    public void testLRUOrder() {
        LRUCache cache = new LRUCache(3);

        cache.put("apple");
        cache.put("banana");
        cache.put("cherry");

        // Overwrite "apple" to make it most recent
        cache.put("apple");
        cache.put("date"); // should remove "banana"

        // Capture output of display
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        cache.display();
        String output = outContent.toString();

        assertTrue(output.contains("apple"));
        assertTrue(output.contains("cherry"));
        assertTrue(output.contains("date"));
        assertFalse(output.contains("banana"));
    }
}
