package calculadoraapp.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Calculadora {

    private static final List<String> simbolosBtn = List.of("+", "-", "*", "/");
    private static final List<String> simboloOp = List.of("AC", ".", "0", "=");
    private static final List<String> numberOrder = List.of("7", "8", "9", "4", "5", "6", "1", "2", "3");

    private static String operacion;
    private static String inputActual = "";
    private static Double numero = null;
    private static Double numero2 = null;
    private static JLabel result;


    private  static final Color COLOR = Color.pink;
    private  static final Color COLORLETRA = Color.BLACK;
    private static final JLabel operacionLabel = new JLabel(" ", SwingConstants.RIGHT);

    public static void showView() {
        JFrame frame = new JFrame("Calculadora");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());


        JPanel panelLabel = new JPanel(new GridLayout(2, 1));






        JPanel botones = new JPanel(new GridLayout(5, 3, 5, 5));
        JPanel operaciones = new JPanel(new GridLayout(4, 1 ));


        operacionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        result = new JLabel("0", SwingConstants.RIGHT);
        result.setFont(new Font("Arial", Font.BOLD, 24));

        panelLabel.add(operacionLabel);
        panelLabel.add(result);

        // Botones numéricos
        for (String numeroTexto : numberOrder) {
            JButton btn = new JButton(numeroTexto);
            btn.setPreferredSize(new Dimension(50, 25));

            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.setBackground(COLOR);
            btn.setFont(new Font("Arial",Font.BOLD,25));

            btn.addActionListener(e -> {
                inputActual += numeroTexto;
                result.setText(inputActual);
                updateOperacionLabel();
            });

            botones.add(btn);
        }

        // Botones de operaciones + - * /
        for (String simbolo : simbolosBtn) {
            JButton operacionesBtn = new JButton(simbolo);
            operacionesBtn.setPreferredSize(new Dimension(100, 10));
            operacionesBtn.setBackground(COLOR);
            operacionesBtn.setFont(new Font("Arial",Font.ITALIC,25));


            operacionesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            operacionesBtn.addActionListener(e -> {
                if (!inputActual.isEmpty()) {
                    if (numero != null && operacion != null) {
                        numero2 = Double.parseDouble(inputActual);
                        numero = operacionesFuncion(numero, numero2);
                        result.setText(String.valueOf(numero));
                    } else {
                        numero = Double.parseDouble(inputActual);
                    }
                    inputActual = "";
                }

                operacion = e.getActionCommand();
                updateOperacionLabel();
            });

            operaciones.add(operacionesBtn);
        }

        // Botones especiales: AC, ., 0, =
        for (String simbolo : simboloOp) {
            JButton operacionesBtn2 = new JButton(simbolo);

            operacionesBtn2.setCursor(new Cursor(Cursor.HAND_CURSOR));

            operacionesBtn2.setBackground(COLOR);
            operacionesBtn2.setFont(new Font("Arial",Font.BOLD,25));
            operacionesBtn2.addActionListener(e -> switchOperaciones(simbolo));
            botones.add(operacionesBtn2);
        }

        panelPrincipal.add(panelLabel, BorderLayout.NORTH);
        panelPrincipal.add(botones, BorderLayout.CENTER);
        panelPrincipal.add(operaciones, BorderLayout.EAST);

        frame.add(panelPrincipal);
        panelLabel.setBackground(new Color(34, 139, 34)); // Verde oscuro
        frame.setBackground(new Color(34, 139, 34));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void switchOperaciones(String simbolo) {
        switch (simbolo) {
            case "AC" -> {
                inputActual = "";
                numero = null;
                numero2 = null;
                operacion = null;
                result.setText("0");
                operacionLabel.setText(" ");
            }
            case "." -> {
                if (!inputActual.contains(".")) {
                    inputActual += ".";
                    result.setText(inputActual);
                    updateOperacionLabel();
                }
            }
            case "0" -> {
                inputActual += "0";
                result.setText(inputActual);
                updateOperacionLabel();
            }
            case "=" -> {
                if (inputActual.isEmpty() || operacion == null || numero == null) return;

                numero2 = Double.parseDouble(inputActual);
                Double resultado = operacionesFuncion(numero, numero2);

                operacionLabel.setText(numero + " " + operacion + " " + numero2 + " =");
                result.setText(String.valueOf(resultado));

                numero = resultado;
                inputActual = "";
                numero2 = null;
                operacion = null;
            }
        }
    }

    public static double operacionesFuncion(double n1, double n2) {
        return switch (operacion) {
            case "+" -> n1 + n2;
            case "-" -> n1 - n2;
            case "*" -> n1 * n2;
            case "/" -> n2 != 0 ? n1 / n2 : 0;
            default -> 0;
        };
    }

    private static void updateOperacionLabel() {
        if (numero != null && operacion != null) {
            operacionLabel.setText(numero + " " + operacion + " " + inputActual);
            System.out.println("ff");
        } else {
            operacionLabel.setText(inputActual);
        }
    }

    public static void main(String[] args) {
        showView();
    }
}
