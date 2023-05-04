package org.words.WordsCounterWithThreads;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Leitor extends Thread {
   private File[] arquivos;
   private String palavra;
   private Contador contador;
   private int start, end, wordCount;
   private Pattern pattern;

   public Leitor(File[] arquivos, String palavra, Contador contador, int start, int end) {
      this.arquivos = arquivos;
      this.palavra = palavra;
      this.contador = contador;
      this.start = start;
      this.end = end;
      this.pattern = Pattern.compile("\\b" + palavra + "\\b");
      this.wordCount = 0;
   }

   public void run() {
      for (int i = start; i <= end; i++) {
         try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivos[i]), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
               Matcher matcher = pattern.matcher(line);
               while (matcher.find()) {
                  wordCount++;
               }
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      try {
         contador.increment(wordCount);
      } catch (InterruptedException e) {
         throw new RuntimeException(e);
      }
   }
}