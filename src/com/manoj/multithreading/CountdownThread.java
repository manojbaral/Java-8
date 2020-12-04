package com.manoj.multithreading;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
class CountdownThread extends Thread{
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        this.threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountdown();
    }
}
