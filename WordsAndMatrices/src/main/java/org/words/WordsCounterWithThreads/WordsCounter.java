package org.words.WordsCounterWithThreads;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordsCounter {
   public static void main(String[] args) {
      // coleta um array com os arquivos de texto do diretorio do dataset
      String pasta = "src/main/java/org/words/dataset";
      File diretorio = new File(pasta);
      File[] arquivos = diretorio.listFiles();

      // pergunta ao usuário a palavra desejada
      //hello   coffee   traditional
      String word = "traditional";

      // Cria um contador
      Contador contador = new Contador();

      // coleta a quantidade de threads desejada
      int numThreads = 8;

      // calcula quantos arquivos cada thread deve processar
      int total = arquivos.length;
      int arquivosPorThread = total / numThreads;

      // Cria a lista de threads
      List<Leitor> threads = new ArrayList<>();

      // Cria as threads
      for (int i = 0; i < numThreads; i++) {
         int start = i * arquivosPorThread;
         int end = (i == numThreads - 1) ? total - 1 : start + arquivosPorThread - 1;
         Leitor thread = new Leitor(arquivos, word, contador, start, end);
         threads.add(thread);
      }

      // Inicia as threads
      for (Leitor thread : threads) {
         thread.start();
      }

      // Espera pelas threads terminarem
      for (Leitor thread : threads) {
         try {
            thread.join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      System.out.println("Em todos os arquivos, a palavra '" + word + "' foi encontrada " + contador.getCountWord() + " vezes.");
   }
}
