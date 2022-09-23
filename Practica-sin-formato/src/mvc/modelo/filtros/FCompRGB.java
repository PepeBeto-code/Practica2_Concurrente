package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar COmponente RGB a una imagen
 */
public class FCompRGB extends Filtros {
    
    private int r, g, b;
    
    public FCompRGB() {
        super();
    }
    
    /**
     * Metodo que asigna cuanto se va agregar o sustraer de los canales de 
     * colores de la imagen
     * @param r Un entero que es cuanto rojo se añade o sustrae
     * @param g Un entero que es cuanto verde se añade o sustrae
     * @param b Un entero que es cuanto azul se añade o sustrae
     */
    public void asignarRGB(int r, int g, int b) {
        this.r = r;
        this.g = b;
        this.b = b;
    }
    
    /**
     * Metodo que aplica componente rgb de forma concurrente
     */
    @Override
    public void concurrente(int factual) {
        for(int j = 0; j < ancho; j++) {
            int rojo = entrada[factual][j].getRed() + r;
            int verd = entrada[factual][j].getGreen() + g;
            int azul = entrada[factual][j].getBlue() + b;
            if(rojo < 0) rojo = 0; if(rojo > 255) rojo = 255;
            if(verd < 0) verd = 0; if(verd > 255) verd = 255;
            if(azul < 0) azul = 0; if(azul > 255) azul = 255;
            resultado[factual][j] = new Color(rojo, verd, azul);
        }
    }
    
    /**
     * Metodo que aplica componente rgb de forma secuencial
     */
    @Override
    public void secuencial(){
        for(int i = 0; i < largo; i++) {
            for(int j = 0; j < ancho; j++) {
                int rojo = entrada[i][j].getRed() + r;
                int verd = entrada[i][j].getGreen() + g;
                int azul = entrada[i][j].getBlue() + b;
                if(rojo < 0) rojo = 0; if(rojo > 255) rojo = 255;
                if(verd < 0) verd = 0; if(verd > 255) verd = 255;
                if(azul < 0) azul = 0; if(azul > 255) azul = 255;
                resultado[i][j] = new Color(rojo, verd, azul);
            }
        }
    }
}
