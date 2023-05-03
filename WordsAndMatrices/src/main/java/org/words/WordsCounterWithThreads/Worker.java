package org.words.WordsCounterWithThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
   private File[] arquivos;
   private String word;
   private Contador contador;
   private int start, end;

   public Worker(File[] arquivos, String word, Contador contador, int start, int end) {
      this.arquivos = arquivos;
      this.word = word;
      this.contador = contador;
      this.start = start;
      this.end = end;
   }

   @Override
   public void run() {
      // Itera para cada arquivo de txt
      for (int c = start; c <= end; c++) {
         File arquivo = arquivos[c];
         //File arquivo : arquivos
         try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
               String[] palavras = linha.split("\\s+");
               for (String palavra : palavras) {
                  if (palavra.equals(word)) {
                     contador.increment();
                  }
               }
            }
         } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      }
   }
}
