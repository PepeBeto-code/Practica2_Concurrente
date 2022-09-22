package lectorRecursos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Color;

public final class ImagenRes {
	
    private static String separador = System.getProperty("file.separator");
	private static BufferedImage image, nImage;
    private static int largo, ancho;
    public static Color[][] pixeles;
    private static boolean inicializado = false;
    
    public static boolean inicializado() {
        return inicializado;
    }
    
    public static BufferedImage obtenerImage() {
        return image;
    }
    
    public static BufferedImage obtenerImageNueva() {
        return nImage;
    }
    
	/**
	 * Metodo para iniciar una imagen
     * @param nombre Una cadena que es el nombre de la imagen con su extensión.
     * Estan en la carpeta /res/imagenes/
	 */
	public static void cargarImagen(File fimg) {
        try {
            image = ImageIO.read(fimg);
            largo = image.getHeight();
            ancho = image.getWidth();
            generarMatrizColor();
            inicializado = true;
            System.out.println(pixeles.length + "x" + pixeles[0].length);
        } catch (IOException  e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
	}
    
    public static void aPNG() {
        try {
            BufferedImage image1 = ImageIO.read(new File("res" + separador + "imagenes" + separador + "kind.jpg"));
            File file = new File("res" + separador + "imagenes" + separador + "kind.png");
            ImageIO.write(image1, "png", file);
            image1 = ImageIO.read(new File("res" + separador + "imagenes" + separador + "seyc.jpg"));
            file = new File("res" + separador + "imagenes" + separador + "seyc.png");
            ImageIO.write(image1, "png", file);
            image1 = ImageIO.read(new File("res" + separador + "imagenes" + separador + "rwby.jpg"));
            file = new File("res" + separador + "imagenes" + separador + "rwby.png");
            ImageIO.write(image1, "png", file);
        } catch (IOException  e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para crear un archivo en formato png en la carpeta /res/imagenes
     * dado un nombre
     */
    public static void guardarImagen(Color[][] pixeles, String nombre) {
        nImage = new BufferedImage(pixeles.length, 
            pixeles[0].length, BufferedImage.TYPE_INT_RGB);
        // Set each pixel of the BufferedImage to the color from the Color[][].
        for (int i = 0; i < pixeles.length; i++) {
            for (int j = 0; j < pixeles[0].length; j++) {
                nImage.setRGB(i, j, pixeles[i][j].getRGB());
            }
        }
        try {
            // Save as PNG
            File file = new File("res" + separador + "imagenes" + separador + nombre + ".png");
            ImageIO.write(nImage, "png", file);
 
            // Save as JPEG
            //file = new File("res" + separador + "imagenes" + separador + nombre + ".jpg");
            //ImageIO.write(image, "jpg", file);
        } catch(IOException e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para generar una matriz de Colores de acuerdo a <code>image</code>
     */
    private static void generarMatrizColor() {
        int[] srgb = image.getRGB(0,0,ancho,largo,null,0,ancho);
        pixeles = new Color[ancho][largo];
        for(int i = 0, j = -1, k = -1; i < srgb.length; i++) {
            if(i % ancho == 0) { 
                k++; 
                j=0;
            }
            pixeles[j][k] = new Color(srgb[i]);
            j++;
        }
    }
}
