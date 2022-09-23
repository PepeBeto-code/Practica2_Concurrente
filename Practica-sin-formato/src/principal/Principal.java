package principal;

import java.util.ArrayList;
import java.util.List;

import mvc.modelo.MultMatrices;
import mvc.controlador.GestorVentana;

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
        //gv.mostrarVentana(0);
        
        /*iniciarMatriz();*/
    }
    
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
