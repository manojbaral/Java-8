package com.manoj.multithreading;

/**
 * Created by Manoj Baral on 12/3/2020.
 * Example using FairLocks and Live Locks
 */
public class SharedResource {
    private Workers owner;

    public SharedResource(Workers owner) {
        this.owner = owner;
    }

    public Workers getOwner() {
        return owner;
    }

    public synchronized void setOwner(Workers owner) {
        this.owner = owner;
    }
}
