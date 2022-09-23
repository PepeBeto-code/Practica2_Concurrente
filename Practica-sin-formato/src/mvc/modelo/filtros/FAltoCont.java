package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar Alto Contraste a una imagen
 */
public class FAltoCont extends Filtros {
    
    public FAltoCont() {
        super();
    }
    
    /**
     * Metodo que aplica altocontraste de forma concurrente
     */
    public void concurrente(int factual) {
        for (int j = 0; j < ancho; j++) {
            float rojo = (entrada[factual][j].getRed() * 0.2126f);
            float verd = (entrada[factual][j].getGreen() * 0.7152f);
            float azul = (entrada[factual][j].getBlue() * 0.0722f);
            int gray = ((rojo + verd + azul) > 127)? 255: 0;
            resultado[factual][j] = new Color(gray, gray, gray);
        }
    }
    
    /**
     * Metodo que aplica altocontraste de forma secuencial
     */
    @Override
    public void secuencial() {
        for(int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                float rojo = (entrada[i][j].getRed() * 0.2126f);
                float verd = (entrada[i][j].getGreen() * 0.7152f);
                float azul = (entrada[i][j].getBlue() * 0.0722f);
                int gray = ((rojo + verd + azul) > 127)? 255: 0;
                resultado[i][j] = new Color(gray, gray, gray);
            }
        }
    }
}
