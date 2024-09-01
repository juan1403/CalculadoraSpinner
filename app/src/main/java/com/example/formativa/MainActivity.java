package com.example.formativa;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1, etNumero2;
    private Spinner SpOperaciones;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        SpOperaciones = findViewById(R.id.SpOperaciones);
        btnCalcular = findViewById(R.id.btnCalcular);

        String[] operaciones = {"Suma", "Resta", "Multiplicación", "División"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operaciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpOperaciones.setAdapter(adapter);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularYEnviarResultado();
            }
        });
    }

    private void calcularYEnviarResultado() {
        String num1Str = etNumero1.getText().toString();
        String num2Str = etNumero2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        String OpeSeleccionada = SpOperaciones.getSelectedItem().toString();
        double resultado = 0;

        switch (OpeSeleccionada) {
            case "Suma":
                resultado = num1 + num2;
                break;
            case "Resta":
                resultado = num1 - num2;
                break;
            case "Multiplicación":
                resultado = num1 * num2;
                break;
            case "División":
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        Intent intent = new Intent(MainActivity.this, ResultadoActividad.class);
        intent.putExtra("resultado", String.valueOf(resultado));
        startActivity(intent);
    }
}

