package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Productor implements Runnable {

    @Override
    public void run() {

        //Variables
        String nombreFichero;
        File archivo;
        RandomAccessFile raf = null;
        int i = 1;
        String numerosString = "";

        try {
            nombreFichero = "buffer.txt";
            archivo = new File(nombreFichero);

            while (i <= 20) {//escribiremos 10 datos

                Main.sem.acquire();
                raf = new RandomAccessFile(archivo, "rwd"); //Abrimos un nuevo random access file

                //***************
                //Sección crítica

                System.out.println("Productor: Entra en la sección");
                if (raf.length() == 0) {

                    //Si la repetición es la número 20, escribe un 0, si es una repetición anterior escribe el arrayList generado
                    if (i == 20) {
                        numerosString = "0";
                        raf.writeUTF(numerosString);
                    } else {
                        int cantidadNumeros = (int) (Math.floor(Math.random() * (5 - 1 + 1)) + 1); //Genera un número aleatorio del 1 al 5
                        ArrayList<Integer> numerosGenerados = generarNumerosAleatorios(cantidadNumeros); //Rellena un arrayList con nuestro método para generar Numeros Aleatorios, con el número de valores generados aleatoriamente antes
                        numerosString = numerosGenerados.toString(); //Pasamos el ArrayList a String
                        raf.writeUTF(numerosString);
                    }
                    System.out.println("Productor: Se han generado los números " + numerosString + " Es el dato nº " + i);
                    i++;

                } else {
                    System.out.println("Productor: no puede escribir");
                    System.out.println("Productor: Sale en la sección");
                }

                Main.sem.release();
                Thread.sleep(500);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (raf != null) raf.close();
            } catch (Exception e2) {
                System.err.println("Productor. Error al cerrar el fichero.");
                System.err.println(e2);
                System.exit(1);  //Si hay error, finalizamos
            }
        }
    }

    //Método para generar numeros aleatorios
    public static ArrayList<Integer> generarNumerosAleatorios(int cantidadNumeros){
        ArrayList<Integer> arrayListNumeros = new ArrayList<Integer>();
        for (int i = 0; i < cantidadNumeros; i ++){
            arrayListNumeros.add((int)(Math.floor(Math.random() * (20 - 1 + 1)) + 1));
        }
        return arrayListNumeros;
    }
}
