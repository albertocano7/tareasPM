package com.example.proyectofinalpm;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

public class Creditos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.BLACK);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(50, 60, 50, 60);

        // Título
        TextView titulo = new TextView(this);
        titulo.setText("CRÉDITOS");
        titulo.setTextSize(30);
        titulo.setTextColor(Color.parseColor("#4CAF50"));
        titulo.setTypeface(null, Typeface.BOLD);
        titulo.setGravity(Gravity.CENTER);
        titulo.setPadding(0, 0, 0, 40);
        layout.addView(titulo);

        // Datos
        agregarDato(layout, "Escuela", "Universidad Nacional Autónoma de México\nFacultad de Estudios Superiores Aragón");
        agregarDato(layout, "Materia", "Programación Móvil 1");
        agregarDato(layout, "Alumno", "Cano Mendoza Alberto");
        agregarDato(layout, "Número de cuenta", "42309533-7");
        agregarDato(layout, "Profesor", "Juan Carlos Camacho Alvarez");
        agregarDato(layout, "Grupo", "2807");
        agregarDato(layout, "Semestre", "2026-II");

        Button btnRegresar = new Button(this);
        btnRegresar.setText("Regresar");
        btnRegresar.setTextSize(18);
        btnRegresar.setTextColor(Color.WHITE);
        btnRegresar.setBackgroundColor(Color.parseColor("#F44336"));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 50, 0, 0);
        layout.addView(btnRegresar, params);

        setContentView(layout);

        btnRegresar.setOnClickListener(v -> finish());
    }

    private void agregarDato(LinearLayout layout, String etiqueta, String valor) {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 10);

        TextView tvEtiqueta = new TextView(this);
        tvEtiqueta.setText(etiqueta.toUpperCase());
        tvEtiqueta.setTextSize(12);
        tvEtiqueta.setTextColor(Color.parseColor("#4CAF50"));
        tvEtiqueta.setTypeface(null, Typeface.BOLD);
        tvEtiqueta.setGravity(Gravity.CENTER);

        TextView tvValor = new TextView(this);
        tvValor.setText(valor);
        tvValor.setTextSize(18);
        tvValor.setTextColor(Color.WHITE);
        tvValor.setGravity(Gravity.CENTER);

        fila.addView(tvEtiqueta);
        fila.addView(tvValor);
        layout.addView(fila, params);
    }
}