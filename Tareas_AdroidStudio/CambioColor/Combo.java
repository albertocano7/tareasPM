////////////controlador
package com.example.funciones;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Combo extends Activity {
    Spinner spinnerColores;
    EditText textoUno;

    public void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.vista);

        spinnerColores = findViewById(R.id.textospinner);
        textoUno = findViewById(R.id.textouno);

        String colores[] = {"Rojo", "Verde", "Azul"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colores);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColores.setAdapter(adapter);

        spinnerColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Rojo
                        textoUno.setTextColor(Color.RED);
                        break;
                    case 1: // Verde
                        textoUno.setTextColor(Color.GREEN);
                        break;
                    case 2: // Azul
                        textoUno.setTextColor(Color.BLUE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

