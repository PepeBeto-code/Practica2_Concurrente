package principal;

import java.util.ArrayList;
import java.util.List;

import mvc.modelo.*;
import mvc.controlador.GestorVentana;
//import lectorRecursos.ImagenRes;

import java.awt.Color;

/**
 * Clase que inicia la ejecucion del proyecto
 */
public class Principal {
    
    public static long tiempoInicial;
    public static long tiempoFinal;
    public static long tiempoDelta;
    public static String imageN = "lal";
    
	/**
	 * Metodo principal
	 */
	public static void main(String[] args) {
        GestorVentana gv = new GestorVentana();
        gv.mostrarVentana(0);
        
        /*iniciarMatriz();
        iniciarPromedio();
        iniciarCorrectud2();
        iniciarBlur();
        iniciarMotionBlur();
        iniciarSharpen();
        iniciarCompRGB();
        iniciarAltoContraste();*/
	}
	
    /*private static void iniciarPromedio() {
        Filtros f = new FPromedio(imageN);
        System.out.println("Promedio");
        iniciarFiltros(f);
    }
    
    private static void iniciarCorrectud2() {
        Filtros f = new FCorrectud2(imageN);
        System.out.println("Correctud2");
        iniciarFiltros(f);
    }
    
    private static void iniciarAltoContraste() {
        System.out.println("Alto Contraste");
        Filtros f = new FAltoCont(imageN);
        iniciarFiltros(f);
    }
    
    private static void iniciarBlur() {
        System.out.println("Blur");
        Filtros f = new FBlur(imageN);
        iniciarFiltros(f);
    }
    
    private static void iniciarMotionBlur() {
        Filtros f = new FMotionBlur(imageN);
        System.out.println("Motion Blur");
        iniciarFiltros(f);
    }
    
    private static void iniciarSharpen() {
        Filtros f = new FSharpen(imageN);
        System.out.println("Shrapen");
        iniciarFiltros(f);
    }
    
    private static void iniciarCompRGB() {
        Filtros f = new FCompRGB(imageN,-1,254,-50);
        System.out.println("Componente RGB");
        iniciarFiltros(f);
    }*/
    
    /*private static void iniciarFiltros(Filtros f) {
        tiempoInicial = System.currentTimeMillis();
        f.secuencial();
        tiempoFinal = System.currentTimeMillis();
        tiempoDelta = tiempoFinal - tiempoInicial;
        f.mostrar("copia",true);
        System.out.println("Aplicacion filtro secuencial tiempo: " + tiempoDelta +"ms");
        
        tiempoInicial = System.currentTimeMillis();
        int hilos = 20;
        List<Thread> hilosh = new ArrayList<Thread>();
        try{
            for(int i = 0; i < ImagenRes.pixeles.length-1; i++){
                Thread t = new Thread(f,""+i);
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
        f.mostrar("copia",false);
        System.out.println("Aplicacion filtro concurrente tiempo: " + tiempoDelta +"ms");
    }*/
    
	/**
	 * Metodo que llama a lo que se requiere para comenzar el proyecto
	 */
	private static void iniciarMatriz() {
		int[][] A = {{1,2},
                     {1,2},
                     {6,5}};
        int[][] B = {{1,2},
                     {1,2}};
        MultMatrices m = new MultMatrices(A,B);
        
        tiempoInicial = System.currentTimeMillis();
        m.secuencial();
        tiempoFinal = System.currentTimeMillis();
        tiempoDelta = tiempoFinal - tiempoInicial;
        m.muestra();
        System.out.println("Multiplicacion matrices secuencial tiempo: " + tiempoDelta +"ms");
        
        tiempoInicial = System.currentTimeMillis();
        int hilos = 5;
        List<Thread> hilosh = new ArrayList<Thread>();
        try {
            for(int i = 0; i < A.length; i++){
                Thread t = new Thread(m,""+i);
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
        m.muestra();
        System.out.println("Multiplicacion matrices concurrente tiempo: " + tiempoDelta +"ms");
    }
}
