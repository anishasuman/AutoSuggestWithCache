package util;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    private List<String> history = new ArrayList<>();

    public SearchEngine(int size) {
        // your constructor logic (if needed)
    }

    public void insert(String query) {
        // your insert logic
    }

    public List<String> getSuggestions(String prefix) {
        // your suggestion logic
        return new ArrayList<>();
    }

    public void addToHistory(String query) {
        history.add(query);
    }

    public void showHistory() {
        for (String item : history) {
            System.out.println(item);
        }
    }

    public void clearHistory() {
        history.clear();
    }

    // ✅ ADD THIS METHOD FOR YOUR FAKE "HOME PAGE"
    public void showHomePage() {
        System.out.println("---------------------------------------------------");
        System.out.println("|              Anisha's Smart Home Page           |");
        System.out.println("|  Home    |    Search    |    Tools              |");
        System.out.println("---------------------------------------------------");
        System.out.println("| ➤ Search Section: Type your query                |");
        System.out.println("| ➤ Trending News: AI, Tech, Space, India Startups |");
        System.out.println("| ➤ AI Suggestions: Chatbots, AI Writing, Prompting|");
        System.out.println("| ➤ Quick Links: Google, YouTube, ChatGPT, etc.    |");
        System.out.println("---------------------------------------------------");
        System.out.println("|           Made by Anisha Kumari                  |");
        System.out.println("---------------------------------------------------");
    }
}
