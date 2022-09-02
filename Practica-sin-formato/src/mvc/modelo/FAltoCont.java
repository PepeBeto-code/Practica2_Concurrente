package mvc.modelo;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public class FAltoCont extends Filtros {
    
    private int fila = 0;
    
    public FAltoCont(String nombre) {
        super(nombre);
        extension = "_altocontraste";
    }
    
    @Override
    public synchronized void concurrente() {
        for (int j = 0; j < ImagenRes.pixeles[0].length; j++) {
            float rojo = (ImagenRes.pixeles[fila][j].getRed() * 0.2126f);
            float verd = (ImagenRes.pixeles[fila][j].getGreen() * 0.7152f);
            float azul = (ImagenRes.pixeles[fila][j].getBlue() * 0.0722f);
            int gray = ((rojo + verd + azul) > 127)? 255: 0;
            resultado[fila][j] = new Color(gray, gray, gray);
        }
        fila++;
    }
    
    /**
     * Metodo que aplica altocontraste de forma secuencial
     */
    public void secuencial() {
        FCorrectud2 g = new FCorrectud2(nombre);
        g.secuencial();
        Color[][] gris = g.obtenerResultado();
        for(int i = 0; i < gris.length; i++) {
            for(int j = 0; j < gris[0].length; j++) {
                int rojo = (gris[i][j].getRed());
                int verd = (gris[i][j].getGreen());
                int azul = (gris[i][j].getBlue());
                int gray = ((rojo + verd + azul) > 127)? 255: 0;
                resultado[i][j] = new Color(gray, gray, gray);
            }
        }
    }
}
