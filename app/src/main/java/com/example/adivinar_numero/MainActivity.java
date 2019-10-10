package com.example.adivinar_numero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int MINIMUM_RANGE = 0;
    private static final int MAXIMUM_RANGE = 20;
    private Attempts attempts = new Attempts(5);
    private final int RANDOM_NUMBER_GENERATED = generateRandom();
    private TextView textView_tracks;

    //TODO
    /*
    1. Generar random.
    2. introducir numeor de intentos.
    3. comprobar numero introducido
        3.1 acierto -> (NuevoActivity) intent.explicito: música + gif.
        3.2 fallar -> (NuevoActivity) intent.explicito: música + gif.
    4.Al salir guardar el numero de intentos restantes. --> SharedPreferences.
    ....
    5. Crear menu con partidas guardadas? --> Lenguaje de marcas.

    --i18n--
    Strings...
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int generateRandom(){
        int random = 0;
        random = (int)(Math.random()*(MAXIMUM_RANGE - MINIMUM_RANGE +1)) + MINIMUM_RANGE;
        return random;
    }

    public void checkAnswer(View view) {
        //Comporbar que el numero de intentos sea mayor que 0
        if (attempts.getNumber_Attempts() > 0){
            EditText editText = findViewById(R.id.caja_edit_text);
            int numeroIntroducido = Integer.valueOf(editText.getText().toString());
            //1. Si numero mayor que random --> "Es más pequeño!"
            if (numeroIntroducido > RANDOM_NUMBER_GENERATED ){
                attempts.substractOneAttempt();
                textView_tracks = findViewById(R.id.textView_tracks);
                textView_tracks.setText("Es más pequeño!.");
                //2. Si numero menor que random --> "Es más grande!"
            }else if(numeroIntroducido < RANDOM_NUMBER_GENERATED){
                attempts.substractOneAttempt();
                textView_tracks = findViewById(R.id.textView_tracks);
                textView_tracks.setText("Es más grande!.");
                //3. Si numero es ==  random --> Intent: has ganado
            } else if (numeroIntroducido == RANDOM_NUMBER_GENERATED){
                nextActivityWin();
            }
        }else{
            //Intent: has perdido
            nextActivityLose();
        }
    }

    public void nextActivityWin(){
        Intent intent = new Intent(this, WinActivity.class);
        //Show rest attempts
        intent.putExtra("attempts", attempts.getNumber_Attempts());
        startActivity(intent);
    }

    public void nextActivityLose(){
        Intent intent = new Intent(this, LoseActivity.class);
        startActivity(intent);
    }

//TODO
    /*
    1. PreferenceShared.
    2. Menu partidas guardadas.
    3. Validar Preferences.
    4. intent putInt: intentos.

     */
}
