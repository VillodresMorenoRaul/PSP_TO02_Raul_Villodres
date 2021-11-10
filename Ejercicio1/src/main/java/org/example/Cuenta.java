package org.example;

import sun.tools.tree.SynchronizedStatement;

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
        return "El saldo actual de la cuenta " + getNombreCuenta() + "Es de: " + getSaldo();
    }

    //Realizar un ingreso
    public synchronized String realizarIngreso(Double dinero){
        this.saldo = this.saldo + dinero;
        return "El dinero ingresado es de " + dinero;
    }

    //Retirar dinero
    public synchronized String retirarDinero(Double dinero){
        this.saldo = this.saldo - dinero;
        if(this.saldo <= 0){
            this.saldo = this.saldo + dinero;
            return "No se pudo retirar dinero porque se retiraría más del que hay, la retirada de dinero fue cancelada";
        } else {
            return "Se ha realizado la retirada de dinero de forma exitosa, la cantidad retirada es de " + dinero;
        }
    }

    //Transferir dinero de una cuenta a otra
    public synchronized String realizarTransferencia(){

    }

    //Redondear saldo a dos decimales
}
