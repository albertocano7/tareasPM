package com.example.proyectofinalpm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.BLACK);
        layout.setGravity(android.view.Gravity.CENTER);
        layout.setPadding(60, 80, 60, 80);

        Button btnJuego = new Button(this);
        btnJuego.setText("Mi App");
        btnJuego.setTextSize(22);
        btnJuego.setTextColor(Color.WHITE);
        btnJuego.setBackgroundColor(Color.parseColor("#4CAF50"));
        btnJuego.setPadding(40, 30, 40, 30);

        Button btnCreditos = new Button(this);
        btnCreditos.setText("Créditos");
        btnCreditos.setTextSize(22);
        btnCreditos.setTextColor(Color.WHITE);
        btnCreditos.setBackgroundColor(Color.parseColor("#2196F3"));
        btnCreditos.setPadding(40, 30, 40, 30);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 30, 0, 30);

        layout.addView(btnJuego, params);
        layout.addView(btnCreditos, params);

        setContentView(layout);

        btnJuego.setOnClickListener(v ->
                startActivity(new Intent(this, Snake.class)));

        btnCreditos.setOnClickListener(v ->
                startActivity(new Intent(this, Creditos.class)));
    }
}