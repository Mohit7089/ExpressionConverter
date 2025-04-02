import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.*;

public class ExpressionConverter {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Custom Window");
        mainFrame.setSize(1300, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(Color.BLACK);

        JTextArea firstBox = new JTextArea("An infix expression is an expression used in day-to-day life. "
                + "An infix expression is a single letter, or an operator, preceded by one infix string and "
                + "followed by another infix string. E.g., A, A + B, (A + B) + (C – D).\n\n"
                + "Postfix expression (Reverse Polish Notation) is a single letter or an operator, "
                + "preceded by two postfix strings. E.g., A, A B +, A B + C D –.\n\n"
                + "Prefix expression (Polish Notation) is a single letter or an operator, "
                + "followed by two prefix strings. E.g., + A B, * + A B – C D.\n\n"
                + "This application allows you to convert expressions between any forms: "
                + "Infix, Prefix, and Postfix. Simply select the input and output forms, "
                + "enter the expression, and click 'Convert' to see the result.");
        firstBox.setBounds(100, 50, 1100, 150);
        firstBox.setBackground(Color.BLACK);
        firstBox.setForeground(Color.GREEN);
        firstBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        firstBox.setLineWrap(true);
        firstBox.setWrapStyleWord(true);
        firstBox.setEditable(false);
        firstBox.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        JButton button1 = new JButton("Open Converter");
        button1.setBounds(550, 250, 200, 50);
        button1.setForeground(Color.GREEN);
        button1.setBackground(Color.BLACK);
        button1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        JTextArea thirdBox = new JTextArea("An AVL tree is a self-balancing binary search tree where the height "
                + "difference (balance factor) between left and right subtrees is at most one. This ensures "
                + "efficient insertion, deletion, and search with O(log n) time complexity.\n\n"
                + "When an imbalance occurs, the tree is restructured using four types of rotations: right, left, "
                + "left-right, and right-left.");
        thirdBox.setBounds(100, 350, 1100, 150);
        thirdBox.setBackground(Color.BLACK);
        thirdBox.setForeground(Color.GREEN);
        thirdBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        thirdBox.setLineWrap(true);
        thirdBox.setWrapStyleWord(true);
        thirdBox.setEditable(false);
        thirdBox.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        JButton button2 = new JButton("Open AVL Tree Builder");
        button2.setBounds(550, 550, 200, 50);
        button2.setForeground(Color.GREEN);
        button2.setBackground(Color.BLACK);
        button2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        // ====================================================================================================

        button1.addActionListener(e -> openNewWindow());

        mainFrame.add(firstBox);
        mainFrame.add(button1);
        mainFrame.add(thirdBox);
        mainFrame.add(button2);

        mainFrame.setVisible(true);
    }

    public static void openNewWindow() {
        JFrame newWindow = new JFrame("Expression Converter");
        newWindow.setSize(1300, 800);
        newWindow.setLayout(null);
        newWindow.getContentPane().setBackground(Color.BLACK);

        JLabel label1 = new JLabel("Choose Expression Type:");
        label1.setForeground(Color.GREEN);
        label1.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label1.setBounds(400, 100, 200, 30);

        String[] expressions = { "INFIX", "PREFIX", "POSTFIX" };
        JComboBox<String> firstDropdown = new JComboBox<>(expressions);
        firstDropdown.setBounds(600, 100, 200, 30);
        firstDropdown.setBackground(Color.BLACK);
        firstDropdown.setForeground(Color.GREEN);
        firstDropdown.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JTextArea inputTextArea = new JTextArea();
        inputTextArea.setBounds(400, 150, 600, 100);
        inputTextArea.setBackground(Color.BLACK);
        inputTextArea.setForeground(Color.GREEN);
        inputTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputTextArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);

        JLabel label2 = new JLabel("Convert To:");
        label2.setForeground(Color.GREEN);
        label2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        label2.setBounds(400, 280, 200, 30);

