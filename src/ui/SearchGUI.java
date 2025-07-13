package ui;

import util.SearchEngine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchGUI extends JFrame {

    private JTextField inputField;
    private JTextArea suggestionsArea;
    private DefaultListModel<String> historyModel;
    private JList<String> historyList;
    private SearchEngine engine;
    private JButton clearHistoryButton, deleteSelectedButton;

    public SearchGUI() {
        super("💡 Smart Search Engine");

        engine = new SearchEngine(5);

        Color bgColor = new Color(240, 244, 248);
        Color suggestionColor = new Color(224, 240, 255);
        Color historyColor = new Color(255, 250, 205);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 13);

        JLabel titleLabel = new JLabel("🔍  Enter Search:");
        titleLabel.setFont(labelFont);

        inputField = new JTextField(30);
        inputField.setFont(textFont);
        inputField.setBackground(Color.WHITE);
        inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        suggestionsArea = new JTextArea(10, 40);
        suggestionsArea.setEditable(false);
        suggestionsArea.setFocusable(false);
        suggestionsArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        suggestionsArea.setFont(textFont);
        suggestionsArea.setBackground(suggestionColor);
        suggestionsArea.setBorder(BorderFactory.createTitledBorder(
                new RoundedBorder(15), "💡 Suggestions"));

        suggestionsArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int offset = suggestionsArea.viewToModel2D(e.getPoint());
                    int rowStart = javax.swing.text.Utilities.getRowStart(suggestionsArea, offset);
                    int rowEnd = javax.swing.text.Utilities.getRowEnd(suggestionsArea, offset);
                    String selectedLine = suggestionsArea.getText().substring(rowStart, rowEnd).trim();
                    selectedLine = selectedLine.replace("▣", "").trim();

                    if (!selectedLine.isEmpty()) {
                        inputField.setText(selectedLine);
                        engine.insert(selectedLine);
                        addToHistory(selectedLine);
                        String query = selectedLine.replace(" ", "+");
                        Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query));
                    }
                } catch (Exception ignored) {
                }
            }
        });

        historyModel = new DefaultListModel<>();
        historyList = new JList<>(historyModel);
        historyList.setFont(textFont);
        historyList.setBackground(historyColor);
        historyList.setBorder(BorderFactory.createTitledBorder(
                new RoundedBorder(15), "🕓 Recent Searches (Select & Delete)"));

        clearHistoryButton = new JButton("🗑️ Clear All History");
        clearHistoryButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        clearHistoryButton.addActionListener(e -> historyModel.clear());

        deleteSelectedButton = new JButton("❌ Delete Selected");
        deleteSelectedButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteSelectedButton.addActionListener(e -> {
            List<String> selectedValues = historyList.getSelectedValuesList();
            for (String val : selectedValues) {
                historyModel.removeElement(val);
            }
        });

        JLabel madeByLabel = new JLabel("Made by Anisha Kumari");
        madeByLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        madeByLabel.setHorizontalAlignment(SwingConstants.CENTER);

        inputField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String prefix = inputField.getText().trim();
                List<String> suggestions = engine.getSuggestions(prefix);
                showSuggestions(suggestions);

                if (e.getKeyCode() == KeyEvent.VK_ENTER && !prefix.isEmpty()) {
                    try {
                        engine.insert(prefix);
                        addToHistory(prefix);
                        String query = prefix.replace(" ", "+");
                        Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + query));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Failed to open browser.");
                    }
                }
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(bgColor);
        topPanel.add(titleLabel);
        topPanel.add(inputField);

        JPanel suggestionsPanel = new JPanel(new BorderLayout());
        suggestionsPanel.setBackground(bgColor);
        suggestionsPanel.add(new JScrollPane(suggestionsArea), BorderLayout.CENTER);

        JPanel historyPanel = new JPanel(new BorderLayout(5, 5));
        historyPanel.setBackground(bgColor);
        historyPanel.add(new JScrollPane(historyList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonPanel.setBackground(bgColor);
        buttonPanel.add(deleteSelectedButton);
        buttonPanel.add(clearHistoryButton);
        buttonPanel.add(madeByLabel);
        historyPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBackground(bgColor);
        centerPanel.add(suggestionsPanel);
        centerPanel.add(historyPanel);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(bgColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 570);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showSuggestions(List<String> suggestions) {
        suggestionsArea.setText("");
        String prefix = inputField.getText().trim().toLowerCase();
        List<String> randomSuggestions = List.of(
                prefix + " app",
                prefix + " website",
                prefix + " price",
                prefix + " review",
                prefix + " latest",
                prefix + " update",
                prefix + " tool",
                prefix + " software",
                prefix + " download",
                prefix + " news"
        );

        List<String> displaySuggestions = new ArrayList<>(randomSuggestions);
        Collections.shuffle(displaySuggestions);
        displaySuggestions = displaySuggestions.subList(0, 5);

        for (String s : displaySuggestions) {
            suggestionsArea.append("▣ " + s + "\n");
        }
    }

    private void addToHistory(String entry) {
        if (!historyModel.contains(entry)) {
            historyModel.addElement(entry);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SearchGUI::new);
    }
}

class RoundedBorder extends javax.swing.border.AbstractBorder {
    private final int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = insets.top = insets.bottom = radius + 1;
        return insets;
    }
}
