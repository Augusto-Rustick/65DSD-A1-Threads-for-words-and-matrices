package org.words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class WordsCounter {
    public static void main(String[] args) {
        String pasta = "src/main/java/org/words/dataset";
        File diretorio = new File(pasta);

        File[] arquivos = diretorio.listFiles();
        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    int contador = 0;

                    while ((linha = leitor.readLine()) != null) {
                        String[] palavras = linha.split("\\s+"); // quebra a linha em palavras separadas por espaços
                        for (String palavra : palavras) {
                            if (palavra.equals("hello")) {
                                contador++;
                            }
                        }
                    }

                    System.out.println("No arquivo " + arquivo.getName() + ", a palavra 'hello' foi encontrada " + contador + " vezes.");
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}