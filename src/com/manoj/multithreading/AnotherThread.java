package com.manoj.multithreading;

import static com.manoj.multithreading.ThreadColors.ANSI_BLUE;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE +"Hello from manoj thread" + currentThread().getName() + "logs" + currentThread().getId() + " please get state " + currentThread().getState());
    try {
        Thread.sleep(5000);
    }
    catch (InterruptedException e){
        System.out.println(ANSI_BLUE + "Another thread wake me up" + e);
        return;

    }
        System.out.println("Its been 3 second ");
    }
}
