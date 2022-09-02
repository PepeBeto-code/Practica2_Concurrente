package mvc.modelo;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public class FCorrectud2 extends Filtros {
    
    private int fila = 0;
    
    public FCorrectud2(String nombre) {
        super(nombre);
        extension = "_correctud2";
    }
    
    @Override
    public synchronized void concurrente() {
        for (int j = 0; j < ImagenRes.pixeles[0].length; j++) {
            float rojo = (ImagenRes.pixeles[fila][j].getRed() * 0.2126f);
            float verd = (ImagenRes.pixeles[fila][j].getGreen() * 0.7152f);
            float azul = (ImagenRes.pixeles[fila][j].getBlue() * 0.0722f);
            int gray = (int) (rojo + verd + azul);
            resultado[fila][j] = new Color(gray, gray, gray);
        }
        fila++;
    }
    
    /**
     * Metodo que aplica correctud2 de forma secuencial
     */
    public void secuencial(){
        for(int i = 0; i < ImagenRes.pixeles.length; i++) {
            for(int j = 0; j < ImagenRes.pixeles[0].length; j++) {
                float rojo = (ImagenRes.pixeles[i][j].getRed() * 0.2126f);
                float verd = (ImagenRes.pixeles[i][j].getGreen() * 0.7152f);
                float azul = (ImagenRes.pixeles[i][j].getBlue() * 0.0722f);
                int gray = (int) (rojo + verd + azul);
                resultado[i][j] = new Color(gray, gray, gray);
            }
        }
    }
    
    /**
     * Metodo que obtiene la matriz resultado
     */
    public Color[][] obtenerResultado() {
        Color [][] copia = new Color[resultado.length][];
        for(int i = 0; i < resultado.length; i++) {
            Color[] aMatrix = resultado[i];
            int aLength = aMatrix.length;
            copia[i] = new Color[aLength];
            System.arraycopy(aMatrix, 0, copia[i], 0, aLength);
        }
        return copia;
    }
}
