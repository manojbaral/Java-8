package com.manoj.multithreading;

import java.util.Random;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
class Writer implements Runnable{
    private Message message;

    public Writer(Message message) {
        this.message = message;
    }


    @Override
    public void run() {
        String messages[]={
            "Humpty Dumpty sat on the wall",
            "Humpty Dumpty has a great fall",
            "All the kings horses and all the kings men",
            "Could not put Humpty together again"

        };

        Random random=new Random();
        for (int i=0;i<messages.length;i++){
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
        message.write("Finished");

    }
}

