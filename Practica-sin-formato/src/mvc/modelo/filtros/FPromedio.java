package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar Promedio a una imagen
 */
public class FPromedio extends Filtros {
    
    public FPromedio() {
        super();
    }
    
    /**
     * Metodo que aplica promedio de forma concurrente
     */
    @Override
    public void concurrente(int factual) {
        for (int j = 0; j < ancho; j++) {
            int rojo = entrada[factual][j].getRed();
            int verd = entrada[factual][j].getGreen();
            int azul = entrada[factual][j].getBlue();
            int gray = (int) ((rojo + verd + azul) * 0.3);
            resultado[factual][j] = new Color(gray, gray, gray);
        }
    }
    
    /**
     * Metodo que aplica promedio de forma secuencial
     */
    @Override
    public void secuencial(){
        for(int i = 0; i < largo; i++) {
            for(int j = 0; j < ancho; j++) {
                int rojo = entrada[i][j].getRed();
                int verd = entrada[i][j].getGreen();
                int azul = entrada[i][j].getBlue();
                int gray = (int) ((rojo + verd + azul) * 0.3f);
                resultado[i][j] = new Color(gray, gray, gray);
            }
        }
    }
}
