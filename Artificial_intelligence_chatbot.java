import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class Artificial_intelligence_chatbot extends JFrame {

    private JTextPane chatPane;
    private JTextField inputField;
    private JButton sendButton;
    private HashMap<String, String> faq;

    public Artificial_intelligence_chatbot() {

        setTitle("🤖 Smart AI Chatbot");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        faq = new HashMap<>();
        loadFAQ();

        Color darkBg = new Color(20, 20, 20);
        Color panelBg = new Color(35, 35, 35);

        getContentPane().setBackground(darkBg);

        JLabel title = new JLabel("🤖 ARTIFICIAL INTELLIGENCE CHATBOT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 255, 180));
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        chatPane = new JTextPane();
        chatPane.setEditable(false);
        chatPane.setBackground(panelBg);
        chatPane.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(chatPane);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(darkBg);

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        sendButton = new JButton("Send 🚀");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 255, 180));

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addMessage("Bot", "Hello! Welcome to Smart AI Chatbot.", Color.GREEN);
        addMessage("Bot", "Ask me about Java, AI, NLP, Date, Time or Greetings.", Color.GREEN);

        sendButton.addActionListener(e -> processMessage());
        inputField.addActionListener(e -> processMessage());

        setVisible(true);
    }

    private void loadFAQ() {

        faq.put("what is java",
                "Java is a powerful object-oriented programming language.");

        faq.put("what is ai",
                "Artificial Intelligence enables machines to simulate human intelligence.");

        faq.put("what is nlp",
                "Natural Language Processing helps computers understand human language.");

        faq.put("what is chatbot",
                "A chatbot is software designed to communicate with users.");

        faq.put("who created java",
                "Java was developed by James Gosling.");
    }

    private void processMessage() {

        String userInput = inputField.getText().trim();

        if (userInput.isEmpty()) {
            return;
        }

        addMessage("You", userInput, Color.CYAN);

        String response = getResponse(userInput.toLowerCase());

        addMessage("Bot", response, Color.GREEN);

        inputField.setText("");
    }

    private String getResponse(String input) {

        if (input.contains("hi") || input.contains("hello")) {
            return "Hello! How can I assist you today?";
        }

        if (input.contains("how are you")) {
            return "I'm doing great. Thanks for asking!";
        }

        if (input.contains("time")) {
            return "Current Time: " + LocalTime.now().withNano(0);
        }

        if (input.contains("date")) {
            return "Today's Date: " + LocalDate.now();
        }

        if (input.contains("bye")) {
            return "Goodbye! Have a wonderful day.";
        }

        if (input.contains("help")) {
            return "Try asking about Java, AI, NLP, date or time.";
        }

        for (String question : faq.keySet()) {
            if (input.contains(question)) {
                return faq.get(question);
            }
        }

        return "Sorry, I couldn't understand your question. Please try again.";
    }

    private void addMessage(String sender, String message, Color color) {

        StyledDocument doc = chatPane.getStyledDocument();

        Style style = chatPane.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        StyleConstants.setBold(style, true);

        try {
            doc.insertString(
                    doc.getLength(),
                    sender + ": " + message + "\n\n",
                    style
            );
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        chatPane.setCaretPosition(doc.getLength());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Artificial_intelligence_chatbot();
        });
    }
}
    

