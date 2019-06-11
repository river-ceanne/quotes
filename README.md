# Quotes

This repository is a simple Java applicaton that parses a JSON file containing an array of quote objects using GSON to display random quotes every time it runs.

## Application Dependencies:
On your Java clone od this project, make sure you have the GSON dependecy implemented in your build.gradle file:


    dependencies {
      
    implementation 'com.google.code.gson:gson:2.8.5'

    }
</Code>

## Assets:
* recentquotes.json

## How to run:
Running the main method will display a random quote from a JSON file under assets folder. 

## Code
[Source Files](./src/main/java/quotes/)

<hr>

# Quotes - (Extended)

An extension of the Quotes Java Appkication. 

## Description
When running the application, don’t read in the quotes file. Instead, make a request to an API to get a random quote.
* Formismatic: no API key required, visit http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en to get random quotes

Rather than a Quote of the Day, please use an API that allows you to show a random quote.

Link: [Source Files](./src/main/java/quotes/PotentQuotables.java)

