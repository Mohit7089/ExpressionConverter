import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.*;

public class ExpressionConverter {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Data Structures Visualizer");
        mainFrame.setSize(1300, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(Color.BLACK);

        // Expression Converter Description
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

        JButton button1 = new JButton("Open Expression Converter");
        button1.setBounds(500, 250, 300, 50);
        button1.setForeground(Color.GREEN);
        button1.setBackground(Color.BLACK);
        button1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        // AVL Tree Description
        JTextArea avlBox = new JTextArea("An AVL tree is a self-balancing binary search tree where the height "
                + "difference (balance factor) between left and right subtrees is at most one. This ensures "
                + "efficient insertion, deletion, and search with O(log n) time complexity.\n\n"
                + "When an imbalance occurs, the tree is restructured using four types of rotations: "
                + "right, left, left-right, and right-left.");
        avlBox.setBounds(100, 350, 1100, 150);
        avlBox.setBackground(Color.BLACK);
        avlBox.setForeground(Color.GREEN);
        avlBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        avlBox.setLineWrap(true);
        avlBox.setWrapStyleWord(true);
        avlBox.setEditable(false);
        avlBox.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        JButton avlButton = new JButton("Open AVL Tree Visualizer");
        avlButton.setBounds(500, 550, 300, 50);
        avlButton.setForeground(Color.GREEN);
        avlButton.setBackground(Color.BLACK);
        avlButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        button1.addActionListener(e -> openExpressionConverter());
        avlButton.addActionListener(e -> openAVLTreeVisualizer());

        mainFrame.add(firstBox);
        mainFrame.add(button1);
        mainFrame.add(avlBox);
        mainFrame.add(avlButton);

        mainFrame.setVisible(true);
    }

