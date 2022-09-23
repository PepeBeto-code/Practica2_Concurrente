package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar Sharpen a una imagen
 */
public class FSharpen extends Filtros {
    
    private int[][] sharpen = {{-1,-1,-1},
                               {-1, 9,-1},
                               {-1,-1,-1}};
    
    public FSharpen() {
        super();
    }
    
    /**
     * Metodo que aplica sharpen de forma concurrente
     */
    @Override
    public void concurrente(int factual) {
        Color pixel;
        for(int j = 0; j < ancho; j++) {
                int rojo = 0;
                int verd = 0;
                int azul = 0;
                //Pixel (-1,-1)no
                if(factual-1 > -1 && j-1 > -1) {
                    pixel = entrada[factual-1][j-1];
                    rojo += pixel.getRed()* sharpen[0][0];
                    verd += pixel.getGreen()* sharpen[0][0];
                    azul += pixel.getBlue()* sharpen[0][0];
                }
                //Pixel (0,-1)n
                if(j-1 > -1) {
                    pixel = entrada[factual][j-1];
                    rojo += pixel.getRed()* sharpen[0][1];
                    verd += pixel.getGreen()* sharpen[0][1];
                    azul += pixel.getBlue()* sharpen[0][1];
                }
                //Pixel (1,-1)ne
                if(factual+1 < largo && j-1 > -1) {
                    pixel = entrada[factual+1][j-1];
                    rojo += pixel.getRed()* sharpen[0][2];
                    verd += pixel.getGreen()* sharpen[0][2];
                    azul += pixel.getBlue()* sharpen[0][2];
                }
                //Pixel (-1,0)o
                if(factual-1 > -1) {
                    pixel = entrada[factual-1][j];
                    rojo += pixel.getRed()* sharpen[1][0];
                    verd += pixel.getGreen()* sharpen[1][0];
                    azul += pixel.getBlue()* sharpen[1][0];
                }
                //Pixel (0,0)c
                pixel = entrada[factual][j];
                rojo += pixel.getRed()* sharpen[1][1];
                verd += pixel.getGreen()* sharpen[1][1];
                azul += pixel.getBlue()* sharpen[1][1];
                //Pixel (1,0)e
                if(factual+1 < largo) {
                    pixel = entrada[factual+1][j];
                    rojo += pixel.getRed()* sharpen[1][2];
                    verd += pixel.getGreen()* sharpen[1][2];
                    azul += pixel.getBlue()* sharpen[1][2];
                }
                //Pixel (0,1)so
                if(factual-1 > -1 && j+1 < ancho) {
                    pixel = entrada[factual-1][j+1];
                    rojo += pixel.getRed()* sharpen[2][0];
                    verd += pixel.getGreen()* sharpen[2][0];
                    azul += pixel.getBlue()* sharpen[2][0];
                }
                //Pixel (0,1)s
                if(j+1 < ancho) {
                    pixel = entrada[factual][j+1];
                    rojo += pixel.getRed()* sharpen[2][1];
                    verd += pixel.getGreen()* sharpen[2][1];
                    azul += pixel.getBlue()* sharpen[2][1];
                }
                //Pixel (1,1)se
                if(factual+1 < largo && j+1 < ancho) {
                    pixel = entrada[factual+1][j+1];
                    rojo += pixel.getRed()* sharpen[2][2];
                    verd += pixel.getGreen()* sharpen[2][2];
                    azul += pixel.getBlue()* sharpen[2][2];
                }
            
                rojo = (rojo > 255)? 255:(rojo < 0)? 0:rojo;
                verd = (verd > 255)? 255:(verd < 0)? 0:verd;
                azul = (azul > 255)? 255:(azul < 0)? 0:azul;
                
                resultado[factual][j] = new Color((int)rojo,(int)verd,(int)azul);
            }
    }
    
    /**
     * Metodo que aplica sharpen de forma secuencial
     */
    @Override
    public void secuencial() {
        Color pixel;
        for(int i = 0; i < largo; i++) {
            for(int j = 0; j < ancho; j++) {
                int rojo = 0;
                int verd = 0;
                int azul = 0;
                //Pixel (-1,-1)no
                if(i-1 > -1 && j-1 > -1) {
                    pixel = entrada[i-1][j-1];
                    rojo += pixel.getRed()* sharpen[0][0];
                    verd += pixel.getGreen()* sharpen[0][0];
                    azul += pixel.getBlue()* sharpen[0][0];
                }
                //Pixel (0,-1)n
                if(j-1 > -1) {
                    pixel = entrada[i][j-1];
                    rojo += pixel.getRed()* sharpen[0][1];
                    verd += pixel.getGreen()* sharpen[0][1];
                    azul += pixel.getBlue()* sharpen[0][1];
                }
                //Pixel (1,-1)ne
                if(i+1 < largo && j-1 > -1) {
                    pixel = entrada[i+1][j-1];
                    rojo += pixel.getRed()* sharpen[0][2];
                    verd += pixel.getGreen()* sharpen[0][2];
                    azul += pixel.getBlue()* sharpen[0][2];
                }
                //Pixel (-1,0)o
                if(i-1 > -1) {
                    pixel = entrada[i-1][j];
                    rojo += pixel.getRed()* sharpen[1][0];
                    verd += pixel.getGreen()* sharpen[1][0];
                    azul += pixel.getBlue()* sharpen[1][0];
                }
                //Pixel (0,0)c
                pixel = entrada[i][j];
                rojo += pixel.getRed()* sharpen[1][1];
                verd += pixel.getGreen()* sharpen[1][1];
                azul += pixel.getBlue()* sharpen[1][1];
                //Pixel (1,0)e
                if(i+1 < largo) {
                    pixel = entrada[i+1][j];
                    rojo += pixel.getRed()* sharpen[1][2];
                    verd += pixel.getGreen()* sharpen[1][2];
                    azul += pixel.getBlue()* sharpen[1][2];
                }
                //Pixel (0,1)so
                if(i-1 > -1 && j+1 < ancho) {
                    pixel = entrada[i-1][j+1];
                    rojo += pixel.getRed()* sharpen[2][0];
                    verd += pixel.getGreen()* sharpen[2][0];
                    azul += pixel.getBlue()* sharpen[2][0];
                }
                //Pixel (0,1)s
                if(j+1 < ancho) {
                    pixel = entrada[i][j+1];
                    rojo += pixel.getRed()* sharpen[2][1];
                    verd += pixel.getGreen()* sharpen[2][1];
                    azul += pixel.getBlue()* sharpen[2][1];
                }
                //Pixel (1,1)se
                if(i+1 < largo && j+1 < ancho) {
                    pixel = entrada[i+1][j+1];
                    rojo += pixel.getRed()* sharpen[2][2];
                    verd += pixel.getGreen()* sharpen[2][2];
                    azul += pixel.getBlue()* sharpen[2][2];
                }
            
                rojo = (rojo > 255)? 255:(rojo < 0)? 0:rojo;
                verd = (verd > 255)? 255:(verd < 0)? 0:verd;
                azul = (azul > 255)? 255:(azul < 0)? 0:azul;
                
                resultado[i][j] = new Color((int)rojo,(int)verd,(int)azul);
            }
        }
    }
}
