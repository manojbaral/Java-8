package com.manoj.multithreading;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
public class Countdown {
    private int i;
    public  void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColors.ANSI_RED;
                break;

            case "Thread 2":
                color = ThreadColors.ANSI_PURPLE;
                break;

            default:
                color = ThreadColors.ANSI_GREEN;
        }

        synchronized (this){
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }


    }
}
