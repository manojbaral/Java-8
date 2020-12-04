package com.manoj.multithreading;

import java.util.Random;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
class Reader implements Runnable{
    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random=new Random();
        for (String latestMessage=message.read(); !latestMessage.equals("finished");
             latestMessage=message.read()){
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e){

            }
        }

    }
}
