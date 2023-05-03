package org.words.WordsCounterWithThreads;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class WordsCounter {
    public static void main(String[] args) {
        //coleta um arrei com os arquivos de texto do diretorio do dataset
        String pasta = "src/main/java/org/words/dataset";
        File diretorio = new File(pasta);
        File[] arquivos = diretorio.listFiles();

        //variável de auxílio para o contador
        int countWord = 0;

        //pergunta ao usuário a palavra desejada
        String word = "hello";

        // Cria um semáforo com uma permissão
        Semaphore mutex = new Semaphore(1);

        //coleta a metade dos arquivos para as threads
        int total = arquivos.length;
        int metade =total / 2;

        // Cria as threads
        Thread t1 = new Thread(new Worker(arquivos, word, mutex, countWord, 0, metade));
        Thread t2 = new Thread(new Worker(arquivos, word, mutex, countWord, metade+1, total));

        // Inicia as threads
        t1.start();
        t2.start();

        // Espera pelas threads terminarem
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Em todos os arquivos, a palavra '" + word + "' foi encontrada " + countWord + " vezes.");
    }
}