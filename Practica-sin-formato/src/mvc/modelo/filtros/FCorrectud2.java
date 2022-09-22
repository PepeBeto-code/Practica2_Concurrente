package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public class FCorrectud2 extends Filtros {
        
    public FCorrectud2() {
        super();
        extension = "_correctud2";
    }
    
    @Override
    public void concurrente(int factual) {
        for (int j = 0; j < ImagenRes.pixeles[0].length; j++) {
            float rojo = (ImagenRes.pixeles[factual][j].getRed() * 0.2126f);
            float verd = (ImagenRes.pixeles[factual][j].getGreen() * 0.7152f);
            float azul = (ImagenRes.pixeles[factual][j].getBlue() * 0.0722f);
            int gray = (int) (rojo + verd + azul);
            resultado[factual][j] = new Color(gray, gray, gray);
        }
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
}
