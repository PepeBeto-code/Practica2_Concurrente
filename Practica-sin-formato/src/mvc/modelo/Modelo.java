package mvc.modelo;

import java.io.File;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import lectorRecursos.ImagenRes;
import mvc.controlador.GestorVentana;
import mvc.controlador.TipoFiltro;
import mvc.modelo.filtros.*;

/**
 * Clase que se encarga de aplicar el filtro correcto a las imagenes
 */
public class Modelo {
   
    private static Color[][] pixeles;
   
	private GestorVentana gv;
	private Filtros filtro;	
   
    private static long tiempoInicial;
    private static long tiempoFinal;
    private static long tiempoDelta;
    
    /**
	 * Constructor de la clase 
	 * @param gv El coordinador entre las ventanas y la logica 
     */
	public Modelo(GestorVentana gv) {
		this.gv = gv;
	}
    
    /**
     * Metodo que recibe el tipo de filtro a usar con una imagen
     * @param f Un indicador del tipo de filtro
     */
    public void iniciarFiltro(TipoFiltro f) {
        switch(f) {
            case PROMEDIO: filtro = new FPromedio();
            break;
            case CORRECTUD2: filtro = new FCorrectud2();
            break;
            case SHARPEN: filtro = new FSharpen();
            break;
            case COMPONENTERGB: filtro = new FCompRGB();
            break;
            case ALTOCONTRASTE: filtro = new FAltoCont();
            break;
            case BLUR: filtro = new FBlur();
            break;
            case MOTIONBLUR: filtro = new FMotionBlur();
            break;
        }
        if(ImagenRes.inicializado()) filtro.iniciarMatriz(pixeles);
    }
    
    /**
     * Metodo que recibe un archivo que es la imagen a aplicar un filtro
     * @param fimg La direccion de imagen
     */
    public void cargarImagen(File fimg){
        ImagenRes.cargarImagen(fimg);
        pixeles = ImagenRes.generarMatrizColor(ImagenRes.obtenerBufferedImage());
        if(filtro != null) filtro.iniciarMatriz(pixeles);
    }
    
    /**
     * Metodo que recibe los datos adicionales para el filtro Componente RGB
     * @param r Un entero que es cuanto rojo se añade o sustrae
     * @param g Un entero que es cuanto verde se añade o sustrae
     * @param b Un entero que es cuanto azul se añade o sustrae
     */
    public void recibirAdicional(int r, int g, int b) {
        ((FCompRGB)filtro).asignarRGB(r,g,b);
    }
    
    /**
     * Metodo que al ser usado indica que se debe aplicar el filtro que se tiene
     * con la imagen guardada usando metodos secuenciales o concurrentes
     * @param esSecuencial Indica si se va a usar el metodo secuencial o no para 
     * aplicar el filtro
     */
    public void aplicarFiltro(boolean esSecuencial) {
        if(esSecuencial) {
            filtroSecuencial();
        } else {
            filtroConcurrente();
        }
    }
    
    private void filtroSecuencial() {
        tiempoInicial = System.currentTimeMillis();
        filtro.secuencial();
        tiempoFinal = System.currentTimeMillis();
        tiempoDelta = tiempoFinal - tiempoInicial;
        System.out.println("Aplicacion filtro secuencial tiempo: " + tiempoDelta +"ms");
        gv.recibirImagen(ImagenRes.generarBufferedImage(filtro.obtenerResultado()));
    }
    
    private void filtroConcurrente() {
        tiempoInicial = System.currentTimeMillis();
        int hilos = 20;
        List<Thread> hilosh = new ArrayList<Thread>();
        try{
            for(int i = 0; i < pixeles.length; i++){
                Thread t = new Thread(filtro,""+i);
                hilosh.add(t);
                t.start();
                if(hilosh.size() == hilos){
                    for(Thread threads: hilosh){
                        threads.join();
                    }
                    hilosh.clear();
                }
            }
            for(Thread threads: hilosh) {
                threads.join();
            }
        } catch(InterruptedException e) {}
        tiempoFinal = System.currentTimeMillis();
        tiempoDelta = tiempoFinal - tiempoInicial;
        System.out.println("Aplicacion filtro concurrente tiempo: " + tiempoDelta +"ms");
        gv.recibirImagen(ImagenRes.generarBufferedImage(filtro.obtenerResultado()));
    }
}
