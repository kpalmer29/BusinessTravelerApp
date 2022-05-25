package com.example.businesstravelerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayPage extends AppCompatActivity {

    String destinationCity;
    String destinationCountry;
    String originCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);

        Intent caller = getIntent();
        originCountry = caller.getStringExtra("Origin");
        destinationCountry = caller.getStringExtra("DestinationCountry");
        destinationCity = caller.getStringExtra("DestinationCity");

        String display = destinationCity + " Travel Info";

        TextView prompt = (TextView) findViewById(R.id.displayPrompt);
        prompt.setText(display);

        Button hotels = (Button) findViewById(R.id.buttonHotels);
        Button restaurants = (Button) findViewById(R.id.buttonFood);
        Button exchange = (Button) findViewById(R.id.buttonExchange);
        Button textSMS = (Button) findViewById(R.id.buttonSMS);
        Button homepage = (Button) findViewById(R.id.buttonHome);
        Button weather = (Button) findViewById(R.id.buttonWeather);

        Intent callWebView = new Intent();
        callWebView.setClass(getApplicationContext(), webViewActivity.class);
        String exchangeUrl = "https://www.xe.com/currencyconverter/convert/?Amount=1&";
        //From=USD&To=GBP

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = setFrom(originCountry);
                if (from.equals("Error"))
                    throw new RuntimeException("Origin Country not properly set");
                String to = setTo(destinationCountry);
                if (to.equals("Error"))
                    throw new RuntimeException("Destination Country not properly set");

                String url = exchangeUrl + from + "&" + to;
                callWebView.putExtra("URL", url);
                startActivity(callWebView);
            }
        });

        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent();
                toHome.setClass(getApplicationContext(), MainActivity.class);
                startActivity(toHome);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String initUrl = "https://www.booking.com/city/";
                // "fr/paris.html"

                if (hotelsUrl(destinationCountry, destinationCity).equals("Error"))
                    throw new RuntimeException("hotels-Method not properly working");
                String url = initUrl + hotelsUrl(destinationCountry, destinationCity);
                callWebView.putExtra("URL", url);
                startActivity(callWebView);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = restaurantURL();
                if (url.equals("Error"))
                    throw new RuntimeException("Restaurants sub-method failure");
                else if (url.equals("Osong")) {
                    Toast.makeText(DisplayPage.this, "Information not currently " +
                            "available", Toast.LENGTH_SHORT).show();}
                else {
                callWebView.putExtra("URL", url);
                startActivity(callWebView);}
            }
        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String weatherUrl = "https://www.google.com/search?q=" +
                        destinationCity.toLowerCase().replace(' ', '+')
                         + "+weather";

                callWebView.putExtra("URL", weatherUrl);
                startActivity(callWebView);
            }
        });

        textSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textbody = "Hey! I just landed in " + destinationCity + ".";
                Uri destination = Uri.parse("smsto:5551234567");
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, destination);
                smsIntent.putExtra("sms_body", textbody);
                startActivity(smsIntent);
            }
        });
    }



    private String setFrom(String origin) {
        switch (origin) {
            case "USA":
                return "From=USD";
            case "UK":
            case "Ireland":
                return "From=GBP";
            case "France":
            case "Germany":
            case "Spain":
            case "Italy":
                return "From=EUR";
            case "Brazil":
                return "From=BRL";
            case "China":
                if (destinationCity.equals("Hong Kong"))
                    return "From=HKD";
                else
                    return "From=CNY";
            case "India":
                return "From=INR";
            case "Japan":
                return "From=JPY";
            case "Korea":
                return "From=KRW";
        }
        return "Error";
    }

    private String setTo(String destination) {
        switch (destination) {
            case "USA":
                return "To=USD";
            case "UK":
            case "Ireland":
                return "To=GBP";
            case "France":
            case "Germany":
            case "Spain":
            case "Italy":
                return "To=EUR";
            case "Brazil":
                return "To=BRL";
            case "China":
                if (destinationCity.equals("Hong Kong"))
                    return "To=HKD";
                else
                    return "To=CNY";
            case "India":
                return "To=INR";
            case "Japan":
                return "To=JPY";
            case "Korea":
                return "To=KRW";
        }
        return "Error";
    }

    private String hotelsUrl(String desCountry, String desCity) {
        switch (desCountry) {
            case "USA":
                return "us/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "UK":
                return "gb/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Ireland":
                return "ie/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "France":
                return "fr/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Germany":
                return "de/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Spain":
                return "es/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Italy":
                return "it/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Brazil":
                return "br/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "China":
                if (destinationCity.equals("Hong Kong"))
                    return "hk/hong-kong.html";
                else
                    return "cn/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "India":
                if (desCity.equals("Mumbai"))
                    desCity = "Bombay";
                return "in/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Japan":
                return "jp/" + desCity.toLowerCase().replace(' ', '-') + ".html";
            case "Korea":
                return "kr/" + desCity.toLowerCase().replace(' ', '-') + ".html";
        }
        return "Error";
    }

    private String restaurantURL() {
        String infatuationURL = "https://www.theinfatuation.com/";
        String urlYelp = "https://www.yelp.com/search?cflt=restaurants&find_loc=";
        String urlZomato = "https://www.zomato.com/"; //mumbai/restaurants
        String url50 = "https://www.theworlds50best.com/discovery/sitemap/china/";
        switch (destinationCountry) {
            case "USA":
                return infatuationURL + destinationCity.toLowerCase().replace(' ', '-');
            case "UK":
                if (destinationCity.equals("London"))
                    return infatuationURL + "london";
                else
                    return urlYelp + destinationCity.toLowerCase().replace(' ', '-');
            case "Ireland":
            case "Brazil":
                return urlYelp + destinationCity.toLowerCase().replace(' ', '-');
            case "France":
                if (destinationCity.equals("Paris"))
                    return infatuationURL + destinationCity.toLowerCase();
                else
                    return urlYelp + destinationCity.toLowerCase().replace(' ', '-');
            case "Germany":
                if (destinationCity.equals("Berlin"))
                    return infatuationURL + destinationCity.toLowerCase();
                else
                    return urlYelp + destinationCity.toLowerCase().replace(' ', '-');
            case "Spain":
                if (destinationCity.equals("Madrid") || destinationCity.equals("Barcelona"))
                    return infatuationURL + destinationCity.toLowerCase();
                else
                    return urlYelp + destinationCity.toLowerCase().replace(' ', '-');
            case "Italy":
                if (destinationCity.equals("Rome"))
                    return infatuationURL + destinationCity.toLowerCase();
                else
                    return urlYelp + destinationCity.toLowerCase();
            case "China":
                if (destinationCity.equals("Hong Kong"))
                    return infatuationURL + "hong-kong";
                else
                    return url50 + destinationCity.toLowerCase();
            case "India":
                if (destinationCity.equals("New Delhi"))
                    return urlZomato + "ncr/dine-out";
                else
                    return urlZomato + destinationCity.toLowerCase() +"/dine-out";
            case "Japan":
                if (destinationCity.equals("Tokyo"))
                    return "tokyo";
                else
                    return urlYelp + destinationCity.toLowerCase().replace(' ', '-');
            case "Korea":
                if (destinationCity.equals("Seoul"))
                    return "https://www.eater.com/maps/best-seoul-restaurants-38";
                else if (destinationCity.equals("Ulsan"))
                    return "https://en.tripadvisor.com.hk/Restaurants-g297893-Ulsan.html";
                else
                    return "Osong";
        }
        return "Error";
    }


}