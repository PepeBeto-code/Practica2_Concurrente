package Sopa.Busqueda;

import Sopa.Busqueda.Coordenada;
import Sopa.Busqueda.Palabra;
import Sopa.Busqueda.Resultado;

import java.util.ArrayList;
import java.util.List;

public class Buscar {
    protected char [][] sopa;

      public Buscar(char [][] sopa){
        this.sopa=sopa;
      }

    public Resultado buscarArriba(int x, int y, int letra, String palabra, List<Coordenada> coordenadas){
        if(0 <= x){
           return buscar(x,y,letra,palabra, buscarArriba(--x,y,++letra,palabra,coordenadas),coordenadas);
        }
        return new Resultado(false,new Palabra(palabra,coordenadas));
    }

    public Resultado buscarAbajo(int x, int y, int letra, String palabra,List<Coordenada> coordenadas){
        if(x <= sopa[0].length-1){
            return buscar(x,y,letra,palabra, buscarAbajo(++x,y,++letra,palabra,coordenadas),coordenadas);
        }
        return new Resultado(false,new Palabra(palabra,coordenadas));
    }

    public Resultado buscarIzq(int x, int y, int letra, String palabra,List<Coordenada> coordenadas){
        if(0 <= y){
            return buscar(x,y,letra,palabra, buscarIzq(x,--y,++letra,palabra,coordenadas),coordenadas);
        }
        return new Resultado(false,new Palabra(palabra,coordenadas));
    }

    public Resultado buscarDer(int x, int y, int letra, String palabra, List<Coordenada> coordenadas){
        if(y <= sopa[0].length-1 ){
            return buscar(x,y,letra,palabra, buscarDer(x,++y,++letra,palabra,coordenadas),coordenadas);
        }
        return new Resultado(false,new Palabra(palabra,coordenadas));
    }

    public Resultado buscar(int x, int y, int letra, String palabra, Resultado method, List<Coordenada> coordenadas){
        if(letra < palabra.length()){
            if (sopa[x][y] == palabra.charAt(letra)) {
                if (sopa[x][y] == palabra.charAt(palabra.length() - 1)) {
                    coordenadas.add(new Coordenada(x, y));
                    return new Resultado(true, new Palabra(palabra, coordenadas));
                } else {
                    coordenadas.add(new Coordenada(x, y));
                    return method;
                }
            }
        }
        return new Resultado(false,new Palabra(palabra,coordenadas));
    }

    public Resultado buscarPalabra(int x, int y, int letra, String palabra){
        List<Coordenada> cor = new ArrayList<>();
        Resultado res = buscarArriba(x,y,letra,palabra,cor);

        if (!res.getResultado()){
            cor.clear();
             res = buscarAbajo(x,y,letra,palabra,cor);
            if (!res.getResultado()){
                cor.clear();
                res = buscarIzq(x,y,letra,palabra,cor);
                if (!res.getResultado()){
                    cor.clear();
                    res = buscarDer(x,y,letra,palabra,cor);                }
            }
        }
        return res;
    }

    public Palabra buscarEnArreglo(String palabra){
        Palabra p = null;
        for (int i=0;i<sopa.length; i++)
        {
            for (int j=0;j<sopa[0].length;j++) {
                Resultado res = buscarPalabra(i, j, 0, palabra);
                if (res.getResultado()){
                    p = res.getPalabra();
                }
            }
        }
        return p;
    }

    /**public static void main(String[] args) {
        List<Coordenada> cor = new ArrayList<>();
        Resultado r = buscarPalabra(1,0,0,"ehk");
        if (r.getResultado()){
            System.out.println("Se armo");
            System.out.println(r.getPalabra().toString());
        }else{
            System.out.println("No se armo");
        }
        Palabra p = buscarEnArreglo("ojc");
        if (p==null)
            System.out.println("No se encontro la palabra");
            System.out.println(p.toString());
    }*/
}
