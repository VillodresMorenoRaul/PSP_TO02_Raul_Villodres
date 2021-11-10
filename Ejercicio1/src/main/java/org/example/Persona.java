package org.example;

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

    }
}
