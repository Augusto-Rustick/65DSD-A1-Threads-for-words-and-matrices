package org.words.WordsCounterNoThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordsCounter {
   public static void main(String[] args) {
      //coleta um arrei com os arquivos de texto do diretorio do dataset
      String pasta = "src/main/java/org/words/dataset";
      File diretorio = new File(pasta);
      File[] arquivos = diretorio.listFiles();
      //variável de auxilio para o contador
      int countWord = 0;
      String word = "hello";
      //intera para cada arquivo de txt
      for (File arquivo : arquivos) {
         if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
            try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
               String linha;
               int counterFile = 0;
               while ((linha = leitor.readLine()) != null) {
                  String[] palavras = linha.split("\\s+");
                  for (String palavra : palavras) {
                     if (palavra.equalsIgnoreCase(word)) {
                        counterFile++;
                        countWord++;
                     }
                  }
               }
            } catch (IOException e) {
               System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
            }
         }
      }
      System.out.println("Em todos os arquivos, a palavra '" + word + "' foi encontrada " + countWord + " vezes.");
   }
}