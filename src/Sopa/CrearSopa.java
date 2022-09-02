package Sopa;
import Sopa.GenerarSopa.Sopa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrearSopa {
    protected char [][] matriz;

    public List<String> getPal() {
        return pal;
    }

    protected  List<String> pal;

    public char[][] getMatriz() {
        return matriz;
    }

    /**
     * Método que imprime la matriz con su contenido
     */
    public void imprimirSopa() {
        for(int i=0;i<matriz.length;i++) {
            for(int j=0;j<matriz[0].length;j++) {
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void crearSopa(){
        Scanner sc=new Scanner(System.in);
        List<String> palabras = new ArrayList<>();
        Sopa sp = new Sopa();
        sp.llenarSopa();

        for (int i=0;i<5; i++ ){
            System.out.println("Ingresa una palabra que quieras que contenga la sopa de letras");
            palabras.add(sc.nextLine());
        }

        for (String pl: palabras) {
            sp.setPalabra(pl);
            sp.generarPosicionAleatoria();
            if(sp.isS())sp.insertarPalabraHorizontal();
            else sp.insertarPalabraVertical();
        }
        pal=palabras;
        matriz = sp.getMatriz();
        imprimirSopa();

    }

}
