package org.example;

public class Main extends Persona implements Runnable{

    //Constantes
    static final String CUENTABANCARIA1 = "CuentaBancaria32";
    static final String CUENTABANCARIA2 = "CuentaBancaria50";
    static final Double VALORINICIALCUENTA1 = 407.32;
    static final Double VALORINICIALCUENTA2 = 370.94;

    //Creamos dos cuentas bancarias, que tomaran los valores de las constantes
    static Cuenta cuenta1 = new Cuenta(CUENTABANCARIA1, VALORINICIALCUENTA1);
    static Cuenta cuenta2 = new Cuenta(CUENTABANCARIA2, VALORINICIALCUENTA2);

    public Main(Cuenta cuenta, String nombreUsuario){
        super(cuenta, nombreUsuario);
    }

    public Main(){
        super();
    }

    public void run(){
        System.out.println("Main iniciado");
    }

    public static void main(String[] args){
        //Declaramos los objetos persona
        Persona persona1 = new Persona(cuenta1, "Rodrigo Wendingo");
        Persona persona2 = new Persona(cuenta2, "Nobuo Uematsu");
        Persona persona3 = new Persona(cuenta2, "Fernando Alonso");
        Persona persona4 = new Persona(cuenta1, "Juana de Arco");

        //Pasamos los objetos de tipo persona a drones
        Thread hilo1 = new Thread(persona1);
        Thread hilo2 = new Thread(persona2);
        Thread hilo3 = new Thread(persona3);
        Thread hilo4 = new Thread(persona4);

        try{
            hilo1.start();
            hilo2.start();
            hilo3.start();
            hilo4.start();

        } catch(Exception e){
            System.err.println("Uno o varios hilos no pudieron iniciarse");
        }

        System.out.println("Fin del programa");
    }
}
