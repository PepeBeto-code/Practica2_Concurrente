package Sopa.Busqueda;

import java.util.List;

public class Palabra {
    private String palabra;
    private List<Coordenada> coordenadas;

    public Palabra(String palabra,List<Coordenada> coordenadas){
        this.palabra = palabra;
        this.coordenadas = coordenadas;
    }

    public String imprimirCoor(){
        String cor="";
        for (Coordenada t: coordenadas) {
            cor+=t.toString()+", ";
        }
        return cor;
    }

    public String toString(){
        return "Palabra: "+this.palabra+" Coordenadas: "+ imprimirCoor();
    }
}
