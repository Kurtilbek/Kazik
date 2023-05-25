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
    private static final int REQUEST_CODE = 1;
    public SharedPreferences getPref(){
        return pref;
    }
    public int getM(){
        return m;
    }
    public static void saveData(int dataToSave) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(MONEY, dataToSave);
        editor.apply();
    }

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

//        int variableValue = pref.getInt(MONEY, 10000);
//
//        Intent intent = new Intent(this, RuletActivity.class);
//        intent.putExtra("moneyData", variableValue);
//        m = intent.getIntExtra(MONEY, 10000);


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Загружаем новое значение переменной m из SharedPreferences
        m = pref.getInt(MONEY, m);
        balanceNumber.setText(String.valueOf(m));

        // Обновляем значение переменной m и отображаем его на экране
        balanceNumber.setText(String.valueOf(m));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Получаем новое значение переменной m из RuletActivity
            int newM = data.getIntExtra(MONEY, m);

            // Обновляем значение переменной m и отображаем его на экране
            m = newM;
            balanceNumber.setText(String.valueOf(m));

            // Сохраняем новое значение переменной m в SharedPreferences
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(MONEY, m);
            editor.apply();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Сохраняем значение переменной m в SharedPreferences
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(MONEY, m);
        editor.apply();
    }
//    private void SavePreferences(String key, int m){
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt(MONEY, m);
//        editor.commit();
//        Intent sd = new Intent(this,RuletActivity.class);
//        startActivity(sd);
//    }
    public void goToRulet(View view){
        Intent intent = new Intent(this, RuletActivity.class);
        intent.putExtra(MONEY, m);
        startActivityForResult(intent, REQUEST_CODE);
        onPause();
        finish();
    }
    public void goToKamikadze(View view){
        startActivity(new Intent(this, KamikadzeActivity.class));
        onPause();
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
}