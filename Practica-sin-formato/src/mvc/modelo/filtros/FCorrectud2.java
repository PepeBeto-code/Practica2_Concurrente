package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar Correctud2 a una imagen
 */
public class FCorrectud2 extends Filtros {
        
    public FCorrectud2() {
        super();
    }
    
    /**
     * Metodo que aplica correctud2 de forma concurrente
     */
    @Override
    public void concurrente(int factual) {
        for (int j = 0; j < ancho; j++) {
            float rojo = (entrada[factual][j].getRed() * 0.2126f);
            float verd = (entrada[factual][j].getGreen() * 0.7152f);
            float azul = (entrada[factual][j].getBlue() * 0.0722f);
            int gray = (int) (rojo + verd + azul);
            resultado[factual][j] = new Color(gray, gray, gray);
        }
    }
    
    /**
     * Metodo que aplica correctud2 de forma secuencial
     */
    @Override
    public void secuencial(){
        for(int i = 0; i < largo; i++) {
            for(int j = 0; j < ancho; j++) {
                float rojo = (entrada[i][j].getRed() * 0.2126f);
                float verd = (entrada[i][j].getGreen() * 0.7152f);
                float azul = (entrada[i][j].getBlue() * 0.0722f);
                int gray = (int) (rojo + verd + azul);
                resultado[i][j] = new Color(gray, gray, gray);
            }
        }
    }
}
