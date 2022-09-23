package mvc.controlador;

import java.io.File;

import java.awt.image.BufferedImage;

import lectorRecursos.ImagenRes;
import mvc.modelo.Modelo;
import mvc.vista.Ventana;

/**
 * Clase que coordina las ventanas y la logica del proyecto
 */
public class GestorVentana {
	
	private Ventana[] ventanas;
    private Modelo modelo;
    
	/**
	 * Constructor de la clase
	 */
	public GestorVentana() {
		ventanas = new Ventana[1];
		ventanas[0] = new Ventana(this);
        modelo = new Modelo(this);
	}
    
    /**
     * Metodo que recibe un tipo de filtro desde la ventana. Comunica el filtro 
     * al modelo
     * @param f El enum que representa al filtro a usarse
     */
    public void recibirFiltro(TipoFiltro f) {
        modelo.iniciarFiltro(f);
    }
    
    /**
     * Metodo que recibe datos adicionales para el filtro de Compoente RGB
     * @param r Un entero que es cuanto rojo se añade o sustrae
     * @param g Un entero que es cuanto verde se añade o sustrae
     * @param b Un entero que es cuanto azul se añade o sustrae
     */
    public void recibirAdicional(int r, int g, int b) {
        modelo.recibirAdicional(r,g,b);
    }
    
    /**
     * Metodo que recibe un archivo que es una imagen desde la ventana y 
     * pasarsela al modelo
     * @param fimg Un objeto File donde esta la imagen
     */
    public void recibirImagen(File fimg) {
        modelo.cargarImagen(fimg);
    }
    
    /**
     * Metodo que recibe una imagen desde el modelo y pasarsela la ventana
     * @param image Un objeto BufferedImage que sera mostrado en la ventana
     */
    public void recibirImagen(BufferedImage image) {
        ventanas[0].recibirImagen(image);
    }
    
    /**
     * Metodo que avisa al modelo que debe aplicar el filtro con la imagen que
     * tiene de forma secuencial o concurrente
     * @param esSecuencial Un booleano que indica de que forma se aplica el 
     * filtro 
     */
    public void aplicarFiltro(boolean esSecuencial) {
        modelo.aplicarFiltro(esSecuencial);
    }
}
