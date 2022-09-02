package mvc.modelo;

import java.awt.Color;
import java.util.concurrent.Semaphore;

import lectorRecursos.ImagenRes;

public class FMotionBlur extends Filtros {
    
    private Semaphore sem,sem2;
    private int fila = 0;
    private float blur[][] ={
            {1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f},
            {0f, 1f, 0f, 0f, 0f, 0f, 0f, 0f, 0f},
            {0f, 0f, 1f, 0f, 0f, 0f, 0f, 0f, 0f},
            {0f, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 0f},
            {0f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 0f},//centro
            {0f, 0f, 0f, 0f, 0f, 1f, 0f, 0f, 0f},
            {0f, 0f, 0f, 0f, 0f, 0f, 1f, 0f, 0f},
            {0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f, 0f},
            {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f}};
    
    public FMotionBlur(String nombre) {
        super(nombre);
        extension = "_motionblur";
        sem = new Semaphore(1);
        sem2 = new Semaphore(1);
    }
    
    @Override
    public void concurrente() {
        int factual = 0;
        //try{
           // sem.acquire();
            factual = fila;
            fila++;
            //sem.release();
          //  if(factual >= ImagenRes.pixeles.length) return;
        //} catch(InterruptedException e) {}
        
        Color pixel;
        for(int j = 0; j < ImagenRes.pixeles[0].length; j++){
            float rojo = 0f;
            float verd = 0f;
            float azul = 0f; 
            //Pixel (-4,-4)
            if(factual-4 > -1 && j-4 > -1) {
                pixel = ImagenRes.pixeles[factual-4][j-4];
                rojo += pixel.getRed()* blur[0][0];
                verd += pixel.getGreen()* blur[0][0];
                azul += pixel.getBlue()* blur[0][0];
            }
            //Pixel (-3,-3)
            if(factual-3 > -1 && j-3 > -1) {
                pixel = ImagenRes.pixeles[factual-3][j-3];
                rojo += pixel.getRed()* blur[1][1];
                verd += pixel.getGreen()* blur[1][1];
                azul += pixel.getBlue()* blur[1][1];
            }
            //Pixel (-2,-2)
            if(factual-2 > -1 && j-2 > -1) {
                pixel = ImagenRes.pixeles[factual-2][j-2];
                rojo += pixel.getRed()* blur[2][2];
                verd += pixel.getGreen()* blur[2][2];
                azul += pixel.getBlue()* blur[2][2];
            }
            //Pixel (-1,-1)
            if(factual-1 > -1 && j-1 > -1) {
                pixel = ImagenRes.pixeles[factual-1][j-1];
                rojo += pixel.getRed()* blur[3][3];
                verd += pixel.getGreen()* blur[3][3];
                azul += pixel.getBlue()* blur[3][3];
            }
            //Pixel (0,0)
            pixel = ImagenRes.pixeles[factual][j];
            rojo += pixel.getRed()* blur[4][4];
            verd += pixel.getGreen()* blur[4][4];
            azul += pixel.getBlue()* blur[4][4];
            //Pixel (1,1)
            if(factual+1 < ImagenRes.pixeles.length && j+1 < ImagenRes.pixeles[0].length) {
                pixel = ImagenRes.pixeles[factual+1][j+1];
                rojo += pixel.getRed()* blur[5][5];
                verd += pixel.getGreen()* blur[5][5];
                azul += pixel.getBlue()* blur[5][5];
            }
            //Pixel (2,2)
            if(factual+2 < ImagenRes.pixeles.length && j+2 < ImagenRes.pixeles[0].length) {
                pixel = ImagenRes.pixeles[factual+2][j+2];
                rojo += pixel.getRed()* blur[6][6];
                verd += pixel.getGreen()* blur[6][6];
                azul += pixel.getBlue()* blur[6][6];
            }
                //Pixel (3,3)
            if(factual+3 < ImagenRes.pixeles.length && j+3 < ImagenRes.pixeles[0].length) {
                pixel = ImagenRes.pixeles[factual+3][j+3];
                rojo += pixel.getRed()* blur[7][7];
                verd += pixel.getGreen()* blur[7][7];
                azul += pixel.getBlue()* blur[7][7];
            }
            //Pixel (4,4)
            if(factual+4 < ImagenRes.pixeles.length && j+4 < ImagenRes.pixeles[0].length) {
                pixel = ImagenRes.pixeles[factual+4][j+4];
                rojo += pixel.getRed()* blur[8][8];
                verd += pixel.getGreen()* blur[8][8];
                azul += pixel.getBlue()* blur[8][8];
            }
            rojo /= 9f;
            verd /= 9f;
            azul /= 9f;
        
            rojo = (rojo > 255)? 255:(rojo < 0)? 0:rojo;
            verd = (verd > 255)? 255:(verd < 0)? 0:verd;
            azul = (azul > 255)? 255:(azul < 0)? 0:azul;
            
            //try{
            //    sem2.acquire();
                resultado[factual][j] = new Color((int)rojo,(int)verd,(int)azul);
            //    sem2.release();
            //} catch(InterruptedException e) {}
        }
    }
    
