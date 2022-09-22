package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public class FPromedio extends Filtros {
    
    public FPromedio() {
        super();
        extension = "_promedio";
    }
    
    @Override
    public void concurrente(int factual) {
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
