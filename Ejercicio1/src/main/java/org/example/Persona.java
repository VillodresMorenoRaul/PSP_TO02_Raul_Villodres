package org.example;

import java.text.DecimalFormat;

public class Persona extends Cuenta implements Runnable {

    //Parametros
    private String nombreUsuario;
    private Cuenta cuenta;


    //Ponemos el constructor de la persona
    public Persona (Cuenta cuenta, String nombreUsuario){
        super();
        this.nombreUsuario = nombreUsuario;
        this.cuenta = cuenta;
    }

    public Persona (){
        super();
    }

    public String getNombre(){
        return nombreUsuario;
    }

    public void setNombre(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }

    public Cuenta getCuenta(){
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta){
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            int decision = (int) (Math.random() * (3-1+1)+1);
            Double dinero = generarNumeroAleatorio();

            //Ingreso de Dinero
            if(decision == 1){
                System.out.println("El usuario " + nombreUsuario + ", " + cuenta.realizarIngreso(dinero));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            //Retirar dinero
            } else if(decision == 2){
                System.out.println("El usuario " + nombreUsuario + ", " + cuenta.retirarDinero(dinero));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            //Realizar una transferencia de una cuenta a otra
            } else if(decision == 3){
                System.out.println("El usuario " + nombreUsuario + "ha hecho una transferencia desde " + cuenta.realizarTransferencia(cuenta.getNombreCuenta(), dinero));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("El usuario " + nombreUsuario + " ha consultado los datos, " + cuenta.obtenerSaldo());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Double generarNumeroAleatorio(){
        double numeroAleatorio = Math.random() * (100-1+1)+1;
        DecimalFormat formato = new DecimalFormat("#.00");
        String numeroAleatorioString = formato.format(numeroAleatorio).replace(",",".");
        numeroAleatorio = Double.parseDouble(numeroAleatorioString);
        return numeroAleatorio;
    }
}
