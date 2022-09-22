package mvc.controlador;

import java.io.File;

import lectorRecursos.ImagenRes;
import mvc.vista.Ventana;
import mvc.modelo.Modelo;

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
	
    public void reiniciar() {
		ventanas[0] = new Ventana(this);
	}
    
	/**
	 * Cambia la ventana dependiendo del entero ingresado.
	 * @param i 0 para <code>VentanaPrincipal</code>, 1 para 
	 * <code>VentanaJuego</code>, 2 para <code>VentanaPausa</code>,  cualquier 
	 * otro numero no termina la ejecucuion pero oculta todas las ventanas
	 */
	public void mostrarVentana(int i) {
		int contador = 0;
		for(Ventana v: ventanas){
			if(contador++ != i)v.setVisible(false);
		}
		ventanas[i].setVisible(true);
	}
    
    public void recibirFiltro(char f) {
        modelo.iniciarFiltro(f);
    }
    
    public void recibirImagen(File fimg) {
        modelo.cargarImagen(fimg);
    }
    
    public void recibirImagen() {
        ventanas[0].cargarImagen();
    }
    
    public void aplicarFiltro() {
        modelo.aplicarFiltro();
        
    }
}
