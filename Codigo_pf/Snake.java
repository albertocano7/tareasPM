package com.example.proyectofinalpm;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Snake extends Activity {

    VistaSnake vistaSnake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layoutPrincipal = new LinearLayout(this);
        layoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        layoutPrincipal.setBackgroundColor(Color.BLACK);

        // Título
        TextView titulo = new TextView(this);
        titulo.setText("SNAKE");
        titulo.setTextSize(24);
        titulo.setTextColor(Color.parseColor("#4CAF50"));
        titulo.setGravity(Gravity.CENTER);
        titulo.setPadding(0, 20, 0, 10);
        layoutPrincipal.addView(titulo);

        // Área del juego
        vistaSnake = new VistaSnake(this);
        LinearLayout.LayoutParams paramsJuego = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
        layoutPrincipal.addView(vistaSnake, paramsJuego);

        // Botón ARRIBA
        LinearLayout filaArriba = new LinearLayout(this);
        filaArriba.setGravity(Gravity.CENTER);
        Button btnArriba = crearBoton("▲");
        filaArriba.addView(btnArriba);
        layoutPrincipal.addView(filaArriba);

        // Botones IZQUIERDA y DERECHA
        LinearLayout filaMedio = new LinearLayout(this);
        filaMedio.setGravity(Gravity.CENTER);
        filaMedio.setOrientation(LinearLayout.HORIZONTAL);

        Button btnIzquierda = crearBoton("◄");
        Button btnDerecha   = crearBoton("►");

        // Espacio entre los botones laterales
        LinearLayout.LayoutParams paramsEspacio = new LinearLayout.LayoutParams(
                120, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView espacio = new TextView(this);

        filaMedio.addView(btnIzquierda);
        filaMedio.addView(espacio, paramsEspacio);
        filaMedio.addView(btnDerecha);
        layoutPrincipal.addView(filaMedio);

        // Botón ABAJO
        LinearLayout filaAbajo = new LinearLayout(this);
        filaAbajo.setGravity(Gravity.CENTER);
        Button btnAbajo = crearBoton("▼");
        filaAbajo.addView(btnAbajo);
        layoutPrincipal.addView(filaAbajo);

        // Espacio inferior
        TextView margenAbajo = new TextView(this);
        margenAbajo.setPadding(0, 0, 0, 20);
        layoutPrincipal.addView(margenAbajo);

        setContentView(layoutPrincipal);

        // Listeners de dirección
        btnArriba.setOnClickListener(v    -> vistaSnake.cambiarDireccion(0, -1));
        btnAbajo.setOnClickListener(v     -> vistaSnake.cambiarDireccion(0,  1));
        btnIzquierda.setOnClickListener(v -> vistaSnake.cambiarDireccion(-1, 0));
        btnDerecha.setOnClickListener(v   -> vistaSnake.cambiarDireccion(1,  0));
    }

    private Button crearBoton(String texto) {
        Button btn = new Button(this);
        btn.setText(texto);
        btn.setTextSize(26);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundColor(Color.parseColor("#4CAF50"));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                160, 160);
        params.setMargins(10, 10, 10, 10);
        btn.setLayoutParams(params);
        return btn;
    }

    @Override
    protected void onPause()  { super.onPause();  vistaSnake.pausar();   }

    @Override
    protected void onResume() { super.onResume(); vistaSnake.reanudar(); }
}