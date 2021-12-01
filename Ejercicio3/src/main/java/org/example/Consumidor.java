package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Consumidor implements Runnable {

    @Override
    public void run() {
        //Variables
        String nombreFichero;
        File archivo;
        RandomAccessFile raf = null;
        int cuenta = 1;
        String valor;
        boolean continua = true;
        nombreFichero = "buffer.txt";

        //Preparamos el acceso al fichero
        try {
        archivo = new File(nombreFichero);
        while (cuenta <= 20 && continua) {//leeremos 20 datos o hasta que se lea un 0

                Main.sem.acquire();
                raf = new RandomAccessFile(archivo, "rwd"); //Creamos un nuevo random Access File con nuestro archivo

                //***************
                //Sección crítica
                System.out.println("Consumidor: Entra en la sección");

                //En caso de que el random access File no esté vacío
                if (raf.length() > 0) {
                    valor = raf.readUTF();
                    if(valor.equals("0")){
                        continua = false;
                    }

                    //Y escribimos los datos, adaptandolo al formato de un arrayList y añadiendo un nuevo elemento que hace la media usando la suma anterior y dividiendola entre la longitud del array
                    System.out.println("Consumidor: dato leído " + valor);
                    raf.setLength(0);
                    cuenta ++;
                }
                //Fin sección crítica
                //*******************

                Main.sem.release();
                Thread.sleep(1000);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (Exception e2) {
                System.err.println("Consumidor: Error al cerrar el fichero.");
                System.err.println(e2);
                System.exit(1);  //Si hay error, finalizamos
            }
        }
    }
}