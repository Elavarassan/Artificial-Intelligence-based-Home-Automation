
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include "DHT.h"
#include "SSD1306.h"
#define DHTTYPE DHT11
// Set these to run example.
#define DHTPIN 2
//DHT dht;
#define FIREBASE_HOST "nodemcu-588e6.firebaseio.com"
#define FIREBASE_AUTH "3lBvXOv5aFaU9Xiz1C8mCr8TKOystsWQx08uxcMO"
#define WIFI_SSID "viswa"
#define WIFI_PASSWORD "dhivya18"
const int dev1=3;
const int dev2=4;
DHT dht(DHTPIN, DHTTYPE);SSD1306 display(0x3c, D3, D5);
void setup() {
pinMode(dev1,OUTPUT);
pinMode(dev2,OUTPUT);
dht.begin();
digitalWrite(dev1,1);
digitalWrite(dev2,1);
Serial.begin(9600);
WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
Serial.print("connecting");
while (WiFi.status() != WL_CONNECTED) {
Serial.print(".");
delay(500);
}
Serial.println();Serial.print("connected: ");
Serial.println(WiFi.localIP());
Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
display.init();
display.flipScreenVertically();
display.setFont(ArialMT_Plain_16);
display.setTextAlignment(TEXT_ALIGN_LEFT);
}
String n="";
void loop() {
// Wait a few seconds between measurements.
delay(2000);
// Reading temperature or humidity takes about 250 milliseconds!
// Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
float h = dht.readHumidity();
String humidity=String(h,0);
// Read temperature as Celsius (the default)
float t = dht.readTemperature();
String temperature=String(t,0);// Read temperature as Fahrenheit (isFahrenheit = true)
float f = dht.readTemperature(true);
String temperature_f=String(f,0);
display.display();
//* Get temperature value */
if (isnan(h) || isnan(t) || isnan(f)) {
display.clear(); // clearing the display
display.drawString(5,0, "Failed DHT");
return;
}
display.clear();
display.drawString(0, 0, "Humidity: " + String(h) + "%\t");
display.drawString(0, 16, "Temp: " + String(t) + "C");
Firebase.setString("temperature",temperature+" "+temperature_f);
Firebase.setString("humidity",humidity);
if(Firebase.getString("Device-1").equals("1"))
{
Serial.println(" sucsessfully device 1 on");
digitalWrite(dev1,HIGH);
display.drawString(0,32,"device1 ON");
}
delay(1000);if(Firebase.getString("Device-1").equals("0"))
{
Serial.println(" sucsessfully device 1 off");
digitalWrite(dev1,LOW);
display.drawString(0,32,"device1 OFF");
}
delay(1000);
if(Firebase.getString("Device-2").equals("1"))
{
Serial.println(" sucsessfully device 2 on");
digitalWrite(dev2,HIGH);
display.drawString(0,48,"device2 ON");
}
delay(1000);
if(Firebase.getString("Device-2").equals("0"))
{
Serial.println(" sucsessfully device 2 off");
digitalWrite(dev2,LOW);
display.drawString(0,48,"device2 OFF");
}delay(1000);
if (Firebase.failed()) // Check for errors
{
Serial.print("setting /number failed:");
Serial.println(Firebase.error());
//ESP.reset();
return;
}
delay(3000);
}