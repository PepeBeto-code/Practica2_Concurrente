package mvc.modelo;

import java.lang.ArithmeticException;

import java.util.ArrayList;
import java.util.List;

public class SopaLetras implements Runnable {
    char[][] sopa;
    
    private String[] resultado;

    /**
     * Constructor de la clase
     * @param sopa Una matriz de caracteres
     */
    public MultMatrices(char[][] sopa) {
        this.sopa = sopa;
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
        
    }
    
    /**
     * Metodo que multiplica las dos matrices de forma secuencial
     */
    public void secuencial(){
        for(int i = 0; i < matrizA.length; i++) {
            for(int j = 0; j < matrizB[0].length; j++) {
                for(int k = 0; k < matrizA[0].length; k++) {
                
                }
            }
        }
    }
    
    /**
     * MEtodo que muestra en consola el resultado de multiplicar las matrices
     */
    public void muestra(){
        
    }
}
