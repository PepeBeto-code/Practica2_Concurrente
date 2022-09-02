package mvc.modelo;

import java.lang.ArithmeticException;

import java.util.ArrayList;
import java.util.List;

public class MultMatrices implements Runnable {
    int[][] matrizA;
    int[][] matrizB;
    int[][] resultado;
    
    private int fila;

    /**
     * Constructor de la clase
     * @param matrizA Un arreglo de enteros
     * @param matrizB Otro arreglo de enteros
     * @throws ArithmeticException Si la cantidad de columnas de 
     * <code>matrizA</code> no coinciden con la cantidad de filas de 
     * <code>matrizB</code>
     */
    public MultMatrices(int[][] matrizA, int[][] matrizB) throws ArithmeticException {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        if(matrizA[0].length != matrizB.length) 
            throw new ArithmeticException("El numero de columnas de la primer" +
            "a matriz debe ser igual al numero de filas de la segunda");
        this.resultado = new int[matrizA.length][matrizB[0].length];
        this.fila = 0;
    }

    @Override
    public void run() {
        concurrente();
    }

    /**
     * Metodo que multiplica las dos matrices de forma concurrente, asignando un
     * hilo a cada fila de las matrices
     * @param fila El numero de la fila que se encargara el hilo
     */
    public synchronized void concurrente(){
        for (int j = 0; j < matrizB[0].length; j++) {
            for (int k = 0; k < matrizA[0].length; k++) {
                resultado[fila][j] += matrizA[fila][k] * matrizB[k][j];
            }
        }
        fila++;
    }
    
    /**
     * Metodo que multiplica las dos matrices de forma secuencial
     */
    public void secuencial(){
        for(int i = 0; i < matrizA.length; i++) {
            for(int j = 0; j < matrizB[0].length; j++) {
                for(int k = 0; k < matrizA[0].length; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }
    
    /**
     * MEtodo que muestra en consola el resultado de multiplicar las matrices
     */
    public void muestra(){
        for(int i = 0; i < matrizA.length; ++i) {
            for(int j = 0; j < matrizB.length; j++) {
                System.out.print(resultado[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