    private static void openExpressionConverter() {
        JFrame converterFrame = new JFrame("Expression Converter");
        converterFrame.setSize(1300, 800);
        converterFrame.setLayout(null);
        converterFrame.getContentPane().setBackground(Color.BLACK);

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
                secondDropdown.addItem("POSTFIX");
            } else if ("POSTFIX".equals(selected)) {
                secondDropdown.addItem("INFIX");
                secondDropdown.addItem("PREFIX");
            }
        });

        convertButton.addActionListener(e -> {
            String inputText = inputTextArea.getText().trim();
            String fromType = (String) firstDropdown.getSelectedItem();
            String toType = (String) secondDropdown.getSelectedItem();

            String result;
            try {
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
                    } else if ("POSTFIX".equals(toType)) {
                        result = prefixToPostfix(inputText);
                    } else {
                        result = "Invalid conversion";
                    }
                } else if ("POSTFIX".equals(fromType)) {
                    if ("INFIX".equals(toType)) {
                        result = postfixToInfix(inputText);
                    } else if ("PREFIX".equals(toType)) {
                        result = postfixToPrefix(inputText);
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

        converterFrame.add(label1);
        converterFrame.add(firstDropdown);
        converterFrame.add(inputTextArea);
        converterFrame.add(label2);
        converterFrame.add(secondDropdown);
        converterFrame.add(resultTextArea);
        converterFrame.add(convertButton);

        converterFrame.setVisible(true);
    }

    private static void openAVLTreeVisualizer() {
        JFrame avlFrame = new JFrame("AVL Tree Visualizer");
        avlFrame.setSize(900, 700);
        avlFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        avlFrame.setLayout(new BorderLayout());

        AVLTree tree = new AVLTree();
        JTextArea output = new JTextArea();
        AVLTreePanel treePanel = new AVLTreePanel(tree);

        JPanel topPanel = new JPanel();
        JTextField insertField = new JTextField(5);
        JButton insertButton = new JButton("Insert");
        JTextField deleteField = new JTextField(5);
        JButton deleteButton = new JButton("Delete");
        JTextField findField = new JTextField(5);
        JButton findButton = new JButton("Find");
        JButton printButton = new JButton("Print");

        topPanel.add(insertField);
        topPanel.add(insertButton);
        topPanel.add(deleteField);
        topPanel.add(deleteButton);
        topPanel.add(findField);
        topPanel.add(findButton);
        topPanel.add(printButton);

        insertButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(insertField.getText());
                tree.insert(key);
                treePanel.repaint();
                insertField.setText("");
            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid integer");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(deleteField.getText());
                tree.delete(key);
                treePanel.repaint();
                deleteField.setText("");
            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid integer");
            }
        });

        findButton.addActionListener(e -> {
            try {
                int key = Integer.parseInt(findField.getText());
                boolean found = tree.find(key);
                JOptionPane.showMessageDialog(avlFrame, "Found: " + found);
                findField.setText("");
            } catch (NumberFormatException ex) {
                output.setText("Please enter a valid integer");
            }
        });

        printButton.addActionListener(e -> output.setText(tree.inOrder()));

        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        avlFrame.add(topPanel, BorderLayout.NORTH);
        avlFrame.add(treePanel, BorderLayout.CENTER);
        avlFrame.add(scrollPane, BorderLayout.SOUTH);

        avlFrame.setVisible(true);
    }

    // Expression Conversion Methods
    private static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        expression = expression.replaceAll("\\s+", "");

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isLetterOrDigit(c)) {
                result.append(c).append(" ");
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            result.append(stack.pop()).append(" ");
        }
        
        return result.toString().trim();
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
        return new StringBuilder(postfix).reverse().toString().replaceAll("\\s+", " ").trim();
    }

    private static String prefixToInfix(String expression) {
        Stack<String> stack = new Stack<>();
        expression = expression.replaceAll("\\s+", "");

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid prefix expression");
                }
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op1 + " " + c + " " + op2 + ")");
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid prefix expression");
        }
        
        return stack.pop();
    }

    private static String prefixToPostfix(String expression) {
        String infix = prefixToInfix(expression);
        return infixToPostfix(infix);
    }

    private static String postfixToInfix(String expression) {
        Stack<String> stack = new Stack<>();
        expression = expression.replaceAll("\\s+", " ").trim();

        for (String token : expression.split(" ")) {
            if (token.length() == 1) {
                char c = token.charAt(0);
                if (Character.isLetterOrDigit(c)) {
                    stack.push(token);
                } else {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid postfix expression");
                    }
                    String op2 = stack.pop();
                    String op1 = stack.pop();
                    stack.push("(" + op1 + " " + c + " " + op2 + ")");
                }
            } else {
                stack.push(token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        
        return stack.pop();
    }

    private static String postfixToPrefix(String expression) {
        Stack<String> stack = new Stack<>();
        expression = expression.replaceAll("\\s+", " ").trim();

        for (String token : expression.split(" ")) {
            if (token.length() == 1) {
                char c = token.charAt(0);
                if (Character.isLetterOrDigit(c)) {
                    stack.push(token);
                } else {
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid postfix expression");
                    }
                    String op2 = stack.pop();
                    String op1 = stack.pop();
                    stack.push(c + " " + op1 + " " + op2);
                }
            } else {
                stack.push(token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
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

    // AVL Tree Classes
    static class AVLTreePanel extends JPanel {
        AVLTree tree;

        public AVLTreePanel(AVLTree tree) {
            this.tree = tree;
            setPreferredSize(new Dimension(800, 500));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (tree.getRoot() != null) {
                drawTree((Graphics2D) g, tree.getRoot(), getWidth() / 2, 40, getWidth() / 4);
            }
        }

        private void drawTree(Graphics2D g, Node node, int x, int y, int xOffset) {
            if (node == null) return;

            g.setColor(new Color(200, 255, 200));
            g.fillOval(x - 25, y - 25, 50, 50);
            g.setColor(Color.GREEN.darker());
            g.drawOval(x - 25, y - 25, 50, 50);
            g.drawString(String.format("%04d", node.key), x - 18, y + 5);

            if (node.left != null) {
                g.setColor(Color.GREEN.darker());
                int childX = x - xOffset;
                int childY = y + 80;
                g.drawLine(x, y + 25, childX, childY - 25);
                g.drawString("L", (x + childX) / 2 - 10, (y + childY) / 2);
                drawTree(g, node.left, childX, childY, xOffset / 2);
            }

            if (node.right != null) {
                g.setColor(Color.GREEN.darker());
                int childX = x + xOffset;
                int childY = y + 80;
                g.drawLine(x, y + 25, childX, childY - 25);
                g.drawString("R", (x + childX) / 2 + 5, (y + childY) / 2);
                drawTree(g, node.right, childX, childY, xOffset / 2);
            }

            if (node.left != null || node.right != null) {
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(tree.height(node)), x - 5, y - 30);
            }
        }
    }

    static class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    static class AVLTree {
        private Node root;

        public Node getRoot() {
            return root;
        }

        int height(Node N) {
            return (N == null) ? 0 : N.height;
        }

        int getBalance(Node N) {
            return (N == null) ? 0 : height(N.left) - height(N.right);
        }

        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            return y;
        }

        Node insert(Node node, int key) {
            if (node == null) return new Node(key);
            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            else return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));
            int balance = getBalance(node);

            if (balance > 1 && key < node.left.key) return rightRotate(node);
            if (balance < -1 && key > node.right.key) return leftRotate(node);
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) current = current.left;
            return current;
        }

        Node delete(Node root, int key) {
            if (root == null) return root;
            if (key < root.key) root.left = delete(root.left, key);
            else if (key > root.key) root.right = delete(root.right, key);
            else {
                if ((root.left == null) || (root.right == null)) {
                    Node temp = (root.left != null) ? root.left : root.right;
                    if (temp == null) return null;
                    root = temp;
                } else {
                    Node temp = minValueNode(root.right);
                    root.key = temp.key;
                    root.right = delete(root.right, temp.key);
                }
            }

            root.height = Math.max(height(root.left), height(root.right)) + 1;
            int balance = getBalance(root);

            if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);
            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }

            if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);
            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }

            return root;
        }

        boolean find(int key) {
            return find(root, key);
        }

        boolean find(Node node, int key) {
            if (node == null) return false;
            if (key == node.key) return true;
            if (key < node.key) return find(node.left, key);
            return find(node.right, key);
        }

        void insert(int key) {
            root = insert(root, key);
        }

        void delete(int key) {
            root = delete(root, key);
        }

        String inOrder() {
            StringBuilder sb = new StringBuilder();
            inOrder(root, sb);
            return sb.toString();
        }

        void inOrder(Node node, StringBuilder sb) {
            if (node != null) {
                inOrder(node.left, sb);
                sb.append(node.key).append(" ");
                inOrder(node.right, sb);
            }
        }
    }
}
