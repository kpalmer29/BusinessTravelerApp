package com.example.businesstravelerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class CityActivity extends AppCompatActivity {

    String destination_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        String[] brazilChoices = {"Rio de Janeiro", "São Paulo"};
        String[] chinaChoices = {
                "Shanghai",
                "Beijing",
                "Chengdu",
                "Hong Kong",
                "Guangzhou",
                "Shenzhen"};
        String[] franceChoices = {"Paris", "Bordeaux", "Marseille", "Lyon"};
        String[] germanyChoices = {"Berlin", "Munich", "Hamburg", "Frankfurt"};
        String[] indiaChoices = {"Mumbai", "New Delhi", "Bangalore", "Goa", "Calcutta"};
        String[] irelandChoices = {"Dublin", "Cork", "Galway"};
        String[] italyChoices = {"Rome", "Naples", "Florence", "Milan"};
        String[] japanChoices = {"Tokyo", "Kyoto", "Osaka", "Fukuoka"};
        String[] koreaChoices = {"Seoul", "Osong", "Ulsan"};
        String[] spainChoices = {"Barcelona", "Madrid", "Valencia", "Seville"};
        String[] ukChoices = {"London", "Manchester", "Birmingham"};
        String[] usaChoices = {
                "New York",
                "Los Angeles",
                "Chicago",
                "Houston",
                "Phoenix",
                "Philadelphia"
        };

        Intent caller = getIntent();
        String originCountry = caller.getStringExtra("OriginCountry");
        String destinationCountry = caller.getStringExtra("DestinationCountry");

        Button choice = (Button) findViewById(R.id.buttonCitySelection);
        NumberPicker selection = (NumberPicker) findViewById(R.id.cityPicker);

        selection.setMinValue(0);

        switch (destinationCountry) {
            case "France":
                selection.setDisplayedValues(franceChoices);
                selection.setMaxValue(franceChoices.length - 1);
                break;
            case "Brazil":
                selection.setDisplayedValues(brazilChoices);
                selection.setMaxValue(brazilChoices.length - 1);
                break;
            case "China":
                selection.setDisplayedValues(chinaChoices);
                selection.setMaxValue(chinaChoices.length - 1);
                break;
            case "Germany":
                selection.setDisplayedValues(germanyChoices);
                selection.setMaxValue(germanyChoices.length - 1);
                break;
            case "India":
                selection.setDisplayedValues(indiaChoices);
                selection.setMaxValue(indiaChoices.length - 1);
                break;
            case "Ireland":
                selection.setDisplayedValues(irelandChoices);
                selection.setMaxValue(irelandChoices.length - 1);
                break;
            case "Italy":
                selection.setDisplayedValues(italyChoices);
                selection.setMaxValue(italyChoices.length - 1);
                break;
            case "Japan":
                selection.setDisplayedValues(japanChoices);
                selection.setMaxValue(japanChoices.length - 1);
                break;
            case "Korea":
                selection.setDisplayedValues(koreaChoices);
                selection.setMaxValue(koreaChoices.length - 1);
                break;
            case "Spain":
                selection.setDisplayedValues(spainChoices);
                selection.setMaxValue(spainChoices.length - 1);
                break;
            case "UK":
                selection.setDisplayedValues(ukChoices);
                selection.setMaxValue(ukChoices.length - 1);
                break;
            case "USA":
                selection.setDisplayedValues(usaChoices);
                selection.setMaxValue(usaChoices.length - 1);
                break;
        }

        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cityChoice = selection.getValue();
                switch (destinationCountry) {
                    case "France":
                        destination_city = franceChoices[cityChoice];
                        break;
                    case "Brazil":
                        if (brazilChoices[cityChoice].equals("São Paulo"))
                            destination_city = "Sao Paulo";
                        else
                            destination_city = brazilChoices[cityChoice];
                        break;
                    case "China":
                        destination_city = chinaChoices[cityChoice];
                        break;
                    case "Germany":
                        destination_city = germanyChoices[cityChoice];
                        break;
                    case "India":
                        destination_city = indiaChoices[cityChoice];
                        break;
                    case "Ireland":
                        destination_city = irelandChoices[cityChoice];
                        break;
                    case "Italy":
                        destination_city = italyChoices[cityChoice];
                        break;
                    case "Japan":
                        destination_city = japanChoices[cityChoice];
                        break;
                    case "Korea":
                        destination_city = koreaChoices[cityChoice];
                        break;
                    case "Spain":
                        destination_city = spainChoices[cityChoice];
                        break;
                    case "UK":
                        destination_city = ukChoices[cityChoice];
                        break;
                    case "USA":
                        destination_city = usaChoices[cityChoice];
                        break;
                }

                Intent goToDisplayPage = new Intent();
                goToDisplayPage.setClass(getApplicationContext(), DisplayPage.class);
                goToDisplayPage.putExtra("Origin", originCountry);
                goToDisplayPage.putExtra("DestinationCountry", destinationCountry);
                goToDisplayPage.putExtra("DestinationCity", destination_city);
                startActivity(goToDisplayPage);
            }
        });


    }
}