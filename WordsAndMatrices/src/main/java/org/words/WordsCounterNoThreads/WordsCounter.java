package org.words.WordsCounterNoThreads;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsCounter {
   public static void main(String[] args) {
      Instant startTime = Instant.now();
      // pergunta ao usuário a palavra desejada
      String word = "traditional"; //hello   coffee   traditional
      // coleta um array com os arquivos de texto do diretorio do dataset
      String pasta = "src/main/java/org/words/dataset";
      File diretorio = new File(pasta);
      File[] arquivos = diretorio.listFiles();
      //variável de auxilio para o contador
      int countWord = 0;
      //
      Pattern pattern = Pattern.compile("\\b" + word + "\\b");
      //intera para cada arquivo de txt
      for (File arquivo : arquivos) {
         try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
               Matcher matcher = pattern.matcher(line);
               while (matcher.find()) {
                  countWord++;
               }
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      System.out.println("Em todos os arquivos, a palavra '" + word + "' foi encontrada " + countWord + " vezes.");
      Instant endTime = Instant.now();
      long duration = Duration.between(startTime, endTime).toMillis();
      System.out.println("Duração: " + duration + " milissegundos");
   }
}