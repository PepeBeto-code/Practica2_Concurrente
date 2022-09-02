package MultiplicacionMatices;

import java.util.LinkedList;

/**
 * Ejemplo de multipliación de matrices usando hilos.
 * @author chuidiang
 */
public class MultiplicaMatrices
{

    public static void main(String[] args)
    {
        // Dos matrices para multiplicar
        double [][] m1 = new double[][] {{-2,5,},{0,1}};
        double [][] m2 = new double[][] {{1,-1},{2,3}};

        // Se multiplican
        double [][] resultado = new MultiplicaMatrices().multiplicacionConHilos(m1, m2);


        // Se saca por pantalla el resultado.
        for (int i=0;i<resultado.length; i++)
        {
            for (int j=0;j<resultado[0].length;j++)
                System.out.print(resultado[i][j]+" ");
            System.out.println(" ");
        }
    }

    /**
     * Realiza la multiplicación de las dos matrices y devuelve el resultado
     * @param m1 primer operando
     * @param m2 segundo operando
     * @return resultado de multiplicar m1xm2
     */
    public double[][] multiplica (double [][] m1, double [][] m2, int method)
    {
        // condiciones que deben cumplirse y que se suponen ciertas
        // con los parámetros de entrada
        assert m1!=null;
        assert m2!=null;
        assert m1.length > 0;
        assert m1[0].length > 0;
        assert m2.length > 0;
        assert m2[0].length > 0;
        assert m1.length==m2[0].length;

        int filas = m1.length;
        int columnas = m2[0].length;
        double [][] resultado = new double[filas][columnas];
       if(method == 1){
           return multConcurrente(m1,m2,filas,columnas,resultado);
       }
       return multSecuencial(m1,m2,filas,columnas,resultado);

    }

    public double[][] multiplicacionConHilos(double [][] m1, double [][] m2){
        return multiplica(m1,m2,1);
    }

    public double[][] multiplicacionSinHilos(double [][] m1, double [][] m2){
        return multiplica(m1,m2,2);
    }

    public double[][] multConcurrente(double [][] m1, double [][] m2,int filas,int columnas,double [][] resultado){
        LinkedList<Thread> hilos = new LinkedList<>();

        for (int fila=0; fila<filas; fila++)
            for (int columna=0; columna<columnas; columna++)
            {
                Thread hilo = new Thread(
                        new HiloMultiplicador(m1,m2,resultado,fila,columna));
                hilos.add(hilo);
                hilo.start();
            }

        for (Thread hilo: hilos)
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return resultado;
    }

    public double[][] multSecuencial(double [][] m1, double [][] m2,int filas,int columnas,double [][] resultado){
        for (int fila=0; fila<filas; fila++)
            for (int columna=0; columna<columnas; columna++)
            {
                resultado[fila][columna]=0.0;
                for (int i=0;i<m2.length;i++)
                    resultado[fila][columna]+=m1[fila][i]*m2[i][columna];
            }
       return resultado;
    }


}