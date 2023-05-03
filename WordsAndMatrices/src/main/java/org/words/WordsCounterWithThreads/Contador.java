package org.words.WordsCounterWithThreads;

import java.util.concurrent.Semaphore;

public class Contador {
    private int countWord;
    private Semaphore mutex = new Semaphore(1);

    public Contador() {
        this.countWord = 0;
    }

    public int getCountWord() {
        return countWord;
    }

    public void increment() throws InterruptedException {
        mutex.acquire();
        countWord++;
        mutex.release();
    }
}
