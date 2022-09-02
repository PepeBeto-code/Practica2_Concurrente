package mvc.modelo;

import java.awt.Color;

import lectorRecursos.ImagenRes;

public abstract class Filtros implements Runnable {
    
    protected Color[][] resultado;
    protected String extension, nombre;
    protected int ancho, largo;
    
    public Filtros(String nombre) {
        ImagenRes.cargarImagen(nombre);
        this.nombre = nombre;
        this.ancho = ImagenRes.pixeles[0].length;
        this.largo = ImagenRes.pixeles.length;
        resultado = new Color[ImagenRes.pixeles.length][ImagenRes.pixeles[0].length];
    }
    
    @Override
    public void run() {
        concurrente();
    }
    
    public abstract void concurrente();
    
    /**
     * Metodo que aplica correctud2 de forma secuencial
     */
    public abstract void secuencial();
    
    public void mostrar(String nombre) {
        ImagenRes.guardarImagen(resultado, "" + this.nombre + extension);
    }
}
