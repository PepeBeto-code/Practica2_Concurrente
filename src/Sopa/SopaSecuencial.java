package Sopa;

import Sopa.Busqueda.Buscar;
import Sopa.Busqueda.Palabra;

public class SopaSecuencial {
    public static void main(String[] args) {
        CrearSopa c= new CrearSopa();
        c.crearSopa();
        Buscar b = new Buscar(c.getMatriz());
        c.imprimirSopa();
        for (String p:c.getPal()) {
           Palabra palabra= b.buscarEnArreglo(p);
           System.out.println(palabra.toString());
        }
    }
}
