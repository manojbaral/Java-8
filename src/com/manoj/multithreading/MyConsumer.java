package com.manoj.multithreading;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import static com.manoj.multithreading.Main.EOF;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
class MyConsumer implements Runnable{
    private ArrayBlockingQueue<String> buffer;
    //private ReentrantLock bufferLock;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer){
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                }
                catch (InterruptedException e){



                }


            }

            }
            }
        }


