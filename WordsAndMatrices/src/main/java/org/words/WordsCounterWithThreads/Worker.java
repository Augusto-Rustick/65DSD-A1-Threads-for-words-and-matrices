package org.words.WordsCounterWithThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Worker implements Runnable {
    private File[] arquivos;
    private String word;
    private Semaphore semaphore;
    private int countWord, start, end;

    public Worker(File[] arquivos, String word, Semaphore semaphore, int countWord,int start,int end) {
        this.arquivos = arquivos;
        this.word = word;
        this.semaphore = semaphore;
        this.countWord = countWord;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        // Itera para cada arquivo de txt
        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    int counterFile = 0;
                    while ((linha = leitor.readLine()) != null) {
                        String[] palavras = linha.split("\\s+");
                        for (String palavra : palavras) {
                            if (palavra.equals(word)) {
                                counterFile++;
                                // Adquire o semáforo para exclusão mútua
                                semaphore.acquire();
                                countWord++;
                                // Libera o semáforo
                                semaphore.release();
                            }
                        }
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
                }
            }
        }
        System.out.println("Em todos os arquivos, a palavra '" + word + "' foi encontrada " + countWord + " vezes.");
    }
}
