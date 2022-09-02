package Sopa;

import Sopa.Busqueda.Buscar;
import Sopa.Busqueda.Palabra;

import java.util.ArrayList;
import java.util.List;

public class SopaConcurrente {
    public static void main(String[] args) {
        List<Thread> hilos = new ArrayList<>();
        CrearSopa c= new CrearSopa();
        c.crearSopa();
        Buscar b = new Buscar(c.getMatriz());
        c.imprimirSopa();
        for (String p:c.getPal()) {
            Thread hilo = new Thread(
                   new HiloSopa(b,p));
            hilos.add(hilo);
        }
        for (Thread t: hilos
             ) {
            t.start();
        }
        for (Thread t: hilos
        ) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }        }
    }
}
