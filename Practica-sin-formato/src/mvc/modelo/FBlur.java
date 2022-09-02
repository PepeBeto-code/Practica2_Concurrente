package mvc.modelo;

import java.awt.Color;
import java.util.concurrent.Semaphore;

import lectorRecursos.ImagenRes;

public class FBlur extends Filtros {
    
    private int fila = 0;
    private float[][] blur = {{0.0f,0.2f,0.0f},
                              {0.2f,0.2f,0.2f},
                              {0.0f,0.2f,0.0f}};
    private Semaphore sem;
    
    public FBlur(String nombre) {
        super(nombre);
        extension = "_blur";
        sem = new Semaphore(1);
    }
    
    @Override
    public synchronized void concurrente() {
        fila++;
    }
    
    /**
     * Metodo que aplica blur de forma secuencial
     */
    public void secuencial() {
        Color pixel;
        for(int i = 0; i < largo; i++) {
            for(int j = 0; j < ancho; j++) {
                float rojo = 0f;
                float verd = 0f;
                float azul = 0f; 
                //Pixel (0,-1)n
                if(j-1 > -1) {
                    pixel = ImagenRes.pixeles[i][j-1];
                    rojo += pixel.getRed()* blur[0][1];
                    verd += pixel.getGreen()* blur[0][1];
                    azul += pixel.getBlue()* blur[0][1];
                }
                //Pixel (-1,0)o
                if(i-1 > -1) {
                    pixel = ImagenRes.pixeles[i-1][j];
                    rojo += pixel.getRed()* blur[1][0];
                    verd += pixel.getGreen()* blur[1][0];
                    azul += pixel.getBlue()* blur[1][0];
                }
                //Pixel (0,0)c
                pixel = ImagenRes.pixeles[i][j];
                rojo += pixel.getRed()* blur[1][1];
                verd += pixel.getGreen()* blur[1][1];
                azul += pixel.getBlue()* blur[1][1];
                //Pixel (1,0)e
                if(i+1 < largo) {
                    pixel = ImagenRes.pixeles[i+1][j];
                    rojo += pixel.getRed()* blur[1][2];
                    verd += pixel.getGreen()* blur[1][2];
                    azul += pixel.getBlue()* blur[1][2];
                }
                //Pixel (0,1)s
                if(j+1 < ancho) {
                    pixel = ImagenRes.pixeles[i][j+1];
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
