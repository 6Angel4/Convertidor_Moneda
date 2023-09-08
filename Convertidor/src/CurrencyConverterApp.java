import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterApp extends JFrame {
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JTextField amountField;
    private JLabel resultLabel;

    // Currency exchange rates (as of your knowledge cutoff date)
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    static {
        exchangeRates.put("Mexico (MXN)", 17.59);
        exchangeRates.put("United States (USD)", 0.5);
        exchangeRates.put("China (CNY)", 6.45);
        exchangeRates.put("England (GBP)", 0.74);
        exchangeRates.put("Japan (JPY)", 110.30);
    }

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        fromCurrencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrencyComboBox = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        amountField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        // Create and set up the layout
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("From Currency:"));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("To Currency:"));
        panel.add(toCurrencyComboBox);
        panel.add(new JLabel("Result:"));
        panel.add(resultLabel);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Add components to the frame
        add(panel, BorderLayout.CENTER);
        add(convertButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void convertCurrency() {
        String fromCurrency = fromCurrencyComboBox.getSelectedItem().toString();
        String toCurrency = toCurrencyComboBox.getSelectedItem().toString();

        try {
            double amount = Double.parseDouble(amountField.getText());
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);
            double convertedAmount = amount * (toRate / fromRate);

            DecimalFormat df = new DecimalFormat("#.##");
            resultLabel.setText(df.format(amount) + " " + fromCurrency + " = " + df.format(convertedAmount) + " " + toCurrency);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CurrencyConverterApp();
        });
    }
}

