package com.example.adivinar_numero;

public class Attempts {
    //Atributos
    protected int number_Attempts;

    //Construcotres
    protected Attempts(int number_Attempts) {
        this.number_Attempts = number_Attempts;
    }
    protected Attempts() {
        this.number_Attempts = 5;
    }

    protected int getNumber_Attempts() {
        return number_Attempts;
    }

    protected void substractOneAttempt(){
        this.number_Attempts--;
    }
}
