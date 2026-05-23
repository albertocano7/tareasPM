package com.example.miapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Calculadora extends Activity implements View.OnClickListener {
    Button[] btnDigitos = new Button[10];
    boolean pintarPunto = true;
    EditText pantalla;
    double op1, op2, res;
    Button btnSuma, btnResta, btnMulti, btnDiv, btnPunto, btnIgual, btnBorrar;
    String operacion = "";

    protected void onCreate(Bundle b) {
        super.onCreate(b);

        LinearLayout panelPrincipal = new LinearLayout(this);
        panelPrincipal.setBackgroundColor(Color.BLACK);
        panelPrincipal.setOrientation(LinearLayout.VERTICAL);

        // Pantalla
        LinearLayout panelPantalla = new LinearLayout(this);
        panelPantalla.setOrientation(LinearLayout.VERTICAL);
        panelPantalla.setBackgroundColor(Color.GRAY);

        TextView cuadro = new TextView(this);
        cuadro.setBackgroundColor(Color.BLUE);
        cuadro.setText(" ");
        cuadro.setMinimumHeight(50);

        pantalla = new EditText(this);
        pantalla.setTextColor(Color.WHITE);
        pantalla.setTextSize(40);
        pantalla.setMaxLines(1);
        pantalla.setTextAlignment(EditText.TEXT_ALIGNMENT_TEXT_END);
        pantalla.setFocusable(false); // Solo lectura, no editable a mano

        panelPantalla.addView(cuadro);
        panelPantalla.addView(pantalla);

        // Filas de dígitos
        LinearLayout li1 = new LinearLayout(this); // 7 8 9
        LinearLayout li2 = new LinearLayout(this); // 4 5 6
        LinearLayout li3 = new LinearLayout(this); // 1 2 3
        LinearLayout li4 = new LinearLayout(this); // 0 . =

        for (int i = 0; i <= 9; i++) {
            btnDigitos[i] = new Button(this);
            btnDigitos[i].setText(i + "");
            btnDigitos[i].setTextSize(18);
            btnDigitos[i].setOnClickListener(this);
            switch (i) {
                case 0:           li4.addView(btnDigitos[i]); break;
                case 1: case 2: case 3: li3.addView(btnDigitos[i]); break;
                case 4: case 5: case 6: li2.addView(btnDigitos[i]); break;
                case 7: case 8: case 9: li1.addView(btnDigitos[i]); break;
            }
        }

        // Punto e Igual en la fila 4
        btnPunto = crearBoton(".", Color.DKGRAY);
        btnIgual = crearBoton("=", Color.parseColor("#FF9800")); // naranja
        li4.addView(btnPunto);
        li4.addView(btnIgual);

        // Panel izquierdo: dígitos
        LinearLayout panelBotones1 = new LinearLayout(this);
        panelBotones1.setOrientation(LinearLayout.VERTICAL);
        panelBotones1.addView(li1);
        panelBotones1.addView(li2);
        panelBotones1.addView(li3);
        panelBotones1.addView(li4);

        // Columna derecha: operaciones
        btnSuma   = crearBoton("+", Color.parseColor("#FF9800"));
        btnResta  = crearBoton("−", Color.parseColor("#FF9800"));
        btnMulti  = crearBoton("×", Color.parseColor("#FF9800"));
        btnDiv    = crearBoton("÷", Color.parseColor("#FF9800"));
        btnBorrar = crearBoton("C", Color.RED);

        LinearLayout panelBotones2 = new LinearLayout(this);
        panelBotones2.setOrientation(LinearLayout.VERTICAL);
        panelBotones2.addView(btnBorrar);
        panelBotones2.addView(btnDiv);
        panelBotones2.addView(btnMulti);
        panelBotones2.addView(btnResta);
        panelBotones2.addView(btnSuma);

        // Ensamblado final
        LinearLayout panelControles = new LinearLayout(this);
        panelControles.addView(panelBotones1);
        panelControles.addView(panelBotones2);

        panelPrincipal.addView(panelPantalla);
        panelPrincipal.addView(panelControles);
        setContentView(panelPrincipal);
    }

    // Utilidad para crear botones de operación con color
    private Button crearBoton(String texto, int color) {
        Button btn = new Button(this);
        btn.setText(texto);
        btn.setTextSize(20);
        btn.setBackgroundColor(color);
        btn.setTextColor(Color.WHITE);
        btn.setOnClickListener(this);
        return btn;
    }

    @Override
    public void onClick(View v) {

        for (int i = 0; i <= 9; i++) {
            if (v.equals(btnDigitos[i])) {
                pantalla.setText(pantalla.getText().toString() + i);
                return;
            }
        }

        // Punto decimal
        if (v.equals(btnPunto)) {
            if (pintarPunto) {
                // Si la pantalla está vacía, agrega "0." automáticamente
                if (pantalla.getText().toString().isEmpty()) {
                    pantalla.setText("0.");
                } else {
                    pantalla.setText(pantalla.getText() + ".");
                }
                pintarPunto = false;
            }
            return;
        }

        // Borrar / Reset
        if (v.equals(btnBorrar)) {
            pantalla.setText("");
            op1 = op2 = res = 0;
            operacion = "";
            pintarPunto = true;
            return;
        }

        // Operadores: guardan op1 y el tipo de operación
        if (v.equals(btnSuma) || v.equals(btnResta) ||
                v.equals(btnMulti) || v.equals(btnDiv)) {

            String texto = pantalla.getText().toString();
            if (texto.isEmpty()) return; // Evita crash si pantalla vacía

            op1 = Double.parseDouble(texto);
            pantalla.setText("");
            pintarPunto = true;

            if (v.equals(btnSuma))  operacion = "+";
            if (v.equals(btnResta)) operacion = "-";
            if (v.equals(btnMulti)) operacion = "*";
            if (v.equals(btnDiv))   operacion = "/";
            return;
        }

        // Igual: calcula según la operación guardada
        if (v.equals(btnIgual)) {
            String texto = pantalla.getText().toString();
            if (texto.isEmpty() || operacion.isEmpty()) return;

            op2 = Double.parseDouble(texto);
            pintarPunto = true;

            switch (operacion) {
                case "+": res = op1 + op2; break;
                case "-": res = op1 - op2; break;
                case "*": res = op1 * op2; break;
                case "/":
                    if (op2 == 0) {
                        pantalla.setText("Error"); // División entre cero
                        operacion = "";
                        return;
                    }
                    res = op1 / op2;
                    break;
            }

            // Muestra entero si no tiene decimales (ej: 6.0 → 6)
            if (res == (long) res) {
                pantalla.setText(String.valueOf((long) res));
            } else {
                pantalla.setText(String.valueOf(res));
            }
            operacion = "";
        }
    }
}