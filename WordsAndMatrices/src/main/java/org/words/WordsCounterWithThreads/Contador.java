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

   public int setCountWord(int countWord) {
      return countWord;
   }

   public void increment(int leitorCount) throws InterruptedException {
      mutex.acquire();
      this.setCountWord(this.getCountWord() + leitorCount);
      mutex.release();
   }
}
