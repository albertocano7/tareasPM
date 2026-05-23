package com.example.miapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout li = new LinearLayout(this);
        li.setOrientation(LinearLayout.VERTICAL);
        li.setBackgroundColor(Color.GREEN);

        Button btnMusica = new Button(this);
        btnMusica.setText("Animales");
        btnMusica.setPadding(30,50,30,50);


        Button btnCalculadora = new Button(this);
        btnCalculadora.setText("Calculadora");
        btnCalculadora.setPadding(30,50,30,50);

        btnCalculadora.setOnClickListener(v->{
            Intent i = new Intent(this, Calculadora.class);
            startActivity(i);
        });

        li.addView(btnMusica);
        li.addView(btnCalculadora);
        setContentView(li);
    }
}