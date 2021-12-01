package org.example;

import java.text.DecimalFormat;

import static org.example.Main.cuenta1;
import static org.example.Main.cuenta2;

public class Cuenta {
    private Double saldo;
    private String nombreCuenta;

    //CONSTRUCTOR
    public Cuenta(String nombreCuenta, Double saldo) {
        this.saldo = saldo;
        this.nombreCuenta = nombreCuenta;
    }

    public Cuenta(){}

    //Getters
    public String getNombreCuenta(){
        return nombreCuenta;
    }

    public Double getSaldo(){
        return saldo;
    }

    //Setters
    public void setNombreCuenta(String nombreCuenta){
        this.nombreCuenta = nombreCuenta;
    }

    public void setSaldo(Double saldo){
        this.saldo = saldo;
    }

    //MÉTODOS

    //Devolver el saldo actual
    public synchronized String obtenerSaldo(){
        return String.valueOf(convertir(getSaldo()));
    }

    //Realizar un ingreso
    public synchronized String realizarIngreso(Double dinero){
        this.saldo = this.saldo + dinero;
        return "El dinero ingresado es de " + dinero + "€";
    }

    //Retirar dinero
    public synchronized String retirarDinero(Double dinero){
        this.saldo = this.saldo - dinero;
        if(this.saldo <= 0){
            this.saldo = this.saldo + dinero;
            return "No se pudo retirar dinero porque se retiraría más del que hay, la retirada de dinero fue cancelada";
        } else {
            return "Se ha realizado la retirada de dinero de forma exitosa, la cantidad retirada es de " + dinero + "€";
        }
    }

    //Transferir dinero de una cuenta a otra
    public synchronized String realizarTransferencia(String nombreCuenta, Double dinero) {

        //Comprobamos si el valor de la cuenta es el de la cuenta1, si es el caso, transferimos de la cuenta 1 a la 2, en caso contrario de la 2 a la 1
        if (cuenta1.getNombreCuenta() == nombreCuenta) {
            cuenta1.setSaldo(cuenta1.getSaldo() - dinero);
            cuenta2.setSaldo(cuenta2.getSaldo() + dinero);

            if (cuenta1.getSaldo() < 0) {
                cuenta1.setSaldo(cuenta1.getSaldo() + dinero);
                cuenta1.setSaldo(cuenta2.getSaldo() - dinero);
                return "Pero No se pudo realizar la transferencia, la cuenta se quedó en números negativos";
            } else {
                return "La transferencia se realizó correctamente";
            }
        } else {
            cuenta2.setSaldo(cuenta2.getSaldo() - dinero);
            cuenta2.setSaldo(cuenta1.getSaldo() + dinero);

            if (cuenta2.getSaldo() < 0) {
                cuenta2.setSaldo(cuenta2.getSaldo() + dinero);
                cuenta2.setSaldo(cuenta1.getSaldo() - dinero);
                return "No se pudo realizar la transferencia, la cuenta se quedó en números negativos";
            } else {
                return "La transferencia se realizó correctamente";
            }
        }
    }

    //Redondear saldo a dos decimales
    public static Double convertir (Double valor){
        DecimalFormat formato = new DecimalFormat("#.00");
        String valorString = formato.format(valor).replace(",",".");
        valor = Double.valueOf(valorString);
        return valor;
    }
}
