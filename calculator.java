import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField display;

    public CalculatorApp() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField(10);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        pack();
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals("=")) {
            String expression = display.getText();
            try {
                double result = evaluateExpression(expression);
                display.setText(Double.toString(result));
            } catch (ArithmeticException ex) {
                display.setText("Error");
            }
        } else {
            display.setText(display.getText() + action);
        }
    }

    private double evaluateExpression(String expression) {
        // Evaluates the mathematical expression using appropriate logic
        // You can implement your own expression evaluation logic here
        return new Object() {
            int position = -1, ch;

            void nextChar() {
                ch = (++position < expression.length()) ? expression.charAt(position) : -1;
            }

            boolean isDigit() {
                return Character.isDigit(ch);
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (position < expression.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) {
                        x *= parseFactor();
                    } else if (eat('/')) {
                        x /= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) {
                    return parseFactor();
                }
                if (eat('-')) {
                    return -parseFactor();
                }

                double x;
                int startPos = this.position;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if (isDigit() || ch == '.') {
                    while (isDigit() || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(expression.substring(startPos, this.position));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
        }.parse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CalculatorApp calculator = new CalculatorApp();
                calculator.setVisible(true);
            }
        });
    }
}
