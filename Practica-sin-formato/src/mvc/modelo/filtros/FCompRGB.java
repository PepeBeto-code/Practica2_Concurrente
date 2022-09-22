package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public class FCompRGB extends Filtros {
    
    private int r, g, b;
    
    public FCompRGB() {
        super();
        extension = "_crgb";
    }

    public FCompRGB(int r, int g, int b) {
        super();
        this.r = r;
        this.g = g;
        this.b = b;
        extension = "_crgb";
    }
    
    public void asignarRGB(int r, int g, int b) {
        this.r = r;
        this.g = b;
        this.b = b;
    }
    
    @Override
    public void concurrente(int factual) {
        for(int j = 0; j < ImagenRes.pixeles[0].length; j++) {
            int rojo = ImagenRes.pixeles[factual][j].getRed() + r;
            int verd = ImagenRes.pixeles[factual][j].getGreen() + g;
            int azul = ImagenRes.pixeles[factual][j].getBlue() + b;
            if(rojo < 0) rojo = 0; if(rojo > 255) rojo = 255;
            if(verd < 0) verd = 0; if(verd > 255) verd = 255;
            if(azul < 0) azul = 0; if(azul > 255) azul = 255;
            resultado[factual][j] = new Color(rojo, verd, azul);
        }
    }
    
    /**
     * Metodo que aplica componente rgb de forma secuencial
     */
    public void secuencial(){
        for(int i = 0; i < ImagenRes.pixeles.length; i++) {
            for(int j = 0; j < ImagenRes.pixeles[0].length; j++) {
                int rojo = ImagenRes.pixeles[i][j].getRed() + r;
                int verd = ImagenRes.pixeles[i][j].getGreen() + g;
                int azul = ImagenRes.pixeles[i][j].getBlue() + b;
                if(rojo < 0) rojo = 0; if(rojo > 255) rojo = 255;
                if(verd < 0) verd = 0; if(verd > 255) verd = 255;
                if(azul < 0) azul = 0; if(azul > 255) azul = 255;
                resultado[i][j] = new Color(rojo, verd, azul);
            }
        }
    }
}
