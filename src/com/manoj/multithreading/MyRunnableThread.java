package com.manoj.multithreading;

import java.util.logging.Logger;

import static com.manoj.multithreading.ThreadColors.ANSI_BLACK;
import static com.manoj.multithreading.ThreadColors.ANSI_RED;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
public class MyRunnableThread implements Runnable {
    private static Logger log=Logger.getLogger(String.valueOf(MyRunnableThread.class));
    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from my runnable thread");
        //log.info(ANSI_BLACK + "Hello from log thread" );

    }
}