    /**
     * Metodo que aplica altocontraste de forma secuencial
     */
    public void secuencial() {
        Color pixel;
        for(int i = 0; i < ImagenRes.pixeles.length; i++){
            for(int j = 0; j < ImagenRes.pixeles[0].length; j++){
                float rojo = 0f;
                float verd = 0f;
                float azul = 0f; 
                //Pixel (-4,-4)
                if(i-4 > -1 && j-4 > -1) {
                    pixel = ImagenRes.pixeles[i-4][j-4];
                    rojo += pixel.getRed()* blur[0][0];
                    verd += pixel.getGreen()* blur[0][0];
                    azul += pixel.getBlue()* blur[0][0];
                }
                //Pixel (-3,-3)
                if(i-3 > -1 && j-3 > -1) {
                    pixel = ImagenRes.pixeles[i-3][j-3];
                    rojo += pixel.getRed()* blur[1][1];
                    verd += pixel.getGreen()* blur[1][1];
                    azul += pixel.getBlue()* blur[1][1];
                }
                //Pixel (-2,-2)
                if(i-2 > -1 && j-2 > -1) {
                    pixel = ImagenRes.pixeles[i-2][j-2];
                    rojo += pixel.getRed()* blur[2][2];
                    verd += pixel.getGreen()* blur[2][2];
                    azul += pixel.getBlue()* blur[2][2];
                }
                //Pixel (-1,-1)
                if(i-1 > -1 && j-1 > -1) {
                    pixel = ImagenRes.pixeles[i-1][j-1];
                    rojo += pixel.getRed()* blur[3][3];
                    verd += pixel.getGreen()* blur[3][3];
                    azul += pixel.getBlue()* blur[3][3];
                }
                //Pixel (0,0)
                pixel = ImagenRes.pixeles[i][j];
                rojo += pixel.getRed()* blur[4][4];
                verd += pixel.getGreen()* blur[4][4];
                azul += pixel.getBlue()* blur[4][4];
                //Pixel (1,1)
                if(i+1 < ImagenRes.pixeles.length && j+1 < ImagenRes.pixeles[0].length) {
                    pixel = ImagenRes.pixeles[i+1][j+1];
                    rojo += pixel.getRed()* blur[5][5];
                    verd += pixel.getGreen()* blur[5][5];
                    azul += pixel.getBlue()* blur[5][5];
                }
                //Pixel (2,2)
                if(i+2 < ImagenRes.pixeles.length && j+2 < ImagenRes.pixeles[0].length) {
                    pixel = ImagenRes.pixeles[i+2][j+2];
                    rojo += pixel.getRed()* blur[6][6];
                    verd += pixel.getGreen()* blur[6][6];
                    azul += pixel.getBlue()* blur[6][6];
                }
                //Pixel (3,3)
                if(i+3 < ImagenRes.pixeles.length && j+3 < ImagenRes.pixeles[0].length) {
                    pixel = ImagenRes.pixeles[i+3][j+3];
                    rojo += pixel.getRed()* blur[7][7];
                    verd += pixel.getGreen()* blur[7][7];
                    azul += pixel.getBlue()* blur[7][7];
                }
                //Pixel (4,4)
                if(i+4 < ImagenRes.pixeles.length && j+4 < ImagenRes.pixeles[0].length) {
                    pixel = ImagenRes.pixeles[i+4][j+4];
                    rojo += pixel.getRed()* blur[8][8];
                    verd += pixel.getGreen()* blur[8][8];
                    azul += pixel.getBlue()* blur[8][8];
                }
                rojo /= 9f;
                verd /= 9f;
                azul /= 9f;
            
                rojo = (rojo > 255)? 255:(rojo < 0)? 0:rojo;
                verd = (verd > 255)? 255:(verd < 0)? 0:verd;
                azul = (azul > 255)? 255:(azul < 0)? 0:azul;

                resultado[i][j] = new Color((int)rojo,(int)verd,(int)azul);
            }
        }
    }
}

