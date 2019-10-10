package com.example.adivinar_numero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int MINIMUM_RANGE = 1;
    private static final int MAXIMUM_RANGE = 20;
    private Attempts attempts;
    private final int RANDOM_NUMBER_GENERATED = generateRandom();
    private TextView textView_tracks;
    private TextView textView_attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load attempts
        int attempts_saved = Preferences.loadAttempts(this);
        if (attempts_saved == 0) {
            attempts = new Attempts(5);
        } else {
            attempts = new Attempts(attempts_saved);
        }
        //set textViews
        TextView textView_guess_number = findViewById(R.id.textView_guess_number);
        textView_guess_number.setText("Guess the number between " + MINIMUM_RANGE + " and " + MAXIMUM_RANGE);
        textView_attempts = findViewById(R.id.textView_attempts_win2);
        textView_attempts.setText(String.valueOf(attempts.getNumber_Attempts()));
    }

    public void onBackPressed() {
        Log.d("Mi App", "El usuario le ha dado para atrás");
        Preferences.saveAttempts(this, attempts.getNumber_Attempts());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * @return Devuelve un INT aleatorio
     */
    public int generateRandom() {
        int random = 0;
        random = (int) (Math.random() * (MAXIMUM_RANGE - MINIMUM_RANGE + 1)) + MINIMUM_RANGE;
        return random;
    }

    /**
     * @param view MainActivity.xml
     */
    public void checkAnswer(View view) {
        //Comporbar que el numero de intentos sea mayor que 0
        if (attempts.getNumber_Attempts() > 0) {
            EditText editText = findViewById(R.id.caja_edit_text);
            if (!editText.getText().toString().equals("")) {
                int numeroIntroducido = Integer.valueOf(editText.getText().toString());
                //1. Si numero mayor que random --> "Es más pequeño!"
                if (numeroIntroducido > RANDOM_NUMBER_GENERATED) {
                    attempts.substractOneAttempt();
                    textView_tracks = findViewById(R.id.textView_tracks);
                    textView_tracks.setText("Es más pequeño!.");
                    //2. Si numero menor que random --> "Es más grande!"
                } else if (numeroIntroducido < RANDOM_NUMBER_GENERATED) {
                    attempts.substractOneAttempt();
                    textView_tracks = findViewById(R.id.textView_tracks);
                    textView_tracks.setText("Es más grande!.");
                    //3. Si numero es ==  random --> Intent: has ganado
                } else if (numeroIntroducido == RANDOM_NUMBER_GENERATED) {
                    nextActivityWin();
                }
                editText.setText("");
            }
        }
        if (attempts.getNumber_Attempts() == 0) {
            nextActivityLose();
        }
        textView_attempts.setText(String.valueOf(attempts.getNumber_Attempts()));
        Preferences.saveAttempts(this, attempts.getNumber_Attempts());
    }

    /**
     * Starts next activityWin.class
     */
    public void nextActivityWin() {
        Intent intent = new Intent(this, WinActivity.class);
        //Show rest attempts
        intent.putExtra("attempts", attempts.getNumber_Attempts());
        intent.putExtra("answer", RANDOM_NUMBER_GENERATED);
        startActivity(intent);
    }

    /**
     * Starts next activityLose.class
     */
    public void nextActivityLose() {
        Intent intent = new Intent(this, LoseActivity.class);
        startActivity(intent);
    }
}

//TODO
    /*
    1.Al salir guardar el numero de intentos restantes. --> SharedPreferences.
    ....
        backburron activitWin.Clas ---> restart app.
        backburron activitLose.Clas ---> restart app.
    2. --i18n--
    --SI Gana---
    - 1. serializar un json con la partida, intentos, , rango minimo, rango maximo y una datetime
    - 2. guardarlo en un xml.
     */
