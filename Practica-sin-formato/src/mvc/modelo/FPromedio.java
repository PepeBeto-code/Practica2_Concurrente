package mvc.modelo;

import java.awt.Color;
import java.util.concurrent.Semaphore;

import lectorRecursos.ImagenRes;

public class FPromedio extends Filtros {
    
    private int fila = 0;
    private Semaphore sem;
    
    public FPromedio(String nombre) {
        super(nombre);
        extension = "_promedio";
        sem = new Semaphore(1);
    }
    
    @Override
    public void concurrente() {
        int factual = 0;
        try{
            sem.acquire();
            factual = fila;
            fila++;
            sem.release();
            if(factual >= ImagenRes.pixeles.length) return;
        } catch(InterruptedException e) {}
        for (int j = 0; j < ImagenRes.pixeles[0].length; j++) {
            int rojo = ImagenRes.pixeles[factual][j].getRed();
            int verd = ImagenRes.pixeles[factual][j].getGreen();
            int azul = ImagenRes.pixeles[factual][j].getBlue();
            int gray = (int) ((rojo + verd + azul) * 0.3);
            resultado[factual][j] = new Color(gray, gray, gray);
        }
    }
    
    /**
     * Metodo que aplica correctud2 de forma secuencial
     */
    public void secuencial(){
        for(int i = 0; i < ImagenRes.pixeles.length; i++) {
            for(int j = 0; j < ImagenRes.pixeles[0].length; j++) {
                int rojo = ImagenRes.pixeles[i][j].getRed();
                int verd = ImagenRes.pixeles[i][j].getGreen();
                int azul = ImagenRes.pixeles[i][j].getBlue();
                int gray = (int) ((rojo + verd + azul) * 0.3f);
                resultado[i][j] = new Color(gray, gray, gray);
            }
        }
    }
}
