package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    static Semaphore sem;
    public static void main(String[] args){
        sem = new Semaphore(1);
        Consumidor consumidor = new Consumidor();
        Productor productor = new Productor();

        Thread thread1 = new Thread(consumidor);
        Thread thread2 = new Thread(productor);

        thread1.start();
        thread2.start();
    }
}
