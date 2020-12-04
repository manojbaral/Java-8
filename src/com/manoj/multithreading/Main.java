package com.manoj.multithreading;


import java.security.PublicKey;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.manoj.multithreading.ThreadColors.ANSI_RED;

/**
 * Created by Manoj Baral on 11/24/2020.
 */
public class Main {

    //Dead Lock
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    //Starvation
    private static ReentrantLock lock = new ReentrantLock(true);


    public static final String EOF = "EOF";

    public static void main(String[] args) {

        Thread t4 = new Thread(new Worker(ANSI_RED), "Priority 10");
        Thread t5 = new Thread(new Worker(ThreadColors.ANSI_BLUE), "Priority 8");
        Thread t6 = new Thread(new Worker(ThreadColors.ANSI_GREEN), "Priority 6");
        Thread t7 = new Thread(new Worker(ThreadColors.ANSI_CYAN), "Priority 4");
        Thread t8 = new Thread(new Worker(ThreadColors.ANSI_PURPLE), "Priority 2");

        final Workers worker1 = new Workers("Worker1", true);
        final Workers worker2 = new Workers("Worker2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        final Challenge1 challenge1=new Challenge1(100.00,"123-45-67");



        t4.setPriority(10);
        t5.setPriority(8);
        t6.setPriority(6);
        t7.setPriority(4);
        t8.setPriority(2);

        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

        //Create and start threads here
        Thread trThread1=new Thread(){
            @Override
            public void run() {
                challenge1.deposit(300.00);
                challenge1.withdraw(50.00);
            }
        };

        Thread trThread2=new Thread(){
            @Override
            public void run() {
                challenge1.deposit(203.75);
                challenge1.withdraw(100.00);
            }
        };

        trThread1.start();
        trThread2.start();




        ArrayBlockingQueue<String> bufer = new ArrayBlockingQueue<String>(6);
        ReentrantLock bufferLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyProducer producer = new MyProducer(bufer, ANSI_RED);
        MyConsumer consumer1 = new MyConsumer(bufer, ThreadColors.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(bufer, ThreadColors.ANSI_RESET);

        //Dead lock


        System.out.println(ANSI_RED + "Hello from the main thread");
        final Thread thread = new AnotherThread();
        thread.setName("===== Another thread =======");
        thread.start();

        new Thread() {
            public void run() {
                System.out.println(ThreadColors.ANSI_CYAN + "Hello thread from anamoys class");
            }
        }.start();

        Thread myRunnableThread = new Thread(new MyRunnableThread() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "hELLO FROM ANAMOYS IMPL CLASS implementing of run()");


                try {
                    thread.join(2000);
                    System.out.println("Another thread terminated or timed out .So i am running again");

                } catch (
                    InterruptedException e)

                {
                    System.out.println(ANSI_RED + " I could not wait after all.i was interpreted");
                }
            }

        });
        myRunnableThread.start();
        //thread.interrupt();
        System.out.println(ANSI_RED + "Hello again");

        Countdown countdown = new Countdown();
        Message message = new Message();

        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();

        //Executive Service
        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        //Submit method
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColors.ANSI_WHITE + " I'M Being printer for the calable class");
                return "This is the callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Somethings went wrong");
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interpreted");
        }

//        //Shutdown the executive service
        executorService.shutdown();


        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");


        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        CountdownThread t3 = new CountdownThread(countdown);
        t2.setName("Thread 3");

        t1.start();
        t2.start();
        t3.start();
        new Thread1().start();
        new Thread2().start();

        final PolitePerson jane = new PolitePerson("jane");
        final PolitePerson john = new PolitePerson("john");

        new Thread(new Runnable() {
            @Override
            public void run() {
                //jane.sayHello(john);
                worker1.work(sharedResource, worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //john.sayHello(jane);
                worker2.work(sharedResource, worker1);

            }
        }).start();


    }

    private static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(ThreadColors.ANSI_RESET + "Thread 1: Has lock1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 1: Waiting for lock 2 ");
                synchronized (lock2) {
                    System.out.println("Thread 1: Has lock1 and lock2 ");
                }

                System.out.println("Thread 1: Released lock2 ");
            }
            System.out.println("Thread 1: Released lock1. Existing...... ");
        }
    }

    private static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println(ThreadColors.ANSI_GREEN + "Thread 2: Has lock1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread 2: Waiting for lock 2 ");

                synchronized (lock2) {
                    System.out.println("Thread 2: Has lock1 and lock2 ");
                }

                System.out.println("Thread 2: Released lock2 ");
            }
            System.out.println("Thread 2: Released lock1. Existing...... ");
        }
    }

    static class PolitePerson {
        private final String name;

        PolitePerson(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void sayHello(PolitePerson person) {
            System.out.format(ThreadColors.ANSI_BLUE + "%s:%s" + " has said hello to me!%n", this.name, person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person) {
            System.out.format(ThreadColors.ANSI_RED + "%s:%s" + " has said hello back to me!%n", this.name, person.getName());
        }
    }

    private static class Worker implements Runnable {
        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                lock.lock();
                try {
                    System.out.format(threadColor + "%s: runCount =%d\n", Thread.currentThread(), runCount++);


                } finally {
                    lock.unlock();
                }
            }

        }
    }
}



