package com.manoj.multithreading;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
class MyProducer implements Runnable{
    //private ReentrantLock bufferLock;
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random=new Random();
        String[] nums={"1","2","3","4","5"};
        for (String num:nums){
            try {
                System.out.println(color + "Adding..." + num);
                //bufferLock.lock();
                buffer.put(num);
//                try {
//                    buffer.add(num);
//
//                } finally {
//                    bufferLock.unlock();
//
//                }

                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");

            }
        }
        System.out.println(color  + "Adding EOF and exiting...");

            //bufferLock.lock();
            try {
                buffer.put("EOF");

//            } finally {
//                bufferLock.unlock();
//
//            }
            } catch (InterruptedException e){

            }


    }
}
