# BusinessTravelerApp
A simple Android App which could be used by businessmen/businesswomen to get essential information about their trip. 

## MainActivity
The first page which opens upon opening the app. This page allows users to select their country of origin for their trip.

## Destination Activity
The page that a user is directed to from MainActivity. This page allows users to select their destination country for their trip.

## CityActivity
This activiy is started after a user finishes Destination Activity. In CityActivity, the user is list to pick from a list of cities
based off of the country they picked in the Destination Activity.

## DisplayPage
This allows the user to click various Buttons which will pull up web-pages giving them:
*The exchange rate between the currencies of the origin and destination countries they selected
*The weather in the destination city
*Hotels in the destination city
*Restaurant suggestions in the destination city
*An option to send an SMS
*A link back to the first page

## WebViewActivity
This is a fullscreen activity which displays a website based off of the choices made by the user in the DisplayPage activity
