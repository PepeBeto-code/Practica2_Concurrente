package mvc.modelo.filtros;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public abstract class Filtros implements Runnable {
    
    protected Color[][] resultado;
    protected String extension, nombre;
    protected int ancho, largo;
    
    public Filtros() {
        //ImagenRes.cargarImagen(nombre);
        //this.nombre = nombre;
    }
    
    public void iniciarMatriz() {
        ancho = ImagenRes.pixeles[0].length;
        largo = ImagenRes.pixeles.length;
        resultado = new Color[ImagenRes.pixeles.length][ImagenRes.pixeles[0].length];
    }
    
    @Override
    public void run() {
        concurrente(Integer.parseInt(Thread.currentThread().getName()));
    }
    
    public abstract void concurrente(int factual);
    
    /**
     * Metodo que aplica correctud2 de forma secuencial
     */
    public abstract void secuencial();
    
    public void mostrar(String nombre, boolean esSecuencial) {
        String extra = (esSecuencial)? "secc" : "conc";
        ImagenRes.guardarImagen(resultado, "" + extra + extension);
    }
}
