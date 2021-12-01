package org.example;

public class GestorPalillos {
    boolean palilloLibre[];
    public GestorPalillos(int numPalillos){
        palilloLibre=new  boolean[numPalillos];
        for (int i=0; i<numPalillos; i++){
            palilloLibre[i]=true;
        } //Fin del for
    } //Fin del constructor
    public synchronized boolean
    intentarCogerPalillos(int pos1, int pos2, boolean zurdo)
    {
        boolean seConsigue=false;

        if(!zurdo){
            if (
                    (palilloLibre[pos1])
                            &&
                            (palilloLibre[pos2]) )
            {
                palilloLibre[pos1]=false;
                palilloLibre[pos2]=false;
                seConsigue=true;
            } //Fin del if
        } else {
            if (
                    (palilloLibre[pos2])
                            &&
                            (palilloLibre[pos1])) {
                palilloLibre[pos2] = false;
                palilloLibre[pos1] = false;
                seConsigue = true;
            }
        }

        return seConsigue;
    }

    public void liberarPalillos(int pos1, int pos2, boolean zurdo){
        if (!zurdo){
            palilloLibre[pos1]=true;
            palilloLibre[pos2]=true;
        } else {
            palilloLibre[pos2]=true;
            palilloLibre[pos1]=true;
        }

    }
}
