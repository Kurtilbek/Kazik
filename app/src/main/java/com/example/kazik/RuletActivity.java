package com.example.kazik;

import static com.example.kazik.MainActivity.pref;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RuletActivity extends AppCompatActivity {
    TextView firstNumber, secondNumber, thirdNumber, balanceNumber, button, test;
    int j = 0,
    rng = 10,
    N = 10,
    spd = 0,
    cst = 100,
    prz = 10000;
    //prz = 1;
    int mm;
    SharedPreferences rPref;

    Random r = new Random();
    //int randomNumber = r.nextInt(14001) + 1000;

    int a, b, c;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rulet);

        button = findViewById(R.id.my_button);
        firstNumber = findViewById(R.id.Text_1);
        secondNumber = findViewById(R.id.Text_2);
        thirdNumber = findViewById(R.id.Text_3);
        balanceNumber = findViewById(R.id.Text_4);
        test = findViewById(R.id.test);

        // Получаем SharedPreferences, созданные в MainActivity
        rPref = getSharedPreferences("moneyData", MODE_PRIVATE);
        mm = rPref.getInt(MainActivity.MONEY, 10000);

        balanceNumber.setText(String.valueOf(mm));

        if(mm<=0){
            button.setClickable(false);
            button.setBackgroundResource(R.drawable.style_offnik);
        }
    }

    public void Vichislenia(View v) throws InterruptedException {
        if ((j >= N && a == b && a == c) && a == 7 && b == 7 && c == 7) // если 777
        {
            mm += prz * 7;
//            mm += prz * r.nextInt(randomNumber);
//            test.setText(String.valueOf(randomNumber));
        }
        if ((j >= N && a == b && a == c) && (a != 7 && b != 7 && c != 7)) // если 111, 222, и тд
        {
            mm += prz;
//            mm += prz * randomNumber;
//            test.setText(String.valueOf(randomNumber));
        }

        mm -= cst;
        for (int i = 0; i < N; i++) {
            j++;
            a = r.nextInt(rng);
            b = r.nextInt(rng);
            c = r.nextInt(rng);
            firstNumber.setText(String.valueOf(a));
            secondNumber.setText(String.valueOf(b));
            thirdNumber.setText(String.valueOf(c));

            balanceNumber.setText(String.valueOf(mm)); // обновляем баланс после первого выпадения числа

            if(mm<=99){
                button.setBackgroundResource(R.drawable.style_offnik);
                button.setClickable(false);
                Toast.makeText(this,getString(R.string.WhatMoney), Toast.LENGTH_SHORT).show();
            }
            SharedPreferences.Editor editor = rPref.edit();
            editor.putInt(MainActivity.MONEY, mm);
            editor.apply();
        }
    }

    public void goToBack(View view){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}