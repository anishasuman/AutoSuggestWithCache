package autocomplete;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }

    public List<String> getSuggestions(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;

        // Traverse to the end of prefix
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return results; // No match
            }
            node = node.children.get(ch);
        }

        // Start DFS from end of prefix
        dfs(node, prefix, results);
        return results;
    }

    private void dfs(TrieNode node, String word, List<String> results) {
        if (node.isEndOfWord) {
            results.add(word);
        }

        for (char ch : node.children.keySet()) {
            dfs(node.children.get(ch), word + ch, results);
        }
    }
}