        JComboBox<String> secondDropdown = new JComboBox<>();
        secondDropdown.setBounds(600, 280, 200, 30);
        secondDropdown.setBackground(Color.BLACK);
        secondDropdown.setForeground(Color.GREEN);
        secondDropdown.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setBounds(400, 350, 600, 100);
        resultTextArea.setBackground(Color.BLACK);
        resultTextArea.setForeground(Color.GREEN);
        resultTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        resultTextArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setEditable(false);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(550, 480, 200, 50);
        convertButton.setForeground(Color.GREEN);
        convertButton.setBackground(Color.BLACK);
        convertButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        firstDropdown.addActionListener(e -> {
            String selected = (String) firstDropdown.getSelectedItem();
            secondDropdown.removeAllItems();

            if ("INFIX".equals(selected)) {
                secondDropdown.addItem("PREFIX");
                secondDropdown.addItem("POSTFIX");
            } else if ("PREFIX".equals(selected)) {
                secondDropdown.addItem("INFIX");
            } else if ("POSTFIX".equals(selected)) {
                secondDropdown.addItem("INFIX");
            }
        });

        convertButton.addActionListener(e -> {
            String inputText = inputTextArea.getText().trim();
            String fromType = (String) firstDropdown.getSelectedItem();
            String toType = (String) secondDropdown.getSelectedItem();

            String result;
            try {
                inputText = inputText.replaceAll("\\s+", "");

                if (inputText.isEmpty()) {
                    throw new IllegalArgumentException("Input expression is empty.");
                }

                if ("INFIX".equals(fromType)) {
                    if ("PREFIX".equals(toType)) {
                        result = infixToPrefix(inputText);
                    } else if ("POSTFIX".equals(toType)) {
                        result = infixToPostfix(inputText);
                    } else {
                        result = "Invalid conversion";
                    }
                } else if ("PREFIX".equals(fromType)) {
                    if ("INFIX".equals(toType)) {
                        result = prefixToInfix(inputText);
                    } else {
                        result = "Invalid conversion";
                    }
                } else if ("POSTFIX".equals(fromType)) {
                    if ("INFIX".equals(toType)) {
                        result = postfixToInfix(inputText);
                    } else {
                        result = "Invalid conversion";
                    }
                } else {
                    result = "Invalid conversion";
                }
            } catch (Exception ex) {
                result = "Error: " + ex.getMessage();
            }

            resultTextArea.setText("Converted Expression: " + result);
        });

        firstDropdown.setSelectedIndex(0);
        firstDropdown.getActionListeners()[0]
                .actionPerformed(new ActionEvent(firstDropdown, ActionEvent.ACTION_PERFORMED, ""));

        newWindow.add(label1);
        newWindow.add(firstDropdown);
        newWindow.add(inputTextArea);
        newWindow.add(label2);
        newWindow.add(secondDropdown);
        newWindow.add(resultTextArea);
        newWindow.add(convertButton);

        newWindow.setVisible(true);
    }

    private static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    throw new IllegalArgumentException("Invalid expression: Unmatched parentheses.");
                }
                stack.pop();
            }

            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException("Invalid expression: Unmatched parentheses.");
            }
            result.append(stack.pop());
        }
        return result.toString();
    }

    private static String infixToPrefix(String expression) {
        StringBuilder reversed = new StringBuilder(expression).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(') {
                reversed.setCharAt(i, ')');
            } else if (reversed.charAt(i) == ')') {
                reversed.setCharAt(i, '(');
            }
        }

        String postfix = infixToPostfix(reversed.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    private static String prefixToInfix(String expression) {
        Stack<String> stack = new Stack<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op1 + c + op2 + ")");
            }
        }
        return stack.pop();
    }

    private static String postfixToInfix(String expression) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                String op2 = stack.pop();
                String op1 = stack.pop();
                stack.push("(" + op1 + c + op2 + ")");
            }
        }
        return stack.pop();
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}