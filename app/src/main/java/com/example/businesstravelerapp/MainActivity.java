package com.example.businesstravelerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    int curr;
    String country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLay);

        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);

        RadioGroup countries1 = (RadioGroup) findViewById(R.id.cityPicker);
        RadioGroup countries2 = (RadioGroup) findViewById(R.id.originGroup2);
        Button selectButton = (Button) findViewById(R.id.buttonCitySelection);

        //make sure neither group checked upon startup
        countries2.clearCheck();
        countries1.clearCheck();

        curr = 0;
        country = "";




        countries2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != -1) {
                    countries1.clearCheck();

                    switch (i) {
                        case R.id.radioFrance:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.franceflag));
                            country = "France";
                            break;
                        case R.id.radioItaly:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.italyflag));
                            country = "Italy";
                            break;
                        case R.id.radioSpain:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.spainflag));
                            country = "Spain";
                            break;
                        case R.id.radioUK:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ukflag));
                            country = "UK";
                            break;
                        case R.id.radioUSA:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.usaflag));
                            country = "USA";
                            break;
                        case R.id.radioGermany:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.germanflag));
                            country = "Germany";
                            break;
                    }

                    curr = i;
                }
            }
        });

        countries1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != -1) {
                    countries2.clearCheck();

                    switch (i) {
                        case R.id.radioBrazil:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.brazilflag));
                            country = "Brazil";
                            break;
                        case R.id.radioChina:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.chinaflag));
                            country = "China";
                            break;
                        case R.id.radioIndia:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indiaflag));
                            country = "India";
                            break;
                        case R.id.radioIreland:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.irelandflag));
                            country = "Ireland";
                            break;
                        case R.id.radioJapan:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.japanflag));
                            country = "Japan";
                            break;
                        case R.id.radioKorea:
                            mainLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.koreaflag));
                            country = "Korea";
                            break;
                    }

                    curr = i;
                }
            }
        });



        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curr != 0) {
                    Intent goToSecond = new Intent();
                    goToSecond.setClass(getApplicationContext(), DestinationActivity.class);
                    goToSecond.putExtra("Country", country);
                    startActivity(goToSecond);
                }
            }
        });
    }


}