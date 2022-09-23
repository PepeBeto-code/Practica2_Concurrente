package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase abstracta que contrine los metodos y atributos que todos los filtros 
 * deben tener para ser realizados
 */
public abstract class Filtros implements Runnable {
    
    protected Color[][] entrada, resultado;
    protected int ancho, largo;
    
    public Filtros() { }
   
    /**
     * Metodo que inicia una matriz de Color que representa una imagen para que 
     * se aplique el filtro
     * @param pixeles Un Color[][]
     */
    public void iniciarMatriz(Color[][] pixeles) {
        entrada = pixeles;
        ancho = entrada[0].length;
        largo = entrada.length;
        
        resultado = new Color[largo][ancho];
    }
    
    
    @Override
    public void run() {
        concurrente(Integer.parseInt(Thread.currentThread().getName()));
    }
    
    /**
     * Metodo a implementar en todos los filtros que sera ejecutado por el 
     * metodo run
     * @param factual La fila de la que se encargara el hilo
     */
    public abstract void concurrente(int factual);
    
    /**
     * Metodo a implementar en todos los filtros que se ejecuta de manera secuencial
     */
    public abstract void secuencial();
    
    /**
     * Metodo que devuelve el resultado de aplicar el filtro
     * @param Una matriz Color
     */
    public Color[][] obtenerResultado() {
        return resultado;
    }
}
