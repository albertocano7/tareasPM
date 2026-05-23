package com.example.fragmento;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        listado = findViewById(R.id.listado);

        // Lista de animales (mismos que en el proyecto Lista)
        String[] animales = {"Gato", "Perro", "Vaca"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                animales
        );

        listado.setAdapter(adapter);

        // Al seleccionar un animal, reemplaza el fragmento de abajo
        listado.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    remplazarFragmento(new FragmentoGato());
                    break;
                case 1:
                    remplazarFragmento(new FragmentoPerro());
                    break;
                case 2:
                    remplazarFragmento(new FragmentoVaca());
                    break;
            }
        });
    }

    public void remplazarFragmento(Fragment fragmento) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenedorFragmentos, fragmento)
                .commit();
    }
}