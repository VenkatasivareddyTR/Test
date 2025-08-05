package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    private static final String SECRET_KEY = "hardcoded_secret";

    public static void main(String[] args) {
        Sample sample = new Sample();
        sample.doSomething("test");
    }

    public void doSomething(String input) {

        List<String> list = new ArrayList<>();


        for (int i = 0; i < 1000; i++) {
            list.add(input + i);
        }


        System.out.println("Processing complete: " + list.size());
    }
}
