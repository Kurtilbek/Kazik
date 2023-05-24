package com.example.kazik;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
public class KamikadzeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView money;
    MainActivity RA = new MainActivity();
    SharedPreferences rPref = RA.getPref();
    int rM = RA.getM();
    private Random random = new Random();
    private Button[] buttons = new Button[9];
    private TextView scoreTextView;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mButton5;
    private Button mButton6;
    private Button mButton7;
    private Button mButton8;
    private Button mButton9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamikadze);
        scoreTextView = findViewById(R.id.score_textview);
        money = findViewById(R.id.money);

        rPref = getSharedPreferences("moneyData", MODE_PRIVATE);
        rM = rPref.getInt(MainActivity.MONEY, 10000);
        money.setText(String.valueOf(rM));

        mButton1 = findViewById(R.id.button_1);
        mButton2 = findViewById(R.id.button_2);
        mButton3 = findViewById(R.id.button_3);
        mButton4 = findViewById(R.id.button_4);
        mButton5 = findViewById(R.id.button_5);
        mButton6 = findViewById(R.id.button_6);
        mButton7 = findViewById(R.id.button_7);
        mButton8 = findViewById(R.id.button_8);
        mButton9 = findViewById(R.id.button_9);

        buttons = new Button[] {mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9};
        // Установка обработчиков нажатия для всех кнопок
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Обработка нажатия кнопки
                    handleButtonClick((Button) view);
                }
            });
        }
        // Генерация новых случайных правильных кнопок
    }
    @Override
    public void onClick(View view) {
        int value = Integer.parseInt(((Button) view).getText().toString());

        if (value == 5) {
            rM += 10;
        } else {
            rM -= 5;
        }

        // Генерация новых случайных правильных кнопок
    }

    private void generateRandomCorrectButtons() {
        int correctButtonIndex = random.nextInt(buttons.length);
        String correctAnswer = String.valueOf(random.nextInt(10));
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];

            if (i == correctButtonIndex) {
                button.setText(correctAnswer);
                button.setTag("correct");
            } else {
                button.setText(String.valueOf(random.nextInt(10)));
                button.setTag("incorrect");
            }
        }
    }
    private void handleButtonClick(Button button) {
        if (button.getTag().equals("correct")) {
            rM += 10;
            money.setText(String.valueOf(rM));
        } else {
            rM -= 2;
            money.setText(String.valueOf(rM));
        }
        money.setText(String.valueOf(rM));
        if (rM >= 50) {
        } else if (rM <= -50) {
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Лох", Toast.LENGTH_SHORT).show();

        }

        generateRandomCorrectButtons();
    }

}