package ui;

import autocomplete.Trie;
import cache.LRUCache;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Trie trie = new Trie();
        LRUCache cache = new LRUCache(5);

        trie.insert("apple");
        trie.insert("app");
        trie.insert("apply");
        trie.insert("apt");
        trie.insert("banana");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter prefix (or 'exit' to quit): ");
            String input = scanner.nextLine();
            if (input.equals("exit")) break;

            List<String> suggestions = trie.getSuggestions(input);
            System.out.println("Suggestions:");
            for (String word : suggestions) {
                System.out.println("- " + word);
            }

            if (!suggestions.isEmpty()) {
                cache.put(suggestions.get(0)); // store first suggestion as selected
            }

            cache.display();
        }
    }
}
