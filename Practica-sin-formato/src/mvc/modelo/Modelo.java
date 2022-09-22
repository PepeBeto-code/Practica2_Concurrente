package mvc.modelo;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import lectorRecursos.ImagenRes;
import mvc.controlador.GestorVentana;
import mvc.modelo.filtros.*;

/**
 * Clase para hacer que el proyecto funcione
 */
public class Modelo {
   
	private GestorVentana gv;
	private Filtros filtro;	
   
    private static long tiempoInicial;
    private static long tiempoFinal;
    private static long tiempoDelta;
    
	public Modelo(GestorVentana gv) {
		this.gv = gv;
	}
    
    public void iniciarFiltro(char f) {
        switch(f) {
            case 'a': filtro = new FPromedio();
            break;
            case 'b': filtro = new FCorrectud2();
            break;
            case 'c': filtro = new FSharpen();
            break;
            case 'd': filtro = new FCompRGB();
            break;
            case 'e': filtro = new FAltoCont();
            break;
            case 'f': filtro = new FBlur();
            break;
            case 'g': filtro = new FMotionBlur();
            break;
        }
        if(ImagenRes.inicializado()) filtro.iniciarMatriz();
    }
    
    public void cargarImagen(File fimg){
        ImagenRes.cargarImagen(fimg);
        if(filtro != null) filtro.iniciarMatriz();
    }
    
    public void aplicarFiltro() {
        filtroConcurrente();
        
    }
    
    private void filtroConcurrente() {
        tiempoInicial = System.currentTimeMillis();
        int hilos = 20;
        List<Thread> hilosh = new ArrayList<Thread>();
        try{
            for(int i = 0; i < ImagenRes.pixeles.length; i++){
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
        filtro.mostrar("copia",false);
    }
}
