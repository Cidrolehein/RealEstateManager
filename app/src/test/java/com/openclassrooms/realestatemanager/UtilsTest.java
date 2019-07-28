package com.openclassrooms.realestatemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void convertEuroToDollar() {
        int input = 5;
        double output;
        double expected = 5.65;
        double delta = 1;
        output = Utils.convertEuroToDollar(input);
        assertEquals(expected, output, delta);
    }

    @Test
    public void getTodayDateFormated() {
        String expected = "10/07/2019"; // change the current date
        String output = Utils.getTodayDateFormated();
        assertEquals(expected, output);
    }

    @Test
    public void isInternetAvailable() {
        assertTrue(Utils.isInternetFormatedAvailable());
    }
}