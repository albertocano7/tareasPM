//Cano Mendoza Alberto

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calculadoraGrafica extends JFrame {

    JTextField num1, num2;
    JButton btnSumar, btnRestar, btnMultiplicar, btnDividir;
    JLabel resultado;
    Font fuente = new Font("Arial", 1, 60);

    public calculadoraGrafica() {
        setTitle("casio");
        setSize(400, 400);
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(4, 1));

        num1 = new JTextField(10);
        num1.setFont(fuente);
        num2 = new JTextField(10);
        num2.setFont(fuente);

        btnSumar = new JButton("+");
        btnRestar = new JButton("-");
        btnMultiplicar = new JButton("x");
        btnDividir = new JButton("/");

        JPanel pOpciones = new JPanel();
        pOpciones.add(btnSumar);
        pOpciones.add(btnRestar);
        pOpciones.add(btnMultiplicar);
        pOpciones.add(btnDividir);

        resultado = new JLabel();
        resultado.setFont(fuente);

        add(num1);
        add(num2);
        add(pOpciones);
        add(resultado);

        // 🔹 Todo con lambdas

        btnSumar.addActionListener(e -> {
            int n1 = Integer.parseInt(num1.getText());
            int n2 = Integer.parseInt(num2.getText());
            resultado.setText((n1 + n2) + "");
        });

        btnRestar.addActionListener(e -> {
            int n1 = Integer.parseInt(num1.getText());
            int n2 = Integer.parseInt(num2.getText());
            resultado.setText((n1 - n2) + "");
        });

        btnMultiplicar.addActionListener(e -> {
            double n1 = Double.parseDouble(num1.getText());
            double n2 = Double.parseDouble(num2.getText());
            resultado.setText((n1 * n2) + "");
        });

        btnDividir.addActionListener(e -> {
            double n1 = Double.parseDouble(num1.getText());
            double n2 = Double.parseDouble(num2.getText());
            if (n2 == 0) {
                resultado.setText("Error");
            } else {
                resultado.setText((n1 / n2) + "");
            }
        });
    }

    public static void main(String[] args) {
        calculadoraGrafica cg = new calculadoraGrafica();
        cg.setVisible(true);
    }
}