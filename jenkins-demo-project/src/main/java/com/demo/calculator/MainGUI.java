package com.demo.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class MainGUI extends JFrame {

    private Calculatrice calc = new Calculatrice();
    private JTextField display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean newInput = false;

    // ── Couleurs ──────────────────────────────────────────
    private static final Color BG_DARK      = new Color(18, 18, 18);
    private static final Color BG_PANEL     = new Color(28, 28, 30);
    private static final Color BTN_NUMBER   = new Color(45, 45, 48);
    private static final Color BTN_OP       = new Color(255, 149, 0);
    private static final Color BTN_FUNC     = new Color(58, 58, 60);
    private static final Color BTN_EQUAL    = new Color(255, 149, 0);
    private static final Color TEXT_WHITE   = new Color(255, 255, 255);
    private static final Color TEXT_GRAY    = new Color(160, 160, 160);
    private static final Color DISPLAY_BG   = new Color(22, 22, 24);
    private static final Color JENKINS_RED  = new Color(204, 41, 54);

    public MainGUI() {
        setTitle("Calculatrice Jenkins");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(380, 620);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout(0, 0));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildBody(), BorderLayout.CENTER);

        setVisible(true);
    }

    // ── HEADER ────────────────────────────────────────────
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_DARK);
        header.setBorder(BorderFactory.createEmptyBorder(18, 20, 0, 20));

        // Badge Jenkins
        JPanel badge = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        badge.setBackground(BG_DARK);
        JLabel jenkinsLabel = new JLabel("⚙ Jenkins Demo");
        jenkinsLabel.setFont(new Font("Arial", Font.BOLD, 11));
        jenkinsLabel.setForeground(JENKINS_RED);
        badge.add(jenkinsLabel);
        header.add(badge, BorderLayout.WEST);

        // Sous-titre
        JLabel sub = new JLabel("CI/CD Pipeline");
        sub.setFont(new Font("Arial", Font.PLAIN, 11));
        sub.setForeground(TEXT_GRAY);
        sub.setHorizontalAlignment(SwingConstants.RIGHT);
        header.add(sub, BorderLayout.EAST);

        return header;
    }

    // ── CORPS PRINCIPAL ───────────────────────────────────
    private JPanel buildBody() {
        JPanel body = new JPanel(new BorderLayout(0, 0));
        body.setBackground(BG_DARK);
        body.setBorder(BorderFactory.createEmptyBorder(10, 16, 16, 16));

        body.add(buildDisplay(), BorderLayout.NORTH);
        body.add(buildButtons(), BorderLayout.CENTER);

        return body;
    }

    // ── ÉCRAN ─────────────────────────────────────────────
    private JPanel buildDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DISPLAY_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 50, 52), 1),
            BorderFactory.createEmptyBorder(16, 20, 12, 20)
        ));

        // Ligne d'opération (en haut)
        JLabel opLabel = new JLabel(" ");
        opLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        opLabel.setForeground(TEXT_GRAY);
        opLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        opLabel.setName("opLabel");
        panel.add(opLabel, BorderLayout.NORTH);

        // Champ principal
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
      	display.setFont(new Font("Arial", Font.PLAIN, 52));
        display.setForeground(TEXT_WHITE);
        display.setBackground(DISPLAY_BG);
        display.setBorder(BorderFactory.createEmptyBorder());
        display.setEditable(false);
        display.setCaretColor(DISPLAY_BG);
        panel.add(display, BorderLayout.CENTER);

        return panel;
    }

    // ── GRILLE DE BOUTONS ─────────────────────────────────
    private JPanel buildButtons() {
        JPanel grid = new JPanel(new GridLayout(5, 4, 10, 10));
        grid.setBackground(BG_DARK);
        grid.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));

        String[][] layout = {
            {"AC", "+/-", "%",  "÷"},
            {"7",  "8",   "9",  "×"},
            {"4",  "5",   "6",  "-"},
            {"1",  "2",   "3",  "+"},
            {"0",  ".",   "⌫",  "="},
        };

        for (String[] row : layout) {
            for (String label : row) {
                grid.add(makeButton(label));
            }
        }

        return grid;
    }

    // ── FABRIQUE DE BOUTONS ───────────────────────────────
    private JButton makeButton(String label) {
        Color bg, fg;

        if (label.equals("=")) {
            bg = BTN_EQUAL; fg = TEXT_WHITE;
        } else if (label.matches("[÷×\\-+]")) {
            bg = BTN_OP; fg = TEXT_WHITE;
        } else if (label.matches("AC|\\+/-|%|⌫")) {
            bg = BTN_FUNC; fg = TEXT_WHITE;
        } else {
            bg = BTN_NUMBER; fg = TEXT_WHITE;
        }

        JButton btn = new JButton(label) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c = getModel().isPressed()
                    ? bg.darker()
                    : getModel().isRollover() ? bg.brighter() : bg;
                g2.setColor(c);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setFont(new Font("Arial", label.equals("AC") ? Font.BOLD : Font.PLAIN, 20));
        btn.setForeground(fg);
        btn.setBackground(bg);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(70, 70));

        btn.addActionListener(e -> handleButton(label));
        return btn;
    }

    // ── LOGIQUE DES BOUTONS ───────────────────────────────
    private void handleButton(String label) {
        switch (label) {
            case "AC":
                currentInput = "";
                operator = "";
                firstNumber = 0;
                newInput = false;
                display.setText("0");
                updateOpLabel("");
                break;

            case "⌫":
                if (currentInput.length() > 1) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                } else {
                    currentInput = "0";
                }
                display.setText(currentInput);
                break;

            case "+/-":
                if (!display.getText().equals("0")) {
                    if (display.getText().startsWith("-")) {
                        display.setText(display.getText().substring(1));
                        currentInput = display.getText();
                    } else {
                        display.setText("-" + display.getText());
                        currentInput = display.getText();
                    }
                }
                break;

            case "%":
                double pct = Double.parseDouble(display.getText()) / 100;
                display.setText(formatResult(pct));
                currentInput = display.getText();
                break;

            case ".":
                if (!currentInput.contains(".")) {
                    currentInput = currentInput.isEmpty() ? "0." : currentInput + ".";
                    display.setText(currentInput);
                }
                break;

            case "+": case "-": case "×": case "÷":
                firstNumber = Double.parseDouble(display.getText());
                operator = label;
                newInput = true;
                updateOpLabel(formatResult(firstNumber) + " " + label);
                break;

            case "=":
                if (!operator.isEmpty() && !currentInput.isEmpty()) {
                    double second = Double.parseDouble(display.getText());
                    double result = 0;
                    try {
                        result = switch (operator) {
                            case "+" -> calc.additionner((int)firstNumber, (int)second);
                            case "-" -> calc.soustraire((int)firstNumber, (int)second);
                            case "×" -> calc.multiplier((int)firstNumber, (int)second);
                            case "÷" -> calc.diviser((int)firstNumber, (int)second);
                            default  -> 0;
                        };
                        updateOpLabel(formatResult(firstNumber) + " " + operator + " " + formatResult(second) + " =");
                        display.setText(formatResult(result));
                        currentInput = display.getText();
                        operator = "";
                        newInput = false;
                    } catch (ArithmeticException ex) {
                        display.setText("Erreur");
                        updateOpLabel("Division par zéro !");
                        currentInput = "";
                        operator = "";
                    }
                }
                break;

            default: // chiffres
                if (newInput) {
                    currentInput = label;
                    newInput = false;
                } else {
                    currentInput = currentInput.equals("0") ? label : currentInput + label;
                }
                display.setText(currentInput);
                break;
        }
    }

    private String formatResult(double val) {
        if (val == (long) val) return String.valueOf((long) val);
        return String.valueOf(val);
    }

    private void updateOpLabel(String text) {
        JPanel displayPanel = (JPanel) display.getParent();
        for (Component c : displayPanel.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setText(text.isEmpty() ? " " : text);
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
