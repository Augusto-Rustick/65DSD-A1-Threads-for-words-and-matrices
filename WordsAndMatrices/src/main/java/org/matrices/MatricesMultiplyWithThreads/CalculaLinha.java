package org.matrices.MatricesMultiplyWithThreads;

public class CalculaLinha extends Thread {
   private int[][] matrizA;
   private int[][] matrizB;
   private int[][] matrizResultado;
   private int linhaInicial;
   private int linhaFinal;

   public CalculaLinha(int[][] matrizA, int[][] matrizB, int[][] matrizResultado, int linhaInicial, int linhaFinal) {
      this.matrizA = matrizA;
      this.matrizB = matrizB;
      this.matrizResultado = matrizResultado;
      this.linhaInicial = linhaInicial;
      this.linhaFinal = linhaFinal;
   }

   public void run() {
      for (int i = linhaInicial; i < linhaFinal; i++) {
         for (int j = 0; j < matrizB[0].length; j++) {
            for (int k = 0; k < matrizA[0].length; k++) {
               matrizResultado[i][j] += matrizA[i][k] * matrizB[k][j];
            }
         }
      }
   }
}
