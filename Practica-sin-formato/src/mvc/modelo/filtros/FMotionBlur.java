package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

/**
 * Clase que implementa una forma de aplicar MotionBlur a una imagen
 */
public class FMotionBlur extends Filtros {
    
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
    
    public FMotionBlur() {
        super();
    }
    
    /**
     * Metodo que aplica motionblur de forma concurrente
     */
    @Override
    public void concurrente(int factual) {
        Color pixel;
        for(int j = 0; j < ancho; j++){
            float rojo = 0f;
            float verd = 0f;
            float azul = 0f; 
            //Pixel (-4,-4)
            if(factual-4 > -1 && j-4 > -1) {
                pixel = entrada[factual-4][j-4];
                rojo += pixel.getRed()* blur[0][0];
                verd += pixel.getGreen()* blur[0][0];
                azul += pixel.getBlue()* blur[0][0];
            }
            //Pixel (-3,-3)
            if(factual-3 > -1 && j-3 > -1) {
                pixel = entrada[factual-3][j-3];
                rojo += pixel.getRed()* blur[1][1];
                verd += pixel.getGreen()* blur[1][1];
                azul += pixel.getBlue()* blur[1][1];
            }
            //Pixel (-2,-2)
            if(factual-2 > -1 && j-2 > -1) {
                pixel = entrada[factual-2][j-2];
                rojo += pixel.getRed()* blur[2][2];
                verd += pixel.getGreen()* blur[2][2];
                azul += pixel.getBlue()* blur[2][2];
            }
            //Pixel (-1,-1)
            if(factual-1 > -1 && j-1 > -1) {
                pixel = entrada[factual-1][j-1];
                rojo += pixel.getRed()* blur[3][3];
                verd += pixel.getGreen()* blur[3][3];
                azul += pixel.getBlue()* blur[3][3];
            }
            //Pixel (0,0)
            pixel = entrada[factual][j];
            rojo += pixel.getRed()* blur[4][4];
            verd += pixel.getGreen()* blur[4][4];
            azul += pixel.getBlue()* blur[4][4];
            //Pixel (1,1)
            if(factual+1 < largo && j+1 < ancho) {
                pixel = entrada[factual+1][j+1];
                rojo += pixel.getRed()* blur[5][5];
                verd += pixel.getGreen()* blur[5][5];
                azul += pixel.getBlue()* blur[5][5];
            }
            //Pixel (2,2)
            if(factual+2 < largo && j+2 < ancho) {
                pixel = entrada[factual+2][j+2];
                rojo += pixel.getRed()* blur[6][6];
                verd += pixel.getGreen()* blur[6][6];
                azul += pixel.getBlue()* blur[6][6];
            }
                //Pixel (3,3)
            if(factual+3 < largo && j+3 < ancho) {
                pixel = entrada[factual+3][j+3];
                rojo += pixel.getRed()* blur[7][7];
                verd += pixel.getGreen()* blur[7][7];
                azul += pixel.getBlue()* blur[7][7];
            }
            //Pixel (4,4)
            if(factual+4 < largo && j+4 < ancho) {
                pixel = entrada[factual+4][j+4];
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
            
            resultado[factual][j] = new Color((int)rojo,(int)verd,(int)azul);
        }
    }
    
    /**
     * Metodo que aplica motionblur de forma secuencial
     */
    @Override
    public void secuencial() {
        Color pixel;
        for(int i = 0; i < largo; i++){
            for(int j = 0; j < ancho; j++){
                float rojo = 0f;
                float verd = 0f;
                float azul = 0f; 
                //Pixel (-4,-4)
                if(i-4 > -1 && j-4 > -1) {
                    pixel = entrada[i-4][j-4];
                    rojo += pixel.getRed()* blur[0][0];
                    verd += pixel.getGreen()* blur[0][0];
                    azul += pixel.getBlue()* blur[0][0];
                }
                //Pixel (-3,-3)
                if(i-3 > -1 && j-3 > -1) {
                    pixel = entrada[i-3][j-3];
                    rojo += pixel.getRed()* blur[1][1];
                    verd += pixel.getGreen()* blur[1][1];
                    azul += pixel.getBlue()* blur[1][1];
                }
                //Pixel (-2,-2)
                if(i-2 > -1 && j-2 > -1) {
                    pixel = entrada[i-2][j-2];
                    rojo += pixel.getRed()* blur[2][2];
                    verd += pixel.getGreen()* blur[2][2];
                    azul += pixel.getBlue()* blur[2][2];
                }
                //Pixel (-1,-1)
                if(i-1 > -1 && j-1 > -1) {
                    pixel = entrada[i-1][j-1];
                    rojo += pixel.getRed()* blur[3][3];
                    verd += pixel.getGreen()* blur[3][3];
                    azul += pixel.getBlue()* blur[3][3];
                }
                //Pixel (0,0)
                pixel = entrada[i][j];
                rojo += pixel.getRed()* blur[4][4];
                verd += pixel.getGreen()* blur[4][4];
                azul += pixel.getBlue()* blur[4][4];
                //Pixel (1,1)
                if(i+1 < largo && j+1 < ancho) {
                    pixel = entrada[i+1][j+1];
                    rojo += pixel.getRed()* blur[5][5];
                    verd += pixel.getGreen()* blur[5][5];
                    azul += pixel.getBlue()* blur[5][5];
                }
                //Pixel (2,2)
                if(i+2 < largo && j+2 < ancho) {
                    pixel = entrada[i+2][j+2];
                    rojo += pixel.getRed()* blur[6][6];
                    verd += pixel.getGreen()* blur[6][6];
                    azul += pixel.getBlue()* blur[6][6];
                }
                //Pixel (3,3)
                if(i+3 < largo && j+3 < ancho) {
                    pixel = entrada[i+3][j+3];
                    rojo += pixel.getRed()* blur[7][7];
                    verd += pixel.getGreen()* blur[7][7];
                    azul += pixel.getBlue()* blur[7][7];
                }
                //Pixel (4,4)
                if(i+4 < largo && j+4 < ancho) {
                    pixel = entrada[i+4][j+4];
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

