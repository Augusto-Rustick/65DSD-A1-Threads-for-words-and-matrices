package org.words.WordsCounterWithThreads;

import java.io.*;
import java.nio.charset.StandardCharsets;

class Leitor extends Thread {
   private File[] arquivos;
   private String palavra;
   private Contador contador;
   private int start;
   private int end;

   public Leitor(File[] arquivos, String palavra, Contador contador, int start, int end) {
      this.arquivos = arquivos;
      this.palavra = palavra;
      this.contador = contador;
      this.start = start;
      this.end = end;
   }

   public void run() {
      for (int i = start; i <= end; i++) {
         try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivos[i]), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
               String[] words = line.split("\\s+");
               for (String word : words) {
                  if (word.equals(palavra)) {
                     contador.increment();
                  }
               }
            }
         } catch (IOException e) {
            e.printStackTrace();
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      }
   }
}