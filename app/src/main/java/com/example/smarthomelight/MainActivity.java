package com.example.smarthomelight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutRaiz;
    private TextView tvEstadoLuz;
    private Button btnInterruptor;

    // Estado del interruptor
    private boolean luzEncendida = false;

    // Contador de pulsaciones
    private TextView tvContador;
    private int contadorPulsaciones = 0;

    // Modo noche
    private androidx.appcompat.widget.SwitchCompat switchModoNoche;
    private TextView tvTitulo;

    // Referencia al boton de panico del layout
    private Button btnPanico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conectamos las variables de Java con las vistas del XML
        layoutRaiz = findViewById(R.id.layoutRaiz);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvEstadoLuz = findViewById(R.id.tvEstadoLuz);
        btnInterruptor = findViewById(R.id.btnInterruptor);
        tvContador = findViewById(R.id.tvContador);
        switchModoNoche = findViewById(R.id.switchModoNoche);

        // Texto inicial del contador
        tvContador.setText(getString(R.string.texto_contador, contadorPulsaciones));

        // Listener del botón interruptor
        btnInterruptor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Alternamos el estado de la luz
                luzEncendida = !luzEncendida;

                if (luzEncendida) {
                    tvEstadoLuz.setText(R.string.estado_luz_encendida);
                    tvEstadoLuz.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.estado_encendido));
                } else {
                    tvEstadoLuz.setText(R.string.estado_luz_apagada);
                    tvEstadoLuz.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.estado_apagado));
                }

                // Incrementamos y actualizamos el contador
                contadorPulsaciones++;
                tvContador.setText(getString(R.string.texto_contador, contadorPulsaciones));
            }
        });

        // Listener del switch de modo noche
        switchModoNoche.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layoutRaiz.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.fondo_oscuro));
                    tvTitulo.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.texto_oscuro));
                } else {
                    layoutRaiz.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.fondo_claro));
                    tvTitulo.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.texto_claro));
                }
            }
        });
        // Enlazamos la variable con el boton del layout usando su id
        btnPanico = findViewById(R.id.btnPanico);
        // Asignamos la accion que se ejecuta al pulsar el boton
        btnPanico.setOnClickListener(new View.OnClickListener() {
            // Metodo que se llama cuando se pulsa el boton
            @Override
            public void onClick(View v) {
                // Mostramos el mensaje de emergencia durante un tiempo largo
                Toast.makeText(MainActivity.this, R.string.toast_alerta_panico, Toast.LENGTH_LONG).show();
            }
        });
    }
}