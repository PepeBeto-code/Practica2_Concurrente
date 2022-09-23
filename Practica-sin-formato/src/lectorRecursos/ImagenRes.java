package lectorRecursos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.StringTokenizer;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Clase que se encarga de leer, guardar y cambiar el formato de matrices de 
 * Color y BufferedImage cosas relacionadas con imagenes
 */
public final class ImagenRes {
	
    private static final String separador = System.getProperty("file.separator");
	private static BufferedImage imagenCargada;
    private static int largo, ancho;
    private static boolean inicializado = false;
    
    /**
     * Metodo que informa si ya se ha cargado una imagen para filtrar
     * @return Un valor booleano que indica si se ha cargado al menos una imagen
     * en lo que va de la ejecucion
     */
    public static boolean inicializado() {
        return inicializado;
    }
    
    /**
     * Metodo para obtener el BufferedImage de la imagen que se ha cargado mas 
     * recientemente
     * @return El BufferedImage de la imagen cargada
     */
    public static BufferedImage obtenerBufferedImage() {
        return imagenCargada.getSubimage(0, 0, ancho, largo);
    }
    
	/**
	 * Metodo para iniciar una imagen dado un File
     * @param fimg El archivo de la imagen
	 */
	public static void cargarImagen(File fimg) {
        try {
            imagenCargada = ImageIO.read(fimg);
            largo = imagenCargada.getHeight();
            ancho = imagenCargada.getWidth();
            inicializado = true;
            System.out.println(largo + "x" + ancho);
        } catch (IOException  e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
	}
    
    /**
     * Metodo para crear un archivo en formato png en una ruta dada por un 
     * objeto File
     * @param fimg El File con la direccion y nombre de la nueva imagen
     * @param image El BufferedImage de la imagen a guardar 
     */
    public static void guardarImagen(File fimg, BufferedImage image) {
        try {
            // Save as PNG
            File file = new File(fimg + ".png");
            ImageIO.write(image, "png", file);
        } catch(IOException e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para generar una matriz de Color a partir de un BufferedImage
     * @param image Un BufferdImage que se va a poner en formato de una matriz
     * @return La imagen en formato de Color[][]
     */
    public static Color[][] generarMatrizColor(BufferedImage image) {
        int[] srgb = image.getRGB(0,0,ancho,largo,null,0,ancho);
        Color[][] pixeles = new Color[ancho][largo];
        for(int i = 0, j = -1, k = -1; i < srgb.length; i++) {
            if(i % ancho == 0) { 
                k++; 
                j=0;
            }
            pixeles[j][k] = new Color(srgb[i]);
            j++;
        }
        return pixeles;
    }
    
    /**
     * Metodo para generar un BufferedImage a partir de una matriz de Color
     * @param pixeles Una matrz de Color que se va a poner en formato de un 
     * BufferedImage
     * @return La imagen en formato de BufferedImage
     */
    public static BufferedImage generarBufferedImage(Color[][] pixeles) {
        BufferedImage nImage = new BufferedImage(pixeles.length, 
            pixeles[0].length, BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int i = 0; i < pixeles.length; i++) {
            for (int j = 0; j < pixeles[0].length; j++) {
                nImage.setRGB(i, j, pixeles[i][j].getRGB());
            }
        }
        return nImage;
    }
}
