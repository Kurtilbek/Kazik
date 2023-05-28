package com.example.kazik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;

public class KamikadzeActivity extends AppCompatActivity {
    TextView balanceNumber;
    TextView b1, b2, b3, b4, b5, b6, b7, b8, b9, gen;
    int[] selectedNumbers = new int[3];
    public int c;
    int mm;
    Random random = new Random();
    SharedPreferences rPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamikadze);

        b1 = findViewById(R.id._1);
        b2 = findViewById(R.id._2);
        b3 = findViewById(R.id._3);
        b4 = findViewById(R.id._4);
        b5 = findViewById(R.id._5);
        b6 = findViewById(R.id._6);
        b7 = findViewById(R.id._7);
        b8 = findViewById(R.id._8);
        b9 = findViewById(R.id._9);
        gen = findViewById(R.id.generate);

        balanceNumber = findViewById(R.id.Text_4);
        // Получаем SharedPreferences, созданные в MainActivity
        rPref = getSharedPreferences("moneyData", MODE_PRIVATE);
        mm = rPref.getInt(MainActivity.MONEY, 10000);

        balanceNumber.setText(String.valueOf(mm));
        toLock();
        if(mm<=0){
            gen.setClickable(false);
            gen.setBackgroundResource(R.drawable.style_offnik);
            toLock();
        }
        if(mm<=999){
            gen.setClickable(false);
            toLock();
            gen.setBackgroundResource(R.drawable.style_offnik);
            Toast.makeText(this,getString(R.string.WhatMoney), Toast.LENGTH_SHORT).show();
        }
    }
    public void tob1 (View view){
        checkNumber(1);
        b1.setClickable(false);
        b1.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob2 (View view){
        checkNumber(2);
        b2.setClickable(false);
        b2.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob3 (View view){
        checkNumber(3);
        b3.setClickable(false);
        b3.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob4 (View view){
        checkNumber(4);
        b4.setClickable(false);
        b4.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob5 (View view){
        checkNumber(5);
        b5.setClickable(false);
        b5.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob6 (View view){
        checkNumber(6);
        b6.setClickable(false);
        b6.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob7 (View view){
        checkNumber(7);
        b7.setClickable(false);
        b7.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob8 (View view){
        checkNumber(8);
        b8.setClickable(false);
        b8.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }
    public void tob9 (View view){
        checkNumber(9);
        b9.setClickable(false);
        b9.setBackgroundResource(R.drawable.style_offnik);
        c++;
    }

    public void Vichislenia(View view){
        toUnlock();

        //Создаем массив чисел от 1 до 9
        int[] randomNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        //Перемешиваем массив
        for (int i = randomNumbers.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            //Меняем местами элементы с индексами i и index
            int temp = randomNumbers[i];
            randomNumbers[i] = randomNumbers[index];
            randomNumbers[index] = temp;
            //Берем первые три числа
            selectedNumbers = Arrays.copyOfRange(randomNumbers, 0, 3);
        }
    }

    public void checkNumber ( int number){
        if(c==3){
            toLock();
        }
        //Проверка на привильность с использованием флагов
        boolean foundMatch = false;
        for (int i = 0; i < selectedNumbers.length; i++) {
            if (selectedNumbers[i] == number) {
                // Если число совпадает с выбранным пользователем числом, увеличиваем счетчик
                mm += 1500;
                balanceNumber.setText(String.valueOf(mm));
                foundMatch = true; //Если правильно, останавливать цикл с помощью break
                break;
            }
        }

        if (!foundMatch) {
            mm -= 1000;
        }


        balanceNumber.setText(String.valueOf(mm));
        SharedPreferences.Editor editor = rPref.edit();
        editor.putInt(MainActivity.MONEY, mm);
        editor.apply();
    }

    public void toLock(){
        b1.setClickable(false);b1.setBackgroundResource(R.drawable.style_offnik);
        b2.setClickable(false);b2.setBackgroundResource(R.drawable.style_offnik);
        b3.setClickable(false);b3.setBackgroundResource(R.drawable.style_offnik);
        b4.setClickable(false);b4.setBackgroundResource(R.drawable.style_offnik);
        b5.setClickable(false);b5.setBackgroundResource(R.drawable.style_offnik);
        b6.setClickable(false);b6.setBackgroundResource(R.drawable.style_offnik);
        b7.setClickable(false);b7.setBackgroundResource(R.drawable.style_offnik);
        b8.setClickable(false);b8.setBackgroundResource(R.drawable.style_offnik);
        b9.setClickable(false);b9.setBackgroundResource(R.drawable.style_offnik);
    }
    public void toUnlock(){
        c = 0;
        b1.setClickable(true);b1.setBackgroundResource(R.drawable.style_cigan);
        b2.setClickable(true);b2.setBackgroundResource(R.drawable.style_cigan);
        b3.setClickable(true);b3.setBackgroundResource(R.drawable.style_cigan);
        b4.setClickable(true);b4.setBackgroundResource(R.drawable.style_cigan);
        b5.setClickable(true);b5.setBackgroundResource(R.drawable.style_cigan);
        b6.setClickable(true);b6.setBackgroundResource(R.drawable.style_cigan);
        b7.setClickable(true);b7.setBackgroundResource(R.drawable.style_cigan);
        b8.setClickable(true);b8.setBackgroundResource(R.drawable.style_cigan);
        b9.setClickable(true);b9.setBackgroundResource(R.drawable.style_cigan);
    }
    public void goToMain(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}