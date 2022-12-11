package org.example;

import static java.lang.Math.sin;

public class Counter {

    public Double count(double a, double b, double h) {
        double f;
        f = h*(sin(a)+sin(b))/2;
        return f;
        //return h*(sin(a)+sin(b))/2;
    }
}
