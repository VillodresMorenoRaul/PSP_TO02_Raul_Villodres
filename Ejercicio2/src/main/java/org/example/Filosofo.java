package org.example;

import java.util.Random;

public class Filosofo implements Runnable{
    GestorPalillos gestorPalillos;
    int posPalilloIzq,posPalilloDer;
    Boolean zurdo = false;
    int Prioridad = 10;
    boolean palillosCogidos;

    public Filosofo(GestorPalillos g, int pIzq, int pDer){
        this.gestorPalillos=g;
        this.posPalilloDer=pDer;
        this.posPalilloIzq=pIzq;
    }
    public void run() {
        while (true){
            if(Thread.currentThread().getName().equals("Thread-0")){
                zurdo = true;
            } else {
                zurdo = false;
            }
            palillosCogidos=
                    this.gestorPalillos.intentarCogerPalillos(
                            posPalilloIzq, posPalilloDer, zurdo );
            if (palillosCogidos){
                comer();
                this.gestorPalillos.liberarPalillos(
                        posPalilloIzq,
                        posPalilloDer, zurdo);
                dormir();
                Prioridad = 10;
                Thread.currentThread().setPriority(Prioridad);
            } else {
                if(Prioridad > 1) {
                    Prioridad--;
                    Thread.currentThread().setPriority(Prioridad);
                }
            }
        } //Fin del while true
    } //Fin del run()

    private void comer() {
        System.out.println("Filosofo "+
                Thread.currentThread().getName()+
                " comiendo");
        esperarTiempoAzar();
    }
    private void esperarTiempoAzar() {
        Random generador=new Random();
        int msAzar=generador.nextInt(3);
        try {
            Thread.sleep(msAzar * 300);
        } catch (InterruptedException ex) {
            System.out.println("Fallo la espera");
        }
    }
    private void dormir(){
        System.out.println("Filosofo "+
                Thread.currentThread().getName()+
                " durmiendo (zzzzzz)");
        esperarTiempoAzar();
    }
}
