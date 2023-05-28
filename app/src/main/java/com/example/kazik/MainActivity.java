package com.example.kazik;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int m = 10000;//стартовый капитал
    TextView balanceNumber;
    public static SharedPreferences pref;
    public final static String MONEY = "mon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("moneyData", MODE_PRIVATE);
        m = pref.getInt(MONEY, m);

        Intent intent = new Intent(this, RuletActivity.class);
        intent.putExtra(MONEY, m);

        balanceNumber = findViewById(R.id.Text_4);
        balanceNumber.setText(String.valueOf(m));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Загружаем новое значение переменной m из SharedPreferences
        m = pref.getInt(MONEY, m);
        balanceNumber.setText(String.valueOf(m));

        // Обновляем значение переменной m и отображаем его на экране
//        balanceNumber.setText(String.valueOf(m));
    }

    public void goToRulet(View view){
        Intent intent = new Intent(this, RuletActivity.class);
        intent.putExtra(MONEY, m);
        startActivity(intent);
        finish();
    }

    public void toDeletAll(View view) {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();

        // Обновляем значение переменной m и отображаем его на экране
        m = 10000;
        balanceNumber.setText(String.valueOf(m));
    }

    public void goToAbout(View view) {
        startActivity(new Intent(this, AboutActivity.class));
        finish();
    }

    public void goToKamikadze(View view) {
        Intent intent = new Intent(this, KamikadzeActivity.class);
        startActivity(intent);
        finish();
    }
}