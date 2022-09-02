package Sopa;

import Sopa.Busqueda.Buscar;
import Sopa.Busqueda.Palabra;

public class HiloSopa implements Runnable{
    private Buscar buscar;
    private String palabra;
    public HiloSopa(Buscar buscar,String palabra){
        this.buscar = buscar;
        this.palabra =  palabra;
    }
    @Override
    public void run(){
        Palabra p = this.buscar.buscarEnArreglo(this.palabra);
        System.out.println(p.toString());
    }
}
