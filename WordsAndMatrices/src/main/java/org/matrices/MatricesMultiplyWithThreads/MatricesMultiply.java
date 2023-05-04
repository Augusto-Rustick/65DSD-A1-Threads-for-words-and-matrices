package org.matrices.MatricesMultiplyWithThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class MatricesMultiply {
   public static void main(String[] args) {
      Instant startTime = Instant.now();
      // escolha do caso a ser calculado e quantidade de threads
      int caso = 3;
      int numThreads = 8;
      // coleta um arrei com os arquivos de texto do diretorio do dataset
      String pasta = "src/main/java/org/matrices/dataset/caso" + caso;
      File diretorio = new File(pasta);
      File[] arquivos = diretorio.listFiles();

      // Ler a matriz A do arquivo A.txt
      int[][] matrizA = lerMatrizDoArquivo(arquivos[0]);

      // Ler a matriz B do arquivo B.txt
      int[][] matrizB = lerMatrizDoArquivo(arquivos[1]);

      // Verificar se as dimensões das matrizes são compatíveis para a multiplicação
      if (matrizA[0].length != matrizB.length) {
         System.out.println("As dimensões das matrizes não são compatíveis para a multiplicação");
         return;
      }

      // Realizar a multiplicação das matrizes
      int[][] matrizResultado = new int[matrizA.length][matrizB[0].length];

      // Criar threads para cada linha da matriz resultante
      int tamanhoFatia = matrizA.length / numThreads;
      CalculaLinha[] threads = new CalculaLinha[numThreads];

      for (int i = 0; i < numThreads; i++) {
         int inicio = i * tamanhoFatia;
         int fim = (i == numThreads - 1) ? matrizA.length : (i + 1) * tamanhoFatia;
         threads[i] = new CalculaLinha(matrizA, matrizB, matrizResultado, inicio, fim);
         threads[i].start();
      }

      for (int i = 0; i < numThreads; i++) {
         try {
            threads[i].join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      Instant endTime = Instant.now();
      long duration = Duration.between(startTime, endTime).toMillis();
      System.out.println("Duração: " + duration + " milisseconds");
      // Imprimir o resultado na tela
      if (caso == 1) {
         for (int i = 0; i < matrizResultado.length; i++) {
            for (int j = 0; j < matrizResultado[0].length; j++) {
               System.out.print(matrizResultado[i][j] + " ");
            }
            System.out.println();
         }
      }
   }

   // Função para ler a matriz de um arquivo
   private static int[][] lerMatrizDoArquivo(File arquivo) {
      try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
         int linhas = Integer.parseInt(leitor.readLine());
         int colunas = Integer.parseInt(leitor.readLine());
         int[][] matriz = new int[linhas][colunas];
         for (int i = 0; i < linhas; i++) {
            String[] elementos = leitor.readLine().split(" ");
            for (int j = 0; j < colunas; j++) {
               matriz[i][j] = Integer.parseInt(elementos[j]);
            }
         }
         return matriz;
      } catch (IOException e) {
         System.out.println("Erro ao ler o arquivo " + arquivo + ": " + e.getMessage());
         return null;
      }
   }
}
