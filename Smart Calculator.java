import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartCalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double firstOperand, secondOperand, result;
    private boolean isOperatorClicked;

    public SmartCalculatorGUI() {
        setTitle("Smart Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display Panel
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isOperatorClicked) {
                display.setText(command);
                isOperatorClicked = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("C")) {
            display.setText("");
            operator = "";
            firstOperand = secondOperand = result = 0;
        } else if (command.equals("=")) {
            try {
                secondOperand = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        result = firstOperand / secondOperand;
                        break;
                }
                display.setText(String.valueOf(result));
                operator = "";
            } catch (NumberFormatException | ArithmeticException ex) {
                display.setText("Error: " + ex.getMessage());
            }
        } else {
            if (!display.getText().isEmpty()) {
                firstOperand = Double.parseDouble(display.getText());
                operator = command;
                isOperatorClicked = true;
            }
        }
    }

    public static void main(String[] args) {
        new SmartCalculatorGUI();
    }
}
