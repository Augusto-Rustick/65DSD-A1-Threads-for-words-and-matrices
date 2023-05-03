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
        //pergunta ao user a palavra desejada
        Scanner s = new Scanner(System.in);
        System.out.println("Qual palavra você deseja procurar?");
        String word = s.next();
        //intera para cada arquivo de txt
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
                                countWord++;
                            }
                        }
                    }
                    System.out.println("No arquivo " + arquivo.getName() + ", a palavra '" + word + "' foi encontrada " + counterFile + " vezes.");
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
                }
            }
        }

        System.out.println("Número de arquivos encontrados: " + arquivos.length);
        System.out.println("Em todos os arquivos, a palavra '" + word + "' foi encontrada " + countWord + " vezes.");
    }
}