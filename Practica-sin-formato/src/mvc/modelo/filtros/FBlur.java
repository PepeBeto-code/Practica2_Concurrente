package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar Blur a una imagen
 */
public class FBlur extends Filtros {
    
    private int fila = 0;
    private float[][] blur = {{0.0f,0.2f,0.0f},
                              {0.2f,0.2f,0.2f},
                              {0.0f,0.2f,0.0f}};
                              
    public FBlur() {
        super();
    }
    
    /**
     * Metodo que aplica blur de forma concurrente
     */
    @Override
    public void concurrente(int factual) {
        Color pixel;
        for(int j = 0; j < ancho; j++) {
            float rojo = 0f;
            float verd = 0f;
            float azul = 0f; 
            //Pixel (0,-1)n
            if(j-1 > -1) {
                pixel = entrada[factual][j-1];
                rojo += pixel.getRed()* blur[0][1];
                verd += pixel.getGreen()* blur[0][1];
                azul += pixel.getBlue()* blur[0][1];
            }
            //Pixel (-1,0)o
            if(factual-1 > -1) {
                pixel = entrada[factual-1][j];
                rojo += pixel.getRed()* blur[1][0];
                verd += pixel.getGreen()* blur[1][0];
                azul += pixel.getBlue()* blur[1][0];
            }
            //Pixel (0,0)c
            pixel = entrada[factual][j];
            rojo += pixel.getRed()* blur[1][1];
            verd += pixel.getGreen()* blur[1][1];
            azul += pixel.getBlue()* blur[1][1];
            //Pixel (1,0)e
            if(factual+1 < largo) {
                pixel = entrada[factual+1][j];
                rojo += pixel.getRed()* blur[1][2];
                verd += pixel.getGreen()* blur[1][2];
                azul += pixel.getBlue()* blur[1][2];
            }
            //Pixel (0,1)s
            if(j+1 < ancho) {
                pixel = entrada[factual][j+1];
                rojo += pixel.getRed()* blur[2][1];
                verd += pixel.getGreen()* blur[2][1];
                azul += pixel.getBlue()* blur[2][1];
            }
        
            rojo = (rojo > 255)? 255:(rojo < 0)? 0:rojo;
            verd = (verd > 255)? 255:(verd < 0)? 0:verd;
            azul = (azul > 255)? 255:(azul < 0)? 0:azul;
            
            resultado[factual][j] = new Color((int)rojo,(int)verd,(int)azul);
        }
    }
    
    /**
     * Metodo que aplica blur de forma secuencial
     */
    @Override
    public void secuencial() {
        Color pixel;
        for(int i = 0; i < largo; i++) {
            for(int j = 0; j < ancho; j++) {
                float rojo = 0f;
                float verd = 0f;
                float azul = 0f; 
                //Pixel (0,-1)n
                if(j-1 > -1) {
                    pixel = entrada[i][j-1];
                    rojo += pixel.getRed()* blur[0][1];
                    verd += pixel.getGreen()* blur[0][1];
                    azul += pixel.getBlue()* blur[0][1];
                }
                //Pixel (-1,0)o
                if(i-1 > -1) {
                    pixel = entrada[i-1][j];
                    rojo += pixel.getRed()* blur[1][0];
                    verd += pixel.getGreen()* blur[1][0];
                    azul += pixel.getBlue()* blur[1][0];
                }
                //Pixel (0,0)c
                pixel = entrada[i][j];
                rojo += pixel.getRed()* blur[1][1];
                verd += pixel.getGreen()* blur[1][1];
                azul += pixel.getBlue()* blur[1][1];
                //Pixel (1,0)e
                if(i+1 < largo) {
                    pixel = entrada[i+1][j];
                    rojo += pixel.getRed()* blur[1][2];
                    verd += pixel.getGreen()* blur[1][2];
                    azul += pixel.getBlue()* blur[1][2];
                }
                //Pixel (0,1)s
                if(j+1 < ancho) {
                    pixel = entrada[i][j+1];
                    rojo += pixel.getRed()* blur[2][1];
                    verd += pixel.getGreen()* blur[2][1];
                    azul += pixel.getBlue()* blur[2][1];
                }
            
                rojo = (rojo > 255)? 255:(rojo < 0)? 0:rojo;
                verd = (verd > 255)? 255:(verd < 0)? 0:verd;
                azul = (azul > 255)? 255:(azul < 0)? 0:azul;
                
                resultado[i][j] = new Color((int)rojo,(int)verd,(int)azul);
            }
        }
    }
}
