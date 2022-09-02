package MultiplicacionMatices;

class HiloMultiplicador implements Runnable
{
    private double[][] m1;
    private double[][] m2;
    private double[][] resultado;
    private int fila;
    private int columna;

    /**
     * Guarda los parámetros que se le pasan
     * @param m1 primer operando
     * @param m2 segundo operando
     * @param resultado matriz donde dejar el resultado
     * @param fila fila que debe calcular
     * @param columna columna que debe calcular
     */
    public HiloMultiplicador (double[][] m1, double[][]m2, double[][]resultado, int fila, int columna)
    {
        this.m1 = m1;
        this.m2 = m2;
        this.resultado = resultado;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Calcula el elemento fila,columna de la matriz resultado
     */
    public void run()
    {
        resultado[fila][columna]=0.0;
        for (int i=0;i<m2.length;i++)
            resultado[fila][columna]+=m1[fila][i]*m2[i][columna];
    }
}