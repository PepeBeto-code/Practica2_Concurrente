package Sopa.Busqueda;

public class Resultado {
    private boolean resultado;
    private Palabra palabra;

    public Resultado(boolean resultado,Palabra palabra){
        this.resultado = resultado;
        this.palabra = palabra;
    }

    public boolean getResultado() {
        return resultado;
    }

    public Palabra getPalabra() {
        return palabra;
    }
}
