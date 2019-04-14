package com.udacity.gradle.builditbigger.backend;

/**
 * The object model for the data we are sending through endpoints
 */

import joke.JokeData;

public class MyBean {

    private String myData;

    String getData() {
        myData = new JokeData().getJoke();
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}