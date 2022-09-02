package mvc.controlador;

import mvc.vista.*;

/**
 * Clase que coordina las ventanas y la logica del proyecto
 */
public class GestorVentana {
	
	private Ventana[] ventanas;
	
	/**
	 * Constructor de la clase
	 */
	public GestorVentana() {
		iniciarVentanas();
	}
	
	/**
	 * Inicia las trs ventanas y la logica del proyecto
	 */
	private void iniciarVentanas() {
		ventanas = new Ventana[1];
		ventanas[0] = new VentanaPrincipal(this);
	}
	
    public void reiniciar() {
		ventanas[0] = new VentanaPrincipal(this);
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
    
    
}
